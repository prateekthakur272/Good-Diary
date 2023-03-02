package com.prateekthakur272.gooddiary.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.prateekthakur272.gooddiary.data.DataHelper
import com.prateekthakur272.gooddiary.data.Page

class PageViewModel(private val application: Application,val diary:String) :AndroidViewModel(application) {
    var page = MutableLiveData(Page("","",""))
    fun loadData(): Page {
        return page.value!!
    }
    fun saveData(page: Page){
        this.page.value = page
    }
}