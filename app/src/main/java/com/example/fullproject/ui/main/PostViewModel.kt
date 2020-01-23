package com.example.fullproject.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fullproject.data.network.PostClient
import com.example.fullproject.data.model.BookObject
import com.example.fullproject.data.model.VolumeInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostViewModel : ViewModel() {

    val mutableList = MutableLiveData<List<VolumeInfo>>()

    init {
        getPost()
    }

    private fun getPost() {
        PostClient.Instant.getCallBookObject()?.enqueue(object : Callback<BookObject> {
            override fun onResponse(call: Call<BookObject>, response: Response<BookObject>) {
                showData(response)
            }

            override fun onFailure(call: Call<BookObject>, t: Throwable) {

            }
        })

    }

    private fun showData(response: Response<BookObject>) {
        mutableList.value = response.body()?.items?.mapNotNull {
            it.volumeInfo
        }
    }
}