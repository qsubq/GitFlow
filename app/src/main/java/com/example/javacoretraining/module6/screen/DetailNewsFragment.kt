package com.example.javacoretraining.module6.screen

import android.os.Build
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.javacoretraining.R
import com.example.javacoretraining.databinding.FragmentDetailNewsBinding
import com.example.javacoretraining.data.model.NewsItem

class DetailNewsFragment : Fragment() {
    private lateinit var binding: FragmentDetailNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentDetailNewsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val newsItem = arguments?.getParcelable("ParcelableNews", NewsItem::class.java)

        binding.imgBack.setOnClickListener {
            this.findNavController().navigate(R.id.action_detailNewsFragment_to_containerFragment)
        }

        if (newsItem != null) {
            // Пришлось сделать статичный аватар, т.к json не может содержать id
            binding.imgNews1.setImageResource(R.drawable.avatar_1)

            binding.tvToolbarTitle.text = newsItem.title
            binding.tvTitle.text = newsItem.title
            binding.tvDate.text = newsItem.date
            binding.tvDesc.text = newsItem.description
            binding.tvFond.text = newsItem.fond
            binding.tvPhones.text = newsItem.numbers
            binding.tvAddress.text = newsItem.place
        }
        binding.imgNews2.setImageResource(R.drawable.cardimage_2)
        binding.imgNews3.setImageResource(R.drawable.cardimage_3)

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
}
