package com.example.javacoretraining.module6.screen.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.javacoretraining.R
import com.example.javacoretraining.databinding.FragmentFilterBinding

class FilterFragment : Fragment() {
    private lateinit var binding: FragmentFilterBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentFilterBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imgBack.setOnClickListener {
            this.findNavController().navigate(R.id.action_filterFragment_to_containerFragment)
        }
        binding.imgConfirm.setOnClickListener {
            this.findNavController().navigate(R.id.action_filterFragment_to_containerFragment)
        }
    }
}
