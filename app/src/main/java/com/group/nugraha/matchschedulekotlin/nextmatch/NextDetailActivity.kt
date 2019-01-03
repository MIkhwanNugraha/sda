package com.group.nugraha.matchschedulekotlin.nextmatch

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ProgressBar
import com.group.nugraha.matchschedulekotlin.model.EventsItem

class NextDetailActivity : AppCompatActivity(), NextView {
    var idEvent: String = ""
    var idTandang: String = ""
    var idKandang: String = ""
    var nameKandang: String = ""
    var nameTandang: String = ""

    private lateinit var progresBar: ProgressBar
    private lateinit var presenter: NextDetailPresenter
    private lateinit var events: EventsItem

    companion object {
        const val ID_EVENTS = "id_events"
        const val ID_TANDANG = "id_Tandang"
        const val ID_KANDANG = "id_Kandang"
        const val KANDANG_NAME = "kandang_name"
        const val TANDANG_NAME = "tandang_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.layoutnextdetail)

    override fun showLoading() {

    }

    override fun hideLOading() {
        }

    override fun showEventList(data: List<EventsItem>) {
  }

}