package com.example.motionhack_2023_kel_5

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.motionhack_2023_kel_5.adapter.CreatorListAdapter
import com.example.motionhack_2023_kel_5.creatorProfile.CreatorProfileActivity
import com.example.motionhack_2023_kel_5.data.Creators.Creator
import com.example.motionhack_2023_kel_5.data.Meetings.Meeting
import com.example.motionhack_2023_kel_5.data.Meetings.MeetingList
import com.example.motionhack_2023_kel_5.data.Professions.Profession
import com.example.motionhack_2023_kel_5.data.Professions.ProfessionList
import com.example.motionhack_2023_kel_5.databinding.FragmentDiscoverBinding
import com.example.motionhack_2023_kel_5.viewModel.DiscoverViewModel

class DiscoverFragment : Fragment() {
    private lateinit var binding:FragmentDiscoverBinding
    private lateinit var discoverMvvm:DiscoverViewModel
    private lateinit var Creator: Creator
    private lateinit var CreatorAdapter:CreatorListAdapter
    private lateinit var Meeting: Meeting
    private lateinit var Profession: Profession

    companion object{
        const val Creator_ID = "com.example.motionhack_2023_kel_5.fragments.idCreator"
        const val Creator_Name = "com.example.motionhack_2023_kel_5.fragments.nameCreator"
        const val Creator_Picture = "com.example.motionhack_2023_kel_5.fragments.ppCreator"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        discoverMvvm = ViewModelProviders.of(this)[DiscoverViewModel::class.java]

        CreatorAdapter = CreatorListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDiscoverBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        creatorRecyclerView()
        meetingRecyclerView()
        professionRecyclerView()

        discoverMvvm.creatorItems()
        discoverMvvm.meetingItems()
        discoverMvvm.professionItems()
        observerCreator()
        observerMeeting()
        observerProfession()
        onCreatorClick()
    }

    private fun creatorRecyclerView() {
        binding.rvCheckThemOut.apply {
            layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
            adapter = CreatorAdapter
        }
    }

    private fun meetingRecyclerView() {
        binding.rvUpcomingSessions.apply {
            layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
            adapter = CreatorAdapter
        }
    }

    private fun professionRecyclerView() {
        binding.rvCategories.apply {
            layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
            adapter = CreatorAdapter
        }
    }

    private fun observerCreator() {
        discoverMvvm.observeCreatorLiveData().observe(viewLifecycleOwner
        ) { creatorList->
            CreatorAdapter.setCreators(creatorsList = creatorList as ArrayList<Creator>)
        }
    }

    private fun observerMeeting() {
        discoverMvvm.observeMeetingLiveData().observe(viewLifecycleOwner
        ) { meetingList->
            CreatorAdapter.setMeetings(meetingsList = meetingList as ArrayList<Meeting>)
        }
    }

    private fun observerProfession() {
        discoverMvvm.observeCreatorLiveData().observe(viewLifecycleOwner
        ) { creatorList->
            CreatorAdapter.setProfessions(professionsList = creatorList as ArrayList<Profession>)
        }
    }

    private fun onCreatorClick() {
        binding.rvCheckThemOut.setOnClickListener {
            val intent = Intent(activity, CreatorProfileActivity::class.java)
            intent.putExtra(Creator_ID,Creator.id)
            intent.putExtra(Creator_Name,Creator.name)
            intent.putExtra(Creator_Picture,Creator.profilePhoto)
            startActivity(intent)
        }
    }

}