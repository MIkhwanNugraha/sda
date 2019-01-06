package com.group.nugraha.matchschedulekotlin.sambungan



import com.group.nugraha.matchschedulekotlin.model.TeamResponse
import retrofit.http.GET
import retrofit.http.Path
import rx.Observable

interface Service {
    @GET("teams/{teamname}")
    fun getTeam(@Path("teamname") teamname: String): Observable<TeamResponse>
}