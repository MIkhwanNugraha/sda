package com.group.nugraha.matchschedulekotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    private var name2: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val inte = intent
        name2 = inte.getStringExtra("key2")

    }
}