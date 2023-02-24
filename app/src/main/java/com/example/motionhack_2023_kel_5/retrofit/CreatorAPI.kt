package com.example.motionhack_2023_kel_5.retrofit

import com.example.motionhack_2023_kel_5.data.Creators.KreatorList
import com.example.motionhack_2023_kel_5.data.Meetings.MeetingList
import com.example.motionhack_2023_kel_5.data.Professions.ProfessionList
import retrofit2.Call
import retrofit2.http.GET

interface CreatorAPI {

    @GET("creators?page=1&size=50")
    fun Creator(): Call<KreatorList>

    @GET("professions?page=1&size=50")
    fun Profession(): Call<ProfessionList>

    @GET("meetings?page=1&size=50")
    fun Meeting(): Call<MeetingList>
}