package com.example.fullproject.data

import com.example.fullproject.repository.BookObject
import retrofit2.Call
import retrofit2.http.GET

interface BookAPI {
    @GET("books/v1/volumes?q=9781451648546")
    fun getBook(): Call<BookObject>
}