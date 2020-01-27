package com.example.fullproject.data.repos


interface DataCallback<T> {
    fun onSuccess(data: T)
    fun onError(t: Throwable)
}