package com.prateekthakur272.gooddiary

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
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
            intent.putExtra("diary", viewModel.currentDiaryName.value)
            startActivity(intent)
        }
        binding.mainNavigationView.setNavigationItemSelectedListener(this)

        viewModel.currentDiary.observe(this){
            binding.pageRecyclerView.adapter = PageAdapter(viewModel.currentDiary.value!!)
        }
        viewModel.allDiaries.value?.forEachIndexed{ index,item ->
            binding.mainNavigationView.menu[0].subMenu?.add(0,index,0,item)
        }
        viewModel.currentDiaryName.observe(this){
            binding.searchBar.searchTextEdit.hint = "${this.getString(R.string.search)} ${viewModel.currentDiaryName.value}"
        }
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.create_new_diary-> {
                createDiary()
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
        viewModel.loadDiary(viewModel.currentDiaryName.value!!)
    }
    private fun createDiary(){
        val dialog = Dialog(this)
        dialog.apply {
            setCancelable(true)
            setContentView(R.layout.create_diary_dialog_layout)
            findViewById<Button>(R.id.button_create_diary).setOnClickListener {
                val diaryName = findViewById<EditText>(R.id.diary_name).text.toString()
                viewModel.createDiary(diaryName)
                dismiss()
            }
            show()
        }
    }
}