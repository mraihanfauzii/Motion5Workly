package com.example.motionhack_2023_kel_5.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.motionhack_2023_kel_5.data.Meetings.Meeting
import com.example.motionhack_2023_kel_5.databinding.MeetinglistBinding

class MeetingListAdapter(): RecyclerView.Adapter<MeetingListAdapter.MeetingListViewHolder>(){
    private var meetingsList = ArrayList<Meeting>()

    fun setMeetings(meetingsList:ArrayList<Meeting>){
        this.meetingsList = meetingsList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeetingListViewHolder {
        return MeetingListViewHolder(MeetinglistBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MeetingListViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(meetingsList[position].description)
            .into(holder.binding.ivMeeting)
    }

    override fun getItemCount(): Int {
        return meetingsList.size
    }

    class MeetingListViewHolder(val binding: MeetinglistBinding):RecyclerView.ViewHolder(binding.root)
}