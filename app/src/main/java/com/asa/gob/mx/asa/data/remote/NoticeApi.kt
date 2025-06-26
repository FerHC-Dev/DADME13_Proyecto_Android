package com.asa.gob.mx.asa.data.remote

import com.asa.gob.mx.asa.data.remote.model.NoticeDetailDto
import com.asa.gob.mx.asa.data.remote.model.NoticeDto
import com.asa.gob.mx.asa.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface NoticeApi {

    suspend fun getNotices(
        @Url
        url: String?
    ): List<NoticeDto>


    @GET(Constants.URL_NOTICE)
    suspend fun getNotices(): List<NoticeDto>

    @GET(Constants.URL_NOTICE_DETAIL)
    suspend fun getNoticeDetail(
        @Query("id") id: String?
    ): NoticeDetailDto

    @GET(Constants.URL_NOTICE)
    suspend fun getNoticeSecure(
        @Query("id") id: String?
    ): NoticeDto

}