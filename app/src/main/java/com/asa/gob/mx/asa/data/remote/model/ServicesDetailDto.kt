package com.asa.gob.mx.asa.data.remote.model

import com.google.gson.annotations.SerializedName

data class ServicesDetailDto(
    @SerializedName("image")
    var image: String? = null,
    @SerializedName("description")
    var description: String? = null
)
