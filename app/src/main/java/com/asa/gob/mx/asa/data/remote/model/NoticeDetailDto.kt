package com.asa.gob.mx.asa.data.remote.model

import com.google.gson.annotations.SerializedName


data class NoticeDetailDto(
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("image")
    var image: String? = null,
    @SerializedName("url")
    var url: String? = null
)
