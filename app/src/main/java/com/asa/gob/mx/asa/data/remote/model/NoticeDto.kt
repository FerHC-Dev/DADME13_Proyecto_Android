package com.asa.gob.mx.asa.data.remote.model

import com.google.gson.annotations.SerializedName

data class NoticeDto(
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("image")
    var image: String? = null,
    @SerializedName("date")
    var date: String? = null
)
