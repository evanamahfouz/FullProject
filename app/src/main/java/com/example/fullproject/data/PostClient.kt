package com.example.fullproject.data

import com.example.fullproject.repository.BookObject
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostClient {

    companion object {
        var bookIntefracre:BookAPI?= null
        val BaseUrl = "https://www.googleapis.com/"
        val Instant by lazy {
            PostClient()
        }
    }
  init {

       val retrofit= Retrofit.Builder().baseUrl(BaseUrl).addConverterFactory(
            GsonConverterFactory.create()
        )
            .build()
       bookIntefracre=retrofit.create(BookAPI::class.java)
   }
    internal fun getCallBookObject(): Call<BookObject>? {
        return bookIntefracre?.getBook()
    }


}