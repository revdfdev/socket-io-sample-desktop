package com.merkmod.sportika.di

import com.merkmod.sportika.service.ApiService
import org.koin.dsl.module.applicationContext
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory



object Properties {
    val BASE_URL = "http://merkmod.com:1100/api/v1/"
    val NOTIFICATION_URL = "http://merkmod.com:1800/api/notification/"
}




fun createWebService(url: String): ApiService {
    val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    return retrofit.create(ApiService::class.java)
}
