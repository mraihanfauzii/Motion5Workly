package com.example.motionhack_2023_kel_5.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.motionhack_2023_kel_5.data.Creators.Creator
import com.example.motionhack_2023_kel_5.data.Creators.KreatorList
import com.example.motionhack_2023_kel_5.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreatorViewModel():ViewModel() {
    private var creatorDetailsLiveData = MutableLiveData<Creator>()

    fun getCreatorDetail(username:String){
        RetrofitInstance.api.getCreatorDetails(username).enqueue(object : Callback<KreatorList>{
            override fun onResponse(call: Call<KreatorList>, response: Response<KreatorList>) {
                if(response.body()!=null){
                    creatorDetailsLiveData.value = response.body()!!.creators[0]
                }
                else
                    return
            }

            override fun onFailure(call: Call<KreatorList>, t: Throwable) {
                Log.d("CreatorProfileActivity",t.message.toString())
            }

        })
    }

    fun observeCreatorDetailsLiveData():LiveData<Creator>{
        return creatorDetailsLiveData
    }
}