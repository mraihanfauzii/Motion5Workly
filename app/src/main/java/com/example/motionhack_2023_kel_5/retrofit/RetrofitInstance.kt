package com.example.motionhack_2023_kel_5.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val api:CreatorAPI by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.hackathon.dinotis.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CreatorAPI::class.java)
    }
}