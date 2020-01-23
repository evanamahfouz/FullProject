package com.example.fullproject.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fullproject.R
import com.example.fullproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var adapter: MyAdapter
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val viewModel: PostViewModel = ViewModelProviders.of(this).get(PostViewModel::class.java)
        // in content do not change the layout size of the RecyclerView
        adapter = MyAdapter(this)

        with(binding.myRecyclerView) {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        viewModel.mutableList.observe(this, Observer {
            adapter.list = it
            binding.myRecyclerView.adapter = adapter
        })
    }
}
