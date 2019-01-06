package com.group.nugraha.matchschedulekotlin.util

import kotlin.coroutines.CoroutineContext



open class CoroutineContextProvider {
    open val main: CoroutineContext by lazy { Dispactchers.Main }
}