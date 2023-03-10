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
        file.writeText("[]")
        return true
    }

    fun getDiaries(): List<String> {
        return (directory.listFiles()?.map { it.name.removeSuffix(".json") } ?: listOf())
    }

    fun createPage(page: Page){
        val file = File("${directory.absolutePath}/${page.diary}.json")
        if (file.exists()){
            val gson = Gson()
            val pages = getPages(page.diary)
            pages.add(0,page)
            file.writeText(gson.toJson(pages))
        }
    }

    fun getPages(diary: String):ArrayList<Page>{
        val file = File("${directory.absolutePath}/$diary.json")
        if (file.exists()){
            val gson = Gson()
            return gson.fromJson(file.readText(),object:TypeToken<ArrayList<Page>>(){}.type)
        }
        return arrayListOf()
    }

    fun deleteDiary(name:String){
        val file = File("${directory.absolutePath}/${name}.json")
        if (!file.exists())
            return
        file.delete()
    }
}