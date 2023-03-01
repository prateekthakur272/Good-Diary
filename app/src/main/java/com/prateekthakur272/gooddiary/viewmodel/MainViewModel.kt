package com.prateekthakur272.gooddiary.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.prateekthakur272.gooddiary.data.DataHelper

class MainViewModel(application: Application) :AndroidViewModel(application){
    private var dataHelper: DataHelper = DataHelper(getApplication())
    val allDiaries = MutableLiveData(dataHelper.getDiaries())
    private var currentDiaryName = MutableLiveData(dataHelper.getDiaries()[0])
    var currentDiary = MutableLiveData(dataHelper.getPages(currentDiaryName.value!!))

    fun loadDiary(name:String){
        currentDiaryName.value = name
        currentDiary.value = dataHelper.getPages(name)
    }
}