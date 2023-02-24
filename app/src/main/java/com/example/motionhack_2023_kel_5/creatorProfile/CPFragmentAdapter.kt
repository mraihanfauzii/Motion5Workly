package com.example.motionhack_2023_kel_5.creatorProfile

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class CPFragmentAdapter(fm : FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fm, lifecycle) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        var fragment = Fragment()
        when(position){
            0 -> fragment = InformationFragment()
            1 -> fragment = ScheduleFragment()
            2 -> fragment = ForumFragment()
        }
        return fragment
    }
}