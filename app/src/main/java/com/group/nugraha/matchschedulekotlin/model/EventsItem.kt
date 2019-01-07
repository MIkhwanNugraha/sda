package com.group.nugraha.matchschedulekotlin.model

import com.google.gson.annotations.SerializedName

data class EventsItem (
    @SerializedName("idEvent")

    var idEvent: String?=null,



    @SerializedName("idLeague")

    val idLeague: String?=null,



    @SerializedName("idAwayTeam")

    var idAwayTeam: String?=null,



    @SerializedName("intHomeScore")

    val intHomeScore: String?=null,



    @SerializedName("idHomeTeam")

    var idHomeTeam: String?=null,



    @SerializedName("intAwayScore")

    val intAwayScore: String?=null,



    @SerializedName("dateEvent")

    val dateEvent: String?=null,



    @SerializedName("strAwayTeam")

    val strAwayTeam: String?=null,



    @SerializedName("strHomeTeam")

    val strHomeTeam: String?=null,

    @SerializedName("strHomeLineupDefense")

    val strHomeLineupDefense: String? = null,



    @SerializedName("strAwayLineupDefense")

    val strAwayLineupDefense: String?,

    @SerializedName("strAwayFormation")

    val strAwayFormation: String?,



    @SerializedName("strHomeFormation")

    val strHomeFormation: String?,

    @SerializedName("strAwayLineupSubstitutes")

    val strAwayLineupSubtitutes: String?=null



    )