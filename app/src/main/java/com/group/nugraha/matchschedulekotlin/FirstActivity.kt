package com.group.nugraha.matchschedulekotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class FirstActivity : AppCompatActivity() {
    private var name: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val inte = intent
        name = inte.getStringExtra("key")

    }
}