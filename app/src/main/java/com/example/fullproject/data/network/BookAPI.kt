package com.example.fullproject.data.network

import com.example.fullproject.data.model.BookObject
import retrofit2.Call
import retrofit2.http.GET

interface BookAPI {
    @GET("books/v1/volumes?q=9781451648546")
    fun getBook(): Call<BookObject>
}