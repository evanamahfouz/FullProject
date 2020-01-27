package com.example.fullproject.data.model

import com.example.fullproject.data.db.VolumeInfoEntity


data class VolumeInfo(
    val title: String? = null,

    val subtitle: String? = null,

    val authors: List<String>? = null,

    val publisher: String? = null,

    val publishedDate: String? = null,

    val description: String? = null,

    val industryIdentifiers: List<IndustryIdentifier>? = null,

    val readingModes: ReadingModes? = null,

    val printType: String? = null,

    val maturityRating: String? = null,

    val allowAnonLogging: Boolean? = null,

    val contentVersion: String? = null,

    val panelizationSummary: PanelizationSummary? = null,

    val imageLinks: ImageLinks? = null,

    val language: String? = null,

    val previewLink: String? = null,

    val infoLink: String? = null
    ,
    val canonicalVolumeLink: String? = null,
    val pageCount: Int? = null,


    val categories: List<String>? = null,


    val averageRating: Double? = null,


    val ratingsCount: Int? = null
) {
    fun mapToVolumInfoEntity(): VolumeInfoEntity {
        return VolumeInfoEntity(0,title, subtitle, authors.toString(),description)
    }
}











