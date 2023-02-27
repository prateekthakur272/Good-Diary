package com.prateekthakur272.gooddiary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.prateekthakur272.gooddiary.databinding.ActivityPageBinding

class PageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonBack.setOnClickListener {
            finish()
        }
    }
}