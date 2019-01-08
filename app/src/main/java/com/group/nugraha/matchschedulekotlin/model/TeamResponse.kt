package com.group.nugraha.matchschedulekotlin.model

import com.google.gson.annotations.SerializedName

data class TeamResponse (@SerializedName("teams")
                         val teams: List<TeamsItem>)