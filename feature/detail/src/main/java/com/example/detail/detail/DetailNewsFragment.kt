package com.example.detail.detail

import android.Manifest
import android.app.Dialog
import android.app.Notification
import android.app.Notification.EXTRA_NOTIFICATION_ID
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavDeepLinkBuilder
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.ForegroundInfo
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.core.MyCoreBroadcastReceiver
import com.example.core.utils.navigate
import com.example.detail.R
import com.example.detail.databinding.FragmentDetailNewsBinding
import com.example.domain.domain.model.listModel.DomainData
import com.google.android.material.textview.MaterialTextView

private const val CHANNEL_ID = "14"
private const val NOTIFICATION_ID = 101
private const val RECORD_REQUEST_CODE = 100

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
class DetailNewsFragment : Fragment() {
    private lateinit var binding: FragmentDetailNewsBinding
    private val newsItem by lazy {
        arguments?.getParcelable("ParcelableNews", DomainData::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        createNotificationChannel()
        binding = FragmentDetailNewsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requestNotificationPermission()

        Glide.with(requireContext())
            .load(newsItem?.avatar)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .into(binding.imgNews1)

        with(binding) {
            imgBack.setOnClickListener {
                navigate(R.id.action_detailNewsFragment_to_containerFragment)
            }
            linearLayoutMoneyHelp.setOnClickListener {
                showDialog()
            }
            tvToolbarTitle.text = newsItem?.email
            tvTitle.text = newsItem?.first_name
            tvDate.text = newsItem?.last_name
            tvDesc.text = newsItem?.first_name

            with(imgNews1) {
                setImageResource(R.drawable.cardimage_1)
                setImageResource(R.drawable.cardimage_2)
                setImageResource(R.drawable.cardimage_3)
            }
        }

        val spanTextMail = SpannableStringBuilder("У вас есть вопросы? Напишите нам")
        val clickableSpanMail = object : ClickableSpan() {
            override fun onClick(widget: View) {
                Toast.makeText(requireContext(), "onClickEmail", Toast.LENGTH_SHORT).show()
            }
        }
        spanTextMail.setSpan(clickableSpanMail, 20, 32, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.tvSpanEmail.setText(spanTextMail, TextView.BufferType.SPANNABLE)
        binding.tvSpanEmail.movementMethod = LinkMovementMethod.getInstance()

        val spanTextSite = SpannableStringBuilder("Перейти на сайт организации")
        val clickableSpanSite = object : ClickableSpan() {
            override fun onClick(widget: View) {
                Toast.makeText(requireContext(), "onClickSite", Toast.LENGTH_SHORT).show()
            }
        }
        spanTextSite.setSpan(clickableSpanSite, 0, 27, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.tvSpanSite.setText(spanTextSite, TextView.BufferType.SPANNABLE)
        binding.tvSpanSite.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun createNotificationChannel() {
        val name = getString(R.string.app_name)
        val descriptionText = getString(R.string.dmitriy)
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }

        val notificationManager: NotificationManager =
            requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    private fun showDialog() {
        val dialog = Dialog(requireContext()).apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(false)
            setContentView(R.layout.help_with_money_dialog_layout)
        }

        val btnCancel = dialog.findViewById<AppCompatButton>(R.id.btnCancel)
        val btnSend = dialog.findViewById<AppCompatButton>(R.id.btnSend)
        val editText = dialog.findViewById<AppCompatEditText>(R.id.appCompatEditText)

        var amountOfDonation = 500

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (editText.text.isNullOrEmpty()) {
                    btnSend.isEnabled = false
                } else {
                    btnSend.isEnabled = Integer.parseInt(editText.text.toString()) in 1..9999998
                }
            }
        })

        btnCancel.setOnClickListener {
            dialog.cancel()
        }

        btnSend.setOnClickListener {
            val constraints = Constraints.Builder()
                .setRequiresCharging(true)
                .build()

            val newsData = Data.Builder()
                .putInt("id", newsItem!!.id)
                .putString("title", newsItem!!.email)
                .putInt("sum", editText.text.toString().toInt())
                .build()

            val notificationWorkRequest = OneTimeWorkRequestBuilder<NotificationWorker>()
                .setConstraints(constraints)
                .setInputData(newsData)
                .build()

            WorkManager.getInstance(requireContext())
                .enqueueUniqueWork("WorkName", ExistingWorkPolicy.REPLACE, notificationWorkRequest)

            dialog.cancel()
        }

        val frame100 = dialog.findViewById<FrameLayout>(R.id.frame100)
        val frame500 = dialog.findViewById<FrameLayout>(R.id.frame500)
        val frame1000 = dialog.findViewById<FrameLayout>(R.id.frame1000)
        val frame2000 = dialog.findViewById<FrameLayout>(R.id.frame2000)

        val tv100 = dialog.findViewById<MaterialTextView>(R.id.tv100)
        val tv500 = dialog.findViewById<MaterialTextView>(R.id.tv500)
        val tv1000 = dialog.findViewById<MaterialTextView>(R.id.tv1000)
        val tv2000 = dialog.findViewById<MaterialTextView>(R.id.tv2000)

        frame100.setOnClickListener {
            amountOfDonation = 100
            frame100.setBackgroundColor(resources.getColor(R.color.main_green_color))
            tv100.setTextColor(resources.getColor(R.color.white))

            frame500.setBackgroundColor(resources.getColor(R.color.white))
            tv500.setTextColor(resources.getColor(R.color.main_green_color))
            frame1000.setBackgroundColor(resources.getColor(R.color.white))
            tv1000.setTextColor(resources.getColor(R.color.main_green_color))
            frame2000.setBackgroundColor(resources.getColor(R.color.white))
            tv2000.setTextColor(resources.getColor(R.color.main_green_color))
        }

        frame500.setOnClickListener {
            amountOfDonation = 500
            frame500.setBackgroundColor(resources.getColor(R.color.main_green_color))
            tv500.setTextColor(resources.getColor(R.color.white))

            frame100.setBackgroundColor(resources.getColor(R.color.white))
            tv100.setTextColor(resources.getColor(R.color.main_green_color))
            frame1000.setBackgroundColor(resources.getColor(R.color.white))
            tv1000.setTextColor(resources.getColor(R.color.main_green_color))
            frame2000.setBackgroundColor(resources.getColor(R.color.white))
            tv2000.setTextColor(resources.getColor(R.color.main_green_color))
        }

        frame1000.setOnClickListener {
            amountOfDonation = 1000
            frame1000.setBackgroundColor(resources.getColor(R.color.main_green_color))
            tv1000.setTextColor(resources.getColor(R.color.white))

            frame100.setBackgroundColor(resources.getColor(R.color.white))
            tv100.setTextColor(resources.getColor(R.color.main_green_color))
            frame500.setBackgroundColor(resources.getColor(R.color.white))
            tv500.setTextColor(resources.getColor(R.color.main_green_color))
            frame2000.setBackgroundColor(resources.getColor(R.color.white))
            tv2000.setTextColor(resources.getColor(R.color.main_green_color))
        }

        frame2000.setOnClickListener {
            amountOfDonation = 2000
            frame2000.setBackgroundColor(resources.getColor(R.color.main_green_color))
            tv2000.setTextColor(resources.getColor(R.color.white))

            frame100.setBackgroundColor(resources.getColor(R.color.white))
            tv100.setTextColor(resources.getColor(R.color.main_green_color))
            frame500.setBackgroundColor(resources.getColor(R.color.white))
            tv500.setTextColor(resources.getColor(R.color.main_green_color))
            frame1000.setBackgroundColor(resources.getColor(R.color.white))
            tv1000.setTextColor(resources.getColor(R.color.main_green_color))
        }

        dialog.show()
    }

    private fun requestNotificationPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.POST_NOTIFICATIONS),
            RECORD_REQUEST_CODE,
        )
    }

    class NotificationWorker(private val context: Context, params: WorkerParameters) :
        Worker(context, params) {
        override fun doWork(): Result {
            try {
                with(NotificationManagerCompat.from(context)) {
                    if (ActivityCompat.checkSelfPermission(
                            applicationContext,
                            Manifest.permission.POST_NOTIFICATIONS,
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        notify(NOTIFICATION_ID, createNotification())
                        Log.e("Makism", "notify is working")
                    } else {
                        Log.e("Maksim", "Permission is not granted")
                    }
                }
            } catch (ex: Exception) {
                Log.e("Makism", "cath exception $ex")
                return Result.failure()
            }
            return Result.success()
        }

        override fun getForegroundInfo(): ForegroundInfo {
            return ForegroundInfo(NOTIFICATION_ID, createNotification())
        }

        private fun createNotification(): Notification {
            val pendingIntent = NavDeepLinkBuilder(context)
                .setGraph(com.example.core.R.navigation.nav_graph)
                .setDestination(com.example.core.R.id.detailNewsFragment)
                .createPendingIntent()

            val snoozeIntent = Intent(context, MyCoreBroadcastReceiver::class.java).apply {
                action = "ACTION_SNOOZE"
                putExtra(EXTRA_NOTIFICATION_ID, 0)
            }
            val snoozePendingIntent: PendingIntent =
                PendingIntent.getBroadcast(context, 0, snoozeIntent, PendingIntent.FLAG_IMMUTABLE)

            val title = inputData.getString("title")
            val amount = inputData.getInt("sum", 0)

            return NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.money_icon)
                .setContentTitle(title)
                .setContentText("Спасибо, что пожертвовали $amount ₽! Будем очень признательны, если вы сможете пожертвовать еще больше.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .addAction(
                    R.drawable.share_icon,
                    "Remind me later",
                    snoozePendingIntent,
                )
                .build()
        }
    }
}
