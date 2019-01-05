package com.group.nugraha.matchschedulekotlin.nextmatch

import android.widget.ImageView
import com.google.gson.Gson
import com.group.nugraha.matchschedulekotlin.api.ApiRepository

class PenempelSimbol {
    private lateinit var presenter: SimbolPresenter
    private lateinit var image: ImageView
    val apiRepository = ApiRepository()

    fun loadSimbol(id: String, imgsimbol: ImageView){
        imgsimbol.setImageDrawable(null)
        val gson = Gson()
        presenter = SimbolPresenter(imgsimbol, apiRepository, gson)
        presenter.getSimbol(id)
    }
}