package com.example.fullproject.ui.main

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fullproject.BR
import com.example.fullproject.R
import com.example.fullproject.databinding.ListQuickBinding
import com.example.fullproject.repository.VolumeInfo

class MyAdapter(val context: Context, private val list: MutableList<VolumeInfo>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    /**
     * View holder class
     */
    inner class MyViewHolder(val binding: ListQuickBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: VolumeInfo) {
            binding.setVariable(BR.listmodel, data)
            binding.executePendingBindings()
        }

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val c = list[position]

        holder.apply {
            holder.bind(list.get(position))


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


        val binding: ListQuickBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context)
            , R.layout.list_quick, parent, false
        )
        return MyViewHolder(binding)
    }

}