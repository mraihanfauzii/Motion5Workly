package com.example.motionhack_2023_kel_5.retrofit

import com.example.motionhack_2023_kel_5.data.Creators.KreatorList
import com.example.motionhack_2023_kel_5.data.Meetings.MeetingList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CreatorAPI {

    @GET("creators?page=1&size=50")
    fun Creator(): Call<KreatorList>

    @GET("creators?")
    fun getCreatorDetails(@Query("q") username:String) : Call<KreatorList>

    @GET("creators?page=1&size=50")
    fun Profession(): Call<KreatorList>

    //Digunakan untuk menampilkan sedikit(10 meeting) UpcomingSessions di dalam fragment discover
    @GET("meetings?page=1&size=10")
    fun DiscoverMeeting(): Call<MeetingList>

    //Untuk menampilkan 50 meeting ketika user mengklik tanda panah kekanan disebelah tulisan Upcoming Sessions
    @GET("meetings?page=1&size=50")
    fun AllMeeting(): Call<MeetingList>

    @GET("meetings?")
    fun getMeetingProfileDetails(@Query("q") creator:String) : Call<MeetingList>

}