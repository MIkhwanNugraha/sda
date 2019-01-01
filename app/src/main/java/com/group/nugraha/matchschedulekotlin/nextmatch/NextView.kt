package com.group.nugraha.matchschedulekotlin.nextmatch

import com.group.nugraha.matchschedulekotlin.model.EventsItem

interface NextView {
    fun showLoading()
    fun hideLOading()
    fun showEventList(data:List<EventsItem>)
}