package com.example.motionhack_2023_kel_5.creatorProfile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.motionhack_2023_kel_5.DiscoverFragment
import com.example.motionhack_2023_kel_5.MeetingDetailsActivity
import com.example.motionhack_2023_kel_5.R
import com.example.motionhack_2023_kel_5.adapter.CreatorListAdapter
import com.example.motionhack_2023_kel_5.adapter.MeetingListAdapter
import com.example.motionhack_2023_kel_5.adapter.ProfessionListAdapter
import com.example.motionhack_2023_kel_5.data.Creators.Creator
import com.example.motionhack_2023_kel_5.data.Meetings.Meeting
import com.example.motionhack_2023_kel_5.databinding.FragmentDiscoverBinding
import com.example.motionhack_2023_kel_5.databinding.FragmentScheduleBinding
import com.example.motionhack_2023_kel_5.viewModel.DiscoverViewModel

class ScheduleFragment : Fragment() {
    private lateinit var binding:FragmentScheduleBinding
    private lateinit var discoverMvvm: DiscoverViewModel
    private lateinit var MeetingAdapter: MeetingListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        discoverMvvm = ViewModelProviders.of(this)[DiscoverViewModel::class.java]

        MeetingAdapter = MeetingListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentScheduleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        meetingRecyclerView()

        discoverMvvm.DiscoverMeetingItems()

        observerMeeting()
        onMeetingClick()
    }

    private fun meetingRecyclerView() {
        binding.rvCreatorMeeting.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = MeetingAdapter
        }
    }

    private fun observerMeeting() {
        discoverMvvm.observeMeetingLiveData().observe(viewLifecycleOwner
        ) { meetingList->
            MeetingAdapter.setMeetings(meetingsList = meetingList as ArrayList<Meeting>)
        }
    }

    private fun onMeetingClick() {
        MeetingAdapter.onItemClick = { Meeting ->
            val intent = Intent(activity, MeetingDetailsActivity::class.java)
            intent.putExtra(DiscoverFragment.MEETING_TITLE,Meeting.title)
            intent.putExtra(DiscoverFragment.MEETING_CreatorName,Meeting.creator.name)
            intent.putExtra(DiscoverFragment.MEETING_DateStartAt,Meeting.startAt)
            intent.putExtra(DiscoverFragment.MEETING_DateEndAt,Meeting.endAt)
            intent.putExtra(DiscoverFragment.MEETING_Description,Meeting.description)
            startActivity(intent)
        }
    }

}