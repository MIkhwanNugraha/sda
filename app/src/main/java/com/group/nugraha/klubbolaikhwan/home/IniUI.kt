package com.group.nugraha.klubbolaikhwan.home

import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.group.nugraha.klubbolaikhwan.R
import org.jetbrains.anko.*

class IniUI : AnkoComponent<ViewGroup>{

    companion object {
        val namaId = 1
        val gambarId = 2
    }

    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
        linearLayout {
            orientation = LinearLayout.HORIZONTAL
            lparams(matchParent, wrapContent)
            padding = dip(16)

            imageView {
                id = gambarId
                imageResource = R.drawable.img_realmadrid
            }.lparams(dip(50), dip(50))

            textView{
                id = namaId
                text = "Real madrid FC"
            }.lparams(matchParent, wrapContent){
                margin = dip(10)
                gravity = Gravity.CENTER_VERTICAL
            }
        }
    }
}