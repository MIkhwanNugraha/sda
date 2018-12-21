package com.group.nugraha.klubbolaikhwan.home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.group.nugraha.klubbolaikhwan.R
import org.jetbrains.anko.linearLayout
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.verticalLayout

class MainActivity : AppCompatActivity() {

    var iniItem : MutableList<IniItem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initData()

        verticalLayout{
            lparams(matchParent, matchParent)
            orientation = linearLayout.VERTICAL

            recyclerView {
                lparams(matchParent, matchParent)
                layoutManager = LinearLayoutManager(context)
                adapter = IniAdapter(iniItem){
                    //intent
                }
            }
        }
    }

    private fun initData(){
        val namatim = resources.getStringArray(klub_nama)
        val gambarklub = resources.obtainTypedArray(klub_gambar)

        iniItem.clear()
        for (ii in namatim.indices){
            iniItem.add(Ini(namatim[ii], gambarklub.getResourceId(ii,0)))
        }

        gambarklub.recycle()
    }

}
