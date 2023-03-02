package com.prateekthakur272.gooddiary

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.PopupMenu
import androidx.core.view.GravityCompat
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
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
        viewModel.allDiaries.value?.forEachIndexed{ index,item ->
            binding.mainNavigationView.menu[0].subMenu?.add(Menu.NONE,index,Menu.NONE,item)
        }
        binding.buttonAddPage.setOnClickListener {
            val intent = Intent(this,PageActivity::class.java)
            intent.putExtra("diary", viewModel.currentDiaryName.value)
            startActivity(intent)
        }
        binding.mainNavigationView.setNavigationItemSelectedListener(this)
        binding.searchBar.more.setOnClickListener {
            val popUpMenu = PopupMenu(this,binding.searchBar.more)
            popUpMenu.menuInflater.inflate(R.menu.diary_menu,popUpMenu.menu)
            popUpMenu.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.delete -> {
                        binding.mainNavigationView.menu[0].subMenu?.removeItem(viewModel.allDiaries.value!!.indexOf(viewModel.currentDiaryName.value!!))
                        viewModel.deleteDiary(viewModel.currentDiaryName.value!!)
                        Snackbar.make(binding.root,"${viewModel.currentDiaryName.value} deleted",Snackbar.LENGTH_SHORT).show()
                        viewModel.loadDiary(viewModel.allDiaries.value?.getOrNull(0) ?: "")
                        return@setOnMenuItemClickListener true
                    }
                    else -> {return@setOnMenuItemClickListener false}
                }
            }
            popUpMenu.show()
        }
        viewModel.currentDiary.observe(this){
            binding.pageRecyclerView.adapter = PageAdapter(viewModel.currentDiary.value!!)
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
    private fun createDiary(){
        val dialog = Dialog(this)
        dialog.apply {
            setCancelable(true)
            setContentView(R.layout.create_diary_dialog_layout)
            findViewById<Button>(R.id.button_create_diary).setOnClickListener {
                val diaryName = findViewById<EditText>(R.id.diary_name).text.toString()
                viewModel.createDiary(diaryName)
                viewModel.currentDiaryName.value = diaryName
                binding.mainNavigationView.menu[0].subMenu?.add(Menu.NONE,viewModel.allDiaries.value!!.size - 1,Menu.NONE,diaryName)
                dismiss()
            }
            show()
        }
    }
}