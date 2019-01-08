package com.group.nugraha.matchschedulekotlin.api

import com.group.nugraha.matchschedulekotlin.BuildConfig

object ObjekSportDB {
    fun getEventNext(league: String?): String{
        return "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/eventsnextleague.php?id=" + league
    }

    fun getEventLast(leagueId: String?): String{
        return "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/eventspastleague.php?id=" + leagueId
    }

    fun getLookUpNDP(eventId: String?): String{
        return "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/lookupevent.php?id=" + eventId
    }

    fun getTeamDetail(eventId: String?): String{
        return "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/lookupteam.php?id=" + eventId
    }
}