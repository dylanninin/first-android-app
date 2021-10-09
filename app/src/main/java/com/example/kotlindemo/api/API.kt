package com.example.kotlindemo.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST


class API {
    private val service: APIService
    companion object {
        const val URL_PREFIX = "https://httpbin.org/"
    }

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(URL_PREFIX)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(APIService::class.java)
    }

    suspend fun httpPost(): RespBody {
        val body = ReqBody(appID = 1, appType = 4, anyKey = "KotlinDemo")
        return service.httpPost(body=body)
    }
}


interface APIService {

    @POST("post")
    suspend fun httpPost(@Body body: ReqBody): RespBody
}
