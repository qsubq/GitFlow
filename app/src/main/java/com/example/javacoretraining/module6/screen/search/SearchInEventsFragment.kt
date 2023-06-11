package com.example.javacoretraining.module6.screen.search

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
import androidx.fragment.app.Fragment
import com.example.javacoretraining.databinding.FragmentSearchInEventsBinding

class SearchInEventsFragment : Fragment() {
    private lateinit var binding: FragmentSearchInEventsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSearchInEventsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val text = SpannableStringBuilder("Например, мастер-класс, помощь")
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                Toast.makeText(requireContext(), "Клик", Toast.LENGTH_SHORT).show()
            }
        }
        text.setSpan(clickableSpan, 10, 30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.tvExample.setText(text, TextView.BufferType.SPANNABLE)
        binding.tvExample.movementMethod = LinkMovementMethod.getInstance()
    }
}
