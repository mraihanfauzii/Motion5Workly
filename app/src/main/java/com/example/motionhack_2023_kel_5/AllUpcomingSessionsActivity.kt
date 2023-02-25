package com.example.motionhack_2023_kel_5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.motionhack_2023_kel_5.adapter.CreatorListAdapter
import com.example.motionhack_2023_kel_5.adapter.MeetingListAdapter
import com.example.motionhack_2023_kel_5.data.Meetings.Meeting
import com.example.motionhack_2023_kel_5.databinding.ActivityAllUpcomingSessionsBinding
import com.example.motionhack_2023_kel_5.retrofit.CreatorAPI
import com.example.motionhack_2023_kel_5.viewModel.DiscoverViewModel

class AllUpcomingSessionsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAllUpcomingSessionsBinding
    private lateinit var discoverMvvm: DiscoverViewModel
    private lateinit var MeetingAdapter: MeetingListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllUpcomingSessionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        discoverMvvm = ViewModelProviders.of(this)[DiscoverViewModel::class.java]
        MeetingAdapter = MeetingListAdapter()

        meetingRecyclerView()

        discoverMvvm.AllMeetingItems()
        observerMeeting()
        onMeetingClick()

        binding.imgBackAllUpcomingSessions.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun meetingRecyclerView() {
        binding.rvAllUpcomingSessions.apply {
            layoutManager = LinearLayoutManager(this@AllUpcomingSessionsActivity)
            adapter = MeetingAdapter
        }
    }

    private fun observerMeeting() {
        discoverMvvm.observeMeetingLiveData().observe(this) { meetingList->
            MeetingAdapter.setMeetings(meetingsList = meetingList as ArrayList<Meeting>)
        }
    }

    private fun onMeetingClick() {
        MeetingAdapter.onItemClick = { Meeting ->
            val intent = Intent(this@AllUpcomingSessionsActivity,MeetingDetailsActivity::class.java)
            intent.putExtra(DiscoverFragment.MEETING_TITLE,Meeting.title)
            intent.putExtra(DiscoverFragment.MEETING_CreatorName,Meeting.creator.name)
            intent.putExtra(DiscoverFragment.MEETING_DateStartAt,Meeting.startAt)
            intent.putExtra(DiscoverFragment.MEETING_DateEndAt,Meeting.endAt)
            intent.putExtra(DiscoverFragment.MEETING_Description,Meeting.description)
            startActivity(intent)
        }
    }

}