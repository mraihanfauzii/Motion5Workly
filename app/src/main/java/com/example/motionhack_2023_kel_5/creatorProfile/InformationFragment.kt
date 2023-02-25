package com.example.motionhack_2023_kel_5.creatorProfile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.motionhack_2023_kel_5.R
import com.example.motionhack_2023_kel_5.adapter.CreatorListAdapter
import com.example.motionhack_2023_kel_5.databinding.FragmentInformationBinding
import com.example.motionhack_2023_kel_5.viewModel.CreatorViewModel
import com.example.motionhack_2023_kel_5.viewModel.DiscoverViewModel

class InformationFragment : Fragment() {
    private lateinit var binding: FragmentInformationBinding
    private lateinit var discoverMvvm: CreatorViewModel
    private lateinit var CreatorAdapter: CreatorListAdapter

    companion object{
        const val Creator_INFORMATION = "com.example.motionhack_2023_kel_5.fragments.edtCreatorId"
        const val Creator_Name = "com.example.motionhack_2023_kel_5.fragments.edtCreatorName"
        const val Creator_Picture = "com.example.motionhack_2023_kel_5.fragments.imgCreatorProfilePicture"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_information, container, false)
    }

}