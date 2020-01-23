package com.example.fullproject.ui.main

import android.util.Log
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
    val mutableError = MutableLiveData<String>()


    init {
        getPost()
    }

    private fun getPost() {
        PostClient.Instant.getCallBookObject()?.enqueue(object : Callback<BookObject> {
            override fun onResponse(call: Call<BookObject>, response: Response<BookObject>) {
                mutableError.value=""

                showData(response)
            }

            override fun onFailure(call: Call<BookObject>, t: Throwable) {
                Log.v("helloNoResp","No Internet Connection")

                mutableError.value="No Internet Connection"
            }
        })

    }

    private fun showData(response: Response<BookObject>) {
        mutableList.value = response.body()?.items?.mapNotNull {
            it.volumeInfo
        }
    }
}