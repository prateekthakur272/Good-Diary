package com.prateekthakur272.gooddiary.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prateekthakur272.gooddiary.data.Page
import com.prateekthakur272.gooddiary.databinding.PageItemLayoutBinding

class PageAdapter(private val pages:ArrayList<Page>):RecyclerView.Adapter<PageAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView)
    private lateinit var binding: PageItemLayoutBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = PageItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding.root)
    }

    override fun getItemCount(): Int = pages.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(binding){
            title.text = pages[position].title
            dateTime.text = pages[position].date.toString()
            preview.text = if (pages[position].content.length <= 24) pages[position].content else pages[position].content.substring(0..21)+"..."
        }
    }
}