package com.group.nugraha.matchschedulekotlin.nextmatch

import com.group.nugraha.matchschedulekotlin.model.EventsItem
import com.group.nugraha.matchschedulekotlin.model.TeamsItem

interface NextView {
    fun showLoading()
    fun hideLOading()
    fun showEventList(data:List<EventsItem>)
    fun showTeamList(data:List<TeamsItem>?)
    companion object {
        val nomor_liga = "4328"
    }
}