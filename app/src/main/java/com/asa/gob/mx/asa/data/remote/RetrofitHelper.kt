package com.asa.gob.mx.asa.data.remote

//import com.asa.gob.mx.asa.data.remote.ApiKeyInterceptor
//import com.asa.gob.mx.asa.BuildConfig
import com.asa.gob.mx.asa.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {

    val interceptor = HttpLoggingInterceptor().apply{
        //Respuestas al nivel del Body en la operacion
        level = HttpLoggingInterceptor.Level.BODY
    }


    val client = OkHttpClient.Builder().apply{
        addInterceptor(interceptor)
    //    addInterceptor(com.asa.gob.mx.asa.data.remote.ApiKeyInterceptor(BuildConfig.API_KEY))
    }.build()


    fun getRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}