package com.group.nugraha.klubbolaikhwan.Detail

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.group.nugraha.klubbolaikhwan.ListItem.IniUI
import com.group.nugraha.klubbolaikhwan.model.Ini
import org.jetbrains.anko.*

class SecondActivity : AppCompatActivity(){

    companion object {
        val keteranganID = 3
        val POSITIONEXTRA = "position_extra"
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        val intent = intent
        val list = intent.getParcelableExtra<Ini>(POSITIONEXTRA)

        SecondActivityUI(list).setContentView(this)
    }

    inner class SecondActivityUI(var list : Ini): AnkoComponent<SecondActivity>{


        override fun createView(ui: AnkoContext<SecondActivity>) = with(ui) {

            linearLayout {
                orientation = LinearLayout.VERTICAL
                lparams(matchParent, matchParent)

                imageView(){
                    Glide.with(this).load(list.logoklub).into(this)
                    id = IniUI.gambarId
                    padding = dip(10)

                    this@linearLayout.gravity = Gravity.CENTER_HORIZONTAL
                }.lparams(dip(80), dip(80))

                textView {
                    id = IniUI.namaId
                    text = list.namaklub
                    textSize = sp(10).toFloat()
                    gravity = Gravity.CENTER_HORIZONTAL
                    padding = dip(10)
                }

                textView {
                    id = keteranganID
                    text = list.keteranganklub
                    gravity = Gravity.CENTER_HORIZONTAL
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                    padding = dip(10)
                }
            }

        }
    }
}