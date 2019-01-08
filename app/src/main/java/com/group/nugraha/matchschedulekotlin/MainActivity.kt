package com.group.nugraha.matchschedulekotlin

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.group.nugraha.matchschedulekotlin.R.color.colorAccent
import com.group.nugraha.matchschedulekotlin.lastmatch.FirstActivity
import com.group.nugraha.matchschedulekotlin.nextmatch.SecondActivity
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUI().setContentView(this)
    }

    class MainActivityUI : AnkoComponent<MainActivity> {
        override fun createView(ui: AnkoContext<MainActivity>) =  with(ui){
            verticalLayout {
                padding = dip(5)

                button("Last Match"){
                    backgroundColor = ContextCompat.getColor(context,colorAccent)
                    textColor = Color.WHITE
                    setOnClickListener {
                        startActivity<FirstActivity>()
                    }
                }.lparams(matchParent){
                    topMargin = dip(5)
                }

                button("Next Round"){
                    backgroundColor = ContextCompat.getColor(context,colorAccent)
                    textColor = Color.WHITE
                    setOnClickListener {
                        startActivity<SecondActivity>()
                    }
                }.lparams(matchParent){
                    topMargin = dip(5)
                }
            }
       }
    }
}
