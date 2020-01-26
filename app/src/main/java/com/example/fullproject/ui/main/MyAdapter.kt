package com.example.fullproject.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fullproject.R
import com.example.fullproject.data.model.VolumeInfo
import com.example.fullproject.databinding.ListQuickBinding
import com.example.fullproject.ui.description.BookDescriptionActivity

class MyAdapter(private val context: Context): ListAdapter<VolumeInfo,MyAdapter.MyViewHolder>(DiffCallback()){

    var list: List<VolumeInfo> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: ListQuickBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context)
            , R.layout.list_quick, parent, false
        )
        return MyViewHolder(binding).apply {
            itemView.setOnClickListener {
                val context = itemView.context
                val volumeInfo = list[adapterPosition]
                val intent = Intent(context, BookDescriptionActivity::class.java)
                    .putExtra(BookDescriptionActivity.ARG_DESC, volumeInfo.description)
                context.startActivity(intent)
            }
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    /**
     * View holder class
     */
    class MyViewHolder(private val binding: ListQuickBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: VolumeInfo) {
            binding.listModel = data
        }
    }
     class DiffCallback: DiffUtil.ItemCallback<VolumeInfo>() {
        override fun areItemsTheSame(oldItem: VolumeInfo, newItem: VolumeInfo): Boolean {
            if (oldItem.title != newItem.title) return false
            // check if id is the same
            return oldItem.title == newItem.title
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: VolumeInfo, newItem: VolumeInfo): Boolean {
            // check if content is the same
            // equals using data class
            return oldItem == newItem
        }
    }
}