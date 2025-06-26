package com.asa.gob.mx.asa.data

import com.asa.gob.mx.asa.data.remote.NoticeApi
import com.asa.gob.mx.asa.data.remote.model.NoticeDetailDto
import com.asa.gob.mx.asa.data.remote.model.NoticeDto
import retrofit2.Retrofit

class NoticeRepository(
    private val retrofit: Retrofit
) {

    private val noticeApi = retrofit.create(NoticeApi::class.java)

    suspend fun getNotices(): List<NoticeDto> = noticeApi.getNotices()

    suspend fun getNotices(url: String): List<NoticeDto> = noticeApi.getNotices(url)

    suspend fun getNoticeDetail(id: String): NoticeDetailDto = noticeApi.getNoticeDetail(id)

    suspend fun getNoticeSecure(id: String): NoticeDto = noticeApi.getNoticeSecure(id)

}