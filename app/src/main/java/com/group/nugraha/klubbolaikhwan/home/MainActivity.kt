package com.group.nugraha.klubbolaikhwan.home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.group.nugraha.klubbolaikhwan.Adapter.IniAdapter
import com.group.nugraha.klubbolaikhwan.R
import com.group.nugraha.klubbolaikhwan.R.array.*
import com.group.nugraha.klubbolaikhwan.model.Ini
import org.jetbrains.anko.linearLayout
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.verticalLayout

class MainActivity : AppCompatActivity() {

    var iniItem : MutableList<Ini> = mutableListOf()

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
                adapter = IniAdapter(iniItem) {
                    startActivity<SecondActivity>(SecondActivity.POSITIONEXTRA to it)
                    val toast = Toast.makeText(context, it.namaklub, Toast.LENGTH_LONG)
                    toast.show()
                }
            }
        }
    }

    private fun initData(){
        val namatim = resources.getStringArray(klub_nama)
        val gambarklub = resources.obtainTypedArray(klub_gambar)
        val keterangan = resources.getStringArray(klub_keterangan)

        iniItem.clear()
        for (ii in namatim.indices){
            iniItem.add(Ini(namatim[ii], gambarklub.getResourceId(ii,0), keterangan[ii]))
        }

        Log.e("info", iniItem.size.toString())

        gambarklub.recycle()
    }

}
