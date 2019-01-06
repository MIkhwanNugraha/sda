package com.group.nugraha.matchschedulekotlin.nextmatch.detail

import com.group.nugraha.matchschedulekotlin.model.EventsItem
import com.group.nugraha.matchschedulekotlin.model.TeamsItem

interface NextDetailView {
    fun tunjukkanLoading()
    fun sembunyikanLoading()
    fun showDetail(matchDetails: List<EventsItem>,
                   homeTeams: List<TeamsItem>,
                   awayTeams: List<TeamsItem>)
}