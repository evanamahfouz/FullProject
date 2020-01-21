package com.example.fullproject.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fullproject.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewmodel: PostViewModel
        // in content do not change the layout size of the RecyclerView
        my_recycler_view?.setHasFixedSize(true)

        // use a linear layout manager
        my_recycler_view?.layoutManager = LinearLayoutManager(this)
        viewmodel = ViewModelProviders.of(this).get(PostViewModel::class.java)
        viewmodel.getPost()
        viewmodel.mutableList.observe(this, Observer {
            val mAdapter = MyAdapter(it.orEmpty())
            my_recycler_view?.adapter = mAdapter
        })


    }
}
