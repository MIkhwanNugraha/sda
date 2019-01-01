package com.group.nugraha.matchschedulekotlin.model

import com.google.gson.annotations.SerializedName

data class EventsResponse (
    @SerializedName("events")

    val Events: List<EventsItem>
)