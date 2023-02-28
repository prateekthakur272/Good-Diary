package com.prateekthakur272.gooddiary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.prateekthakur272.gooddiary.adapter.PageAdapter
import com.prateekthakur272.gooddiary.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pageRecyclerView.adapter = PageAdapter(arrayListOf())
        binding.pageRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.searchBar.buttonNavigationMenu.setOnClickListener {
            binding.navigationDrawer.openDrawer(binding.mainNavigationView)
        }
        binding.buttonAddPage.setOnClickListener {
            startActivity(Intent(this,PageActivity::class.java))
        }

    }
}