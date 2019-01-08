package com.group.nugraha.matchschedulekotlin.model

import com.google.gson.annotations.SerializedName

data class TeamsItem(
    @SerializedName("idTeam")
    var teamId: String? = null,

    @SerializedName("strTeam")
    var teamName: String? = null,

    @SerializedName("strTeamBadge")
    var strTeamBadge: String? = null
)