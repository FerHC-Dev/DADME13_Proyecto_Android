package com.asa.gob.mx.asa.data.remote.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Notices(
    val id: String,
    val title: String,
    val image: String
): Parcelable
