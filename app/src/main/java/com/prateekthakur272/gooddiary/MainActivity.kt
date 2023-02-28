package com.prateekthakur272.gooddiary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import com.prateekthakur272.gooddiary.adapter.PageAdapter
import com.prateekthakur272.gooddiary.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
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
        binding.mainNavigationView.setNavigationItemSelectedListener(this)
        //binding.mainNavigationView.menu.getItem(0).subMenu!!.add("Personal diary").icon=AppCompatResources.getDrawable(this,R.drawable.ic_back)

    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.create_new_diary-> {
                Toast.makeText(this, "Create new diary",Toast.LENGTH_SHORT).show()
            }
            else -> {}
        }
        binding.navigationDrawer.closeDrawer(GravityCompat.START)
        return true
    }
}