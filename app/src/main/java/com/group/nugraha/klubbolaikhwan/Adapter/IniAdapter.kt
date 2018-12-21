package com.group.nugraha.klubbolaikhwan.Adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.group.nugraha.klubbolaikhwan.home.IniUI
import com.group.nugraha.klubbolaikhwan.model.Ini
import kotlinx.android.extensions.LayoutContainer
import org.jetbrains.anko.AnkoContext


class IniAdapter (var list : MutableList<Ini>, var listener: (Ini) -> Unit )
    :RecyclerView.Adapter<IniAdapter.IniViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IniViewHolder {
        return IniViewHolder(IniUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: IniViewHolder, position: Int) {
        holder.bindItem(list[position], listener)

    }

    inner class IniViewHolder(itamView: View) : RecyclerView.ViewHolder(itamView){
       var imageView : ImageView
        var textView : TextView

        init {
            imageView = itamView.findViewById(IniUI.gambarId)
            textView = itamView.findViewById(IniUI.namaId)
        }

        fun bindItem (items : Ini, listener : (Ini) -> Unit){
            textView.text = items.namaklub
            Glide.with(itemView.context).load(items.logoklub).into(imageView)
            itemView.setOnClickListener{
                listener(items)
            }
        }


    }

}