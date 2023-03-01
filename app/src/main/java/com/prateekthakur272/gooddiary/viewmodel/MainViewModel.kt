package com.prateekthakur272.gooddiary.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.prateekthakur272.gooddiary.data.DataHelper
import com.prateekthakur272.gooddiary.data.Page

class MainViewModel(application: Application) :AndroidViewModel(application){
    private var dataHelper: DataHelper = DataHelper(getApplication())
    var allDiaries:MutableLiveData<ArrayList<String>>
    var currentDiaryName:String
    var currentDiary:MutableLiveData<ArrayList<Page>>
    init {
        if (!dataHelper.getDiaries().contains("Notes"))
            dataHelper.createDiary("Notes")
        allDiaries = MutableLiveData(dataHelper.getDiaries())
        currentDiaryName = allDiaries.value?.getOrNull(0) ?: ""
        currentDiary = MutableLiveData(dataHelper.getPages(currentDiaryName))
    }

    fun loadDiary(name:String){
        currentDiaryName = name
        currentDiary.value = dataHelper.getPages(name)
    }
}