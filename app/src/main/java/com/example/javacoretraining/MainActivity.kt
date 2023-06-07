package com.example.javacoretraining

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.javacoretraining.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding:ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Base_Theme_JavaCoreTraining)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}