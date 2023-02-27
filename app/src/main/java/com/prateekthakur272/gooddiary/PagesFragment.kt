package com.prateekthakur272.gooddiary

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.prateekthakur272.gooddiary.adapter.PageAdapter
import com.prateekthakur272.gooddiary.databinding.FragmentPagesBinding

class PagesFragment : Fragment() {
    private lateinit var binding: FragmentPagesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentPagesBinding.inflate(inflater,container,false)
        binding.pageRecyclerView.adapter = PageAdapter(arrayListOf())
        binding.pageRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.searchBar.buttonNavigationMenu.setOnClickListener {
            binding.navigationDrawer.openDrawer(binding.mainNavigationView)
        }
        binding.buttonAddPage.setOnClickListener {
            startActivity(Intent(context,PageActivity::class.java))
        }
        return binding.root
    }
}