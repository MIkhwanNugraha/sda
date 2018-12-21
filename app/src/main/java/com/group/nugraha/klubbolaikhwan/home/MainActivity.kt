package com.group.nugraha.klubbolaikhwan.home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.group.nugraha.klubbolaikhwan.Adapter.IniAdapter
import com.group.nugraha.klubbolaikhwan.R
import com.group.nugraha.klubbolaikhwan.R.array.*
import com.group.nugraha.klubbolaikhwan.model.Ini
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivity : AppCompatActivity() {

    var iniItem : MutableList<Ini> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUI().setContentView(this)
    }

    inner class MainActivityUI : AnkoComponent<MainActivity> {
        override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {

            initData()

            verticalLayout {
                lparams(matchParent, matchParent)
                orientation = LinearLayout.VERTICAL

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
