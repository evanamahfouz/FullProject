package com.example.fullproject.ui.main

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fullproject.R
import com.example.fullproject.repository.VolumeInfo
import kotlinx.android.synthetic.main.list_quick.view.*

class MyAdapter(private val list: List<VolumeInfo>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    /**
     * View holder class
     */
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title = view.txt_title
        var sub_title = view.txt_subtitle
        var authors = view.txt_authors


    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val c = list[position]

        holder.apply {
            title.text = c.title
            sub_title.text = c.subtitle

            c.authors?.let {
                holder.authors.append(it.joinToString())

            }

            itemView.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, BookDecripActivity::class.java)
                    .putExtra(BookDecripActivity.ARG_DESC, c.description)
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_quick, parent, false)

        return MyViewHolder(v)
    }

}