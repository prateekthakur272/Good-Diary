package com.prateekthakur272.gooddiary.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

class DataHelper(context: Context) {
    private val directory = File(context.filesDir,"Diaries")
    fun createDiary(name:String): Boolean {
        if (!directory.exists())
            directory.mkdir()
        val file = File(directory.absolutePath+"/${name}.json")
        if (file.exists())
            return false
        file.createNewFile()
        return true
    }

    fun getDiaries(): Array<out File> {
        return directory.listFiles()?: arrayOf()
    }

    fun createPage(title:String,content:String,diary:String){
        val file = File("${directory.absolutePath}/$diary")
        if (file.exists()){
            val gson = Gson()
            val pages = getPages(diary)
            pages.add(Page(title,content))
            file.writeText(gson.toJson(pages))
        }
    }

    fun getPages(diary: String): java.util.ArrayList<Page>{
        val file = File("${directory.absolutePath}/$diary")
        if (file.exists()){
            val gson = Gson()
            return gson.fromJson(file.readText(),object:TypeToken<ArrayList<Page>>(){}.type)
        }
        return arrayListOf()
    }
}