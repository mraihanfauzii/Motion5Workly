package com.example.motionhack_2023_kel_5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.motionhack_2023_kel_5.data.Meetings.Meeting
import com.example.motionhack_2023_kel_5.databinding.ActivityMeetingDetailsBinding
import com.example.motionhack_2023_kel_5.viewModel.DiscoverViewModel

class MeetingDetailsActivity : AppCompatActivity() {
    private lateinit var meetingTitle:String
    private lateinit var meetingCreatorName:String
    private lateinit var meetingCreatorUserName:String
    private lateinit var meetingDateStartAt:String
    private lateinit var meetingDateEndAt:String
    private lateinit var meetingDescription:String
    private lateinit var meetingMvvm: DiscoverViewModel

    private lateinit var binding: ActivityMeetingDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMeetingDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        meetingMvvm = ViewModelProviders.of(this)[DiscoverViewModel::class.java]

        binding.imgMeetingDetailBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        getMeetingInformationFromIntent()

        meetingMvvm.getMeetingDetail(meetingCreatorUserName)
        observerCreatorDetailLiveData()
    }

    private fun observerCreatorDetailLiveData() {
        meetingMvvm.observeMeetingDetailsLiveData().observe(this,object : Observer<Meeting> {
            override fun onChanged(t: Meeting?) {
                val meeting = t

                binding.edtCreatorMeetingName.text = meeting!!.creator.name
                binding.edtCreatorMeetingJob.text = meeting!!.creator.professions[0].name
                binding.edtMeetingDetailsTime.text = meeting!!.startAt
                binding.edtDetailMeeting.text = meeting!!.description
            }

        })
    }

    private fun getMeetingInformationFromIntent() {
        val intent = intent
        meetingTitle = intent.getStringExtra(DiscoverFragment.MEETING_TITLE)!!
        meetingCreatorUserName = intent.getStringExtra(DiscoverFragment.MEETING_CreatorUserName)!!
        meetingCreatorName = intent.getStringExtra(DiscoverFragment.MEETING_CreatorName)!!
        meetingDateStartAt = intent.getStringExtra(DiscoverFragment.MEETING_DateStartAt)!!
        meetingDateEndAt = intent.getStringExtra(DiscoverFragment.MEETING_DateEndAt)!!
        meetingDescription = intent.getStringExtra(DiscoverFragment.MEETING_Description)!!
    }
}