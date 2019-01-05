package com.group.nugraha.matchschedulekotlin.nextmatch

import android.provider.Contacts
import android.widget.ImageView
import com.google.gson.Gson
import com.group.nugraha.matchschedulekotlin.api.ApiRepository
import kotlin.coroutines.CoroutineContext

class SimbolPresenter(private val view: ImageView,
                      private val apiRepository: ApiRepository,
                      private val gson: Gson,
                      private val contextPool: CoroutineContextProvider = CoroutineContextProvider()) {
    fun getSimbol(teamId: String?){
        async(contextPool)
    }
    open class CoroutineContextProvider{
        open val main: CoroutineContext by lazy { UI }
    }
}