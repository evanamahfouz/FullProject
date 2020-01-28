package com.example.fullproject.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [VolumeInfoEntity::class],
    version = 2
)
abstract class DataBase : RoomDatabase() {
    abstract fun volumeInfoDOA(): VolumInfoDOA

    companion object {
        @Volatile
        private var instance: DataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        fun getInstance(): DataBase = instance!!

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            DataBase::class.java, "volumeInfo.db"
        ).fallbackToDestructiveMigration().allowMainThreadQueries()
            .build()
    }
}