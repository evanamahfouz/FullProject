package com.example.fullproject.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fullproject.data.PostClient
import com.example.fullproject.repository.BookObject
import com.example.fullproject.repository.VolumeInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostViewModel : ViewModel() {

    val mutableList = MutableLiveData<List<VolumeInfo>>()

    fun getPost() {
        PostClient.Instant.getCallBookObject()?.enqueue(object : Callback<BookObject> {
            override fun onResponse(call: Call<BookObject>, response: Response<BookObject>) {

                showdata(response)
            }

            override fun onFailure(call: Call<BookObject>, t: Throwable) {

            }
        })

    }

    private fun showdata(response: Response<BookObject>) {
        val resp = response.body()


        val arr = resp?.items?.map {
            it.volumeInfo
        }?.filterNotNull()

        mutableList.value = arr


    }

}