package com.group.nugraha.matchschedulekotlin.api

import com.group.nugraha.matchschedulekotlin.BuildConfig

object ObjekSportDB {
    fun getEventNext(league: String?): String{
        return "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/eventsnextleague.php?id=" + league
    }
}