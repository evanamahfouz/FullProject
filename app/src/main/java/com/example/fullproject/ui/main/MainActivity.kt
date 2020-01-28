package com.example.fullproject.ui.main

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fullproject.R
import com.example.fullproject.data.db.DataBase
import com.example.fullproject.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

open class MainActivity : AppCompatActivity() {

    private lateinit var adapter: MyAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var dB: DataBase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val viewModel: PostViewModel = ViewModelProviders.of(this).get(PostViewModel::class.java)
        dB = DataBase.getInstance()

        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
            .apply {

                lifecycleOwner = this@MainActivity
                errorMessage = viewModel
            }

        // in content do not change the layout size of the RecyclerView
        adapter = MyAdapter(this)

        with(binding.myRecyclerView) {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        binding.myRecyclerView.adapter = adapter

        viewModel.mutableList.observe(this, Observer {
            Log.v("helloFromMain", it.size.toString()+" "+ it[0].title+ it[1].title+ it[2].title)
            adapter.submitList(it)
            prog.visibility = View.GONE
        })

        viewModel.mutableError.observe(this, Observer { errorLabel ->

            //database
            if (errorLabel.isNotEmpty()) {



                Log.v("OnFailure", "Something Went Wrong")
                prog.visibility = View.GONE

               Toast.makeText(this, "Something Went Wrong", Toast.LENGTH_LONG).show()

            }
        })

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.install_item) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
