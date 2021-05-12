package com.jetbrains.handson.mpp.sentiaandroidcodechallenge.framework.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://f213b61d-6411-4018-a178-53863ed9f8ec.mock.pstmn.io/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface PropertyApiService{
    @GET("properties")
    fun getProperties():
            Call<NetworkResponse>
}

object NetWorkApi{
    val retrofitService: PropertyApiService by lazy{
        retrofit.create(PropertyApiService::class.java)
    }
}