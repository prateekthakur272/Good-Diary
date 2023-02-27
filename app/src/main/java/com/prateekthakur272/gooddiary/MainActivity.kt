package com.prateekthakur272.gooddiary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.prateekthakur272.gooddiary.adapter.PageAdapter
import com.prateekthakur272.gooddiary.data.Page
import com.prateekthakur272.gooddiary.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.pageRecyclerView.adapter = PageAdapter(arrayListOf(
            Page("title","hello"),
            Page("title","hello"),
            Page("title","hello"),
            Page("title","hello"),
            Page("title","hello"),
            Page("title","hello"),
            Page("title","hello"),
        ))
        binding.pageRecyclerView.layoutManager = LinearLayoutManager(this)
    }
}