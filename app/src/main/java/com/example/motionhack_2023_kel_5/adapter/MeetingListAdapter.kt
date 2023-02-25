package com.example.motionhack_2023_kel_5.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.motionhack_2023_kel_5.data.Creators.Creator
import com.example.motionhack_2023_kel_5.data.Meetings.Meeting
import com.example.motionhack_2023_kel_5.databinding.MeetinglistBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MeetingListAdapter(): RecyclerView.Adapter<MeetingListAdapter.MeetingListViewHolder>(){
    lateinit var onItemClick:((Meeting) -> Unit)
    private var meetingsList = ArrayList<Meeting>()

    fun setMeetings(meetingsList:ArrayList<Meeting>){
        this.meetingsList = meetingsList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeetingListViewHolder {
        return MeetingListViewHolder(MeetinglistBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MeetingListViewHolder, position: Int) {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        inputFormat.timeZone = TimeZone.getTimeZone("UTC")

        val outputFormat = SimpleDateFormat("dd MMMM yyyy, HH:mm:ss", Locale.getDefault())
        outputFormat.timeZone = TimeZone.getDefault()

        val InputStartAt = inputFormat.parse(meetingsList[position].startAt)
        val InputEndAt = inputFormat.parse(meetingsList[position].endAt)

        val StartAt = outputFormat.format(InputStartAt)
        val EndAt = outputFormat.format(InputEndAt)

        holder.binding.edtMeetingName.text = "Title : ${meetingsList[position].title}"
        holder.binding.edtMeetingCreator.text = "By : ${meetingsList[position].creator.name}"
        holder.binding.edtMeetingTime.text = "Time : ${StartAt} - ${EndAt}"

        holder.itemView.setOnClickListener {
            onItemClick.invoke(meetingsList[position])
        }
    }

    override fun getItemCount(): Int {
        return meetingsList.size
    }

    class MeetingListViewHolder(val binding: MeetinglistBinding):RecyclerView.ViewHolder(binding.root)
}