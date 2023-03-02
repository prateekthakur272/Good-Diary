package com.prateekthakur272.gooddiary.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.prateekthakur272.gooddiary.data.DataHelper
import com.prateekthakur272.gooddiary.data.Page

class MainViewModel(application: Application) :AndroidViewModel(application){
    private var dataHelper: DataHelper = DataHelper(getApplication())
    var allDiaries:MutableLiveData<ArrayList<String>>
    var currentDiaryName:MutableLiveData<String>
    var currentDiary:MutableLiveData<ArrayList<Page>>
    init {
        if (!dataHelper.getDiaries().contains("Notes"))
            dataHelper.createDiary("Notes")
        allDiaries = MutableLiveData(dataHelper.getDiaries())
        currentDiaryName = MutableLiveData(allDiaries.value?.getOrNull(0) ?: "")
        currentDiary = MutableLiveData(dataHelper.getPages(currentDiaryName.value!!))
    }

    fun loadDiary(name:String){
        currentDiaryName.value = name
        currentDiary.value = dataHelper.getPages(name)
    }

    fun createDiary(name:String){
        dataHelper.createDiary(name)
        allDiaries.value = dataHelper.getDiaries()
    }
    fun deleteDiary(name:String){
        dataHelper.deleteDiary(name)
        allDiaries.value = dataHelper.getDiaries()
    }
}