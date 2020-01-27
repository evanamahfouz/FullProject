package com.example.fullproject

import android.app.Application
import android.content.Context
import com.example.fullproject.data.db.DataBase

class App : Application() {


    companion object {
        private lateinit var instance: App

        fun application() = instance
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        DataBase.invoke(this)

    }

}