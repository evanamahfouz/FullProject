package com.example.fullproject.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.fullproject.data.model.VolumeInfo

@Entity(tableName = "volumeInfo_tb")
data class VolumeInfoEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name = "title") var title: String?,
    @ColumnInfo(name = "subtitle") var subtitle: String?,
    @ColumnInfo(name = "authors") var authors: String,
    @ColumnInfo(name = "description") var description: String?



) {
    fun mapToVolumInfo(): VolumeInfo {
        return VolumeInfo(title, subtitle, authors.split(','), null, null, description)
    }
}