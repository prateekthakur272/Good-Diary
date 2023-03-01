package com.prateekthakur272.gooddiary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import com.prateekthakur272.gooddiary.adapter.PageAdapter
import com.prateekthakur272.gooddiary.databinding.ActivityMainBinding
import com.prateekthakur272.gooddiary.viewmodel.MainViewModel

class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel:MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = MainViewModel(application)

        setContentView(binding.root)

        binding.pageRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.searchBar.buttonNavigationMenu.setOnClickListener {
            binding.navigationDrawer.openDrawer(binding.mainNavigationView)
        }
        binding.buttonAddPage.setOnClickListener {
            val intent = Intent(this,PageActivity::class.java)
            intent.putExtra("diary", viewModel.currentDiaryName)
            startActivity(intent)
        }
        binding.mainNavigationView.setNavigationItemSelectedListener(this)
        binding.searchBar.searchTextEdit.hint = "${this.getString(R.string.search)} ${viewModel.currentDiaryName}"

        viewModel.currentDiary.observe(this){
            binding.pageRecyclerView.adapter = PageAdapter(viewModel.currentDiary.value!!)
        }
        viewModel.allDiaries.value?.forEachIndexed{ index,item ->
            binding.mainNavigationView.menu[0].subMenu?.add(0,index,0,item)
        }
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.create_new_diary-> {
                Toast.makeText(this,"Create",Toast.LENGTH_SHORT).show()
            }
            else -> {
                viewModel.loadDiary(viewModel.allDiaries.value?.get(item.itemId) ?: "")
            }
        }
        binding.navigationDrawer.closeDrawer(GravityCompat.START)
        return true
    }
    override fun onRestart() {
        super.onRestart()
        viewModel.loadDiary(viewModel.currentDiaryName)
    }
}