package com.merkmod.sportika.service

import com.merkmod.sportika.models.LoginStatus
import com.merkmod.sportika.models.PushResponse
import com.merkmod.sportika.models.RegisterResponse
import io.reactivex.Observable
import retrofit2.http.*

interface ApiService {

    @GET("login")
    fun login(@Query("userName") userName: String, @Query("passWord") password: String)
            :Observable<LoginStatus>

    @POST("register")
    @FormUrlEncoded
    fun registerUser(@Field("userId") userId: String, @Field("connectionId") connectionId: String)
            :Observable<RegisterResponse>

    @POST("push")
    @FormUrlEncoded
    fun pushNotification(@Field("userId") userId: String, @Field("message") message: String):
            Observable<PushResponse>
}