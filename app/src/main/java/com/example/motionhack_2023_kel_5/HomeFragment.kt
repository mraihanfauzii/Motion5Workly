package com.example.motionhack_2023_kel_5

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.example.motionhack_2023_kel_5.creatorProfile.CreatorProfileActivity
import com.example.motionhack_2023_kel_5.databinding.FragmentDiscoverBinding
import com.example.motionhack_2023_kel_5.databinding.FragmentHomeBinding
import com.example.motionhack_2023_kel_5.notification.NotificationActivity
import com.example.motionhack_2023_kel_5.viewModel.DiscoverViewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onNotificationClick()
    }

    private fun onNotificationClick() {
        binding.imgNotification.setOnClickListener {
            val intent = Intent(activity, NotificationActivity::class.java)
            startActivity(intent)
        }
    }
}