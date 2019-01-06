package com.group.nugraha.matchschedulekotlin.sambungan


import com.group.nugraha.matchschedulekotlin.api.ObjekSportDB
import com.group.nugraha.matchschedulekotlin.model.TeamsItem
import retrofit.http.GET
import retrofit.http.Path
import rx.Observable

interface Service {
    @GET("teams/{teamname}")
    fun getData(@Path("teamname") teamname: String): Observable<TeamsItem>
}