package com.example.javacoretraining.module6.screen

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.Fragment
import com.example.javacoretraining.R
import com.example.javacoretraining.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pickMedia =
            registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
                if (uri != null) {
                    binding.imgProfile.setImageURI(uri)
                }
            }
        val resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val imageBitmap = result.data?.extras?.get("data") as Bitmap
                    binding.imgProfile.setImageBitmap(imageBitmap)
                }
            }

        binding.imgProfile.setOnClickListener {
            val alertDialogBuilder = AlertDialog.Builder(requireContext())
            val customView =
                LayoutInflater.from(requireContext()).inflate(R.layout.dialog_item_layout, null)
            alertDialogBuilder.setView(customView)
            val dialog = alertDialogBuilder.create()

            val firstItem = customView.findViewById<LinearLayoutCompat>(R.id.first_item)
            firstItem.setOnClickListener {
                pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))

                dialog.cancel()
            }

            val secondItem = customView.findViewById<LinearLayoutCompat>(R.id.second_item)
            secondItem.setOnClickListener {
                val intentCamera = Intent("android.media.action.IMAGE_CAPTURE")
                resultLauncher.launch(intentCamera)
                dialog.cancel()
            }

            val thirdItem = customView.findViewById<LinearLayoutCompat>(R.id.third_item)
            thirdItem.setOnClickListener {
                binding.imgProfile.setImageResource(0)
                dialog.cancel()
            }

            dialog.show()
        }
    }
}
