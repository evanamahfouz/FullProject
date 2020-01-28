package com.example.fullproject.data.db

import androidx.room.*

@Dao
interface VolumInfoDOA {
    @Query("SELECT * FROM volumeInfo_tb")
    fun getAll(): List<VolumeInfoEntity>

    @Query("SELECT * FROM volumeInfo_tb WHERE title LIKE :title limit 1")
    fun findByTitle(title: String): VolumeInfoEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(data: List<VolumeInfoEntity>)


    @Delete
    fun delete(data: VolumeInfoEntity)

    @Update
    fun updateTodo(vararg data: VolumeInfoEntity)


}