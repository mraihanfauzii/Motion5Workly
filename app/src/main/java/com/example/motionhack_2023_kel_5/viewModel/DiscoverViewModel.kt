package com.example.motionhack_2023_kel_5.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.motionhack_2023_kel_5.data.Creators.Creator
import com.example.motionhack_2023_kel_5.data.Creators.KreatorList
import com.example.motionhack_2023_kel_5.data.Meetings.Meeting
import com.example.motionhack_2023_kel_5.data.Meetings.MeetingList
import com.example.motionhack_2023_kel_5.data.Professions.Profession
import com.example.motionhack_2023_kel_5.data.Professions.ProfessionList
import com.example.motionhack_2023_kel_5.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DiscoverViewModel : ViewModel() {
    private var creatorLiveData = MutableLiveData<List<Creator>>()
    private var meetingLiveData = MutableLiveData<List<Meeting>>()

    fun creatorItems(){
        RetrofitInstance.api.Creator().enqueue(object : Callback<KreatorList>{
            override fun onResponse(call: Call<KreatorList>, response: Response<KreatorList>) {
                if(response.body()!=null){
                    creatorLiveData.value = response.body()!!.creators
                }
            }
            override fun onFailure(call: Call<KreatorList>, t: Throwable) {
                Log.d("DiscoverFragment", t.message.toString())
            }
        })
    }

    fun meetingItems(){
        RetrofitInstance.api.Meeting().enqueue(object : Callback<MeetingList>{
            override fun onResponse(call: Call<MeetingList>, response: Response<MeetingList>) {
                if(response.body()!=null){
                    meetingLiveData.value = response.body()!!.meetings
                }
            }
            override fun onFailure(call: Call<MeetingList>, t: Throwable) {
                Log.d("DiscoverFragment", t.message.toString())
            }
        })
    }

    fun observeCreatorLiveData(): LiveData<List<Creator>> {
        return creatorLiveData
    }

    fun observeMeetingLiveData(): LiveData<List<Meeting>> {
        return meetingLiveData
    }
}