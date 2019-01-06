package com.group.nugraha.matchschedulekotlin.nextmatch

import com.google.gson.Gson
import com.group.nugraha.matchschedulekotlin.api.ApiRepository
import com.group.nugraha.matchschedulekotlin.api.ObjekSportDB
import com.group.nugraha.matchschedulekotlin.model.EventsResponse
import com.group.nugraha.matchschedulekotlin.sambungan.NextView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class NextDetailPresenter (private val view: NextView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson){
    fun getLookUpEvents(eventId: String?){
        view.showLoading()
        doAsync {
            val datandp = gson.fromJson(apiRepository.doRequest(ObjekSportDB.getLookUpNDP(eventId)),
                EventsResponse::class.java)
            uiThread {
                view.showEventList(datandp.Events)
                view.hideLOading()
            }
        }
    }
}