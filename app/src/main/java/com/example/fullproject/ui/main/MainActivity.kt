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
import androidx.room.Room
import com.example.fullproject.R
import com.example.fullproject.data.db.DataBase
import com.example.fullproject.data.db.VolumInfoEntity
import com.example.fullproject.data.model.VolumeInfo
import com.example.fullproject.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: MyAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var listDb: List<VolumeInfo>
    private lateinit var dB: DataBase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: PostViewModel = ViewModelProviders.of(this).get(PostViewModel::class.java)
        val db = Room.databaseBuilder(
            applicationContext,
            DataBase::class.java, "volumeInfo.db"
        ).allowMainThreadQueries().build()

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

        viewModel.mutableList.observe(this, Observer {
            listDb = it
            dB = db
            adapter.list = it
            prog.visibility = View.GONE

            binding.myRecyclerView.adapter = adapter


        })
        viewModel.mutableError.observe(this, Observer {

            //database
            if (it.isNotEmpty()) {

                val data = db.volumeInfoDOA().getAll()
                Log.v(
                    "SizeOfData22", change(data)
                        .size.toString()
                )
                Toast.makeText(this, "From DataBase", Toast.LENGTH_LONG).show()
                adapter.list = change(data)

                prog.visibility = View.GONE

                binding.myRecyclerView.adapter = adapter


            }
        })

    }

    private fun insertDb(
        it: List<VolumeInfo>,
        db: DataBase
    ) {
        for (volume in it) {
            db.volumeInfoDOA().insertAll(
                VolumInfoEntity(
                    0,
                    volume.title.orEmpty(),
                    volume.subtitle.orEmpty(),
                    volume.authors.toString(),
                    volume.description.orEmpty()
                )
            )
            Toast.makeText(this, "Installed SuccessFully ", Toast.LENGTH_LONG).show()
        }
    }

    private fun change(data: List<VolumInfoEntity>): List<VolumeInfo> {


        return data.map {
            VolumeInfo(it.title, it.subtitle, it.authors.split(','), null, null, it.description)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.install_item) {
            insertDb(listDb, dB)
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
