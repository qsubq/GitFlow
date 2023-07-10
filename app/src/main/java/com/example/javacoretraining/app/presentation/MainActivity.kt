package com.example.javacoretraining.app.presentation

import android.os.Bundle
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.javacoretraining.R
import com.example.javacoretraining.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Base_Theme_JavaCoreTraining)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        onBackPressedDispatcher.addCallback(this) {
        }
    }
}
