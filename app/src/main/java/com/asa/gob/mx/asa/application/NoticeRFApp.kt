package com.asa.gob.mx.asa.application

import android.app.Application
import com.asa.gob.mx.asa.data.NoticeRepository
import com.asa.gob.mx.asa.data.remote.RetrofitHelper

class NoticeRFApp: Application() {

    private val retrofit by lazy {
        RetrofitHelper().getRetrofit()
    }

    val repository by lazy {
        NoticeRepository(retrofit)
    }

}