package com.example.motionhack_2023_kel_5.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.motionhack_2023_kel_5.data.Creators.Creator
import com.example.motionhack_2023_kel_5.data.Creators.KreatorList
import com.example.motionhack_2023_kel_5.data.Meetings.Meeting
import com.example.motionhack_2023_kel_5.data.Meetings.MeetingList
import com.example.motionhack_2023_kel_5.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DiscoverViewModel : ViewModel() {
    private var creatorLiveData = MutableLiveData<List<Creator>>()
    private var meetingLiveData = MutableLiveData<List<Meeting>>()
    private var meetingDetailsLiveData = MutableLiveData<Meeting>()

    fun getMeetingDetail(username:String){
        RetrofitInstance.api.getMeetingProfileDetails(username).enqueue(object : Callback<MeetingList>{
            override fun onResponse(call: Call<MeetingList>, response: Response<MeetingList>) {
                if(response.body()!=null){
                    meetingDetailsLiveData.value = response.body()!!.meetings[0]
                }
                else
                    return
            }

            override fun onFailure(call: Call<MeetingList>, t: Throwable) {
                Log.d("MeetingDetailsActivity",t.message.toString())
            }

        })
    }

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

    //Hanya menampilkan 10 Meeting Upcomming Sessions
    fun DiscoverMeetingItems(){
        RetrofitInstance.api.DiscoverMeeting().enqueue(object : Callback<MeetingList>{
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

    //Menampilkan 50 meeting ketika tanda panah kanan disebelah upcoming sessions pada fragment discover diklik
    fun AllMeetingItems(){
        RetrofitInstance.api.AllMeeting().enqueue(object : Callback<MeetingList>{
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

    fun ProfileCreatorMeetingItems(creator:String){
        RetrofitInstance.api.getMeetingProfileDetails(creator).enqueue(object : Callback<MeetingList>{
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

    fun observeMeetingDetailsLiveData(): LiveData<Meeting> {
        return meetingDetailsLiveData
    }
}