package com.prateekthakur272.gooddiary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.prateekthakur272.gooddiary.data.DataHelper
import com.prateekthakur272.gooddiary.data.Page
import com.prateekthakur272.gooddiary.databinding.ActivityPageBinding

class PageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPageBinding
    private lateinit var dataHelper: DataHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPageBinding.inflate(layoutInflater)
        dataHelper = DataHelper(this)

        setContentView(binding.root)

        binding.buttonBack.setOnClickListener {
            if (binding.title.text.toString().isNotBlank())
                dataHelper.createPage(Page(binding.title.text.toString(),binding.content.text.toString(),intent.getStringExtra("diary")!!))
            finish()
        }
    }
}