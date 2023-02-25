package com.example.motionhack_2023_kel_5

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.motionhack_2023_kel_5.adapter.FollowingAdapter
import com.example.motionhack_2023_kel_5.adapter.UserMeetingAdapter
import com.example.motionhack_2023_kel_5.creatorProfile.CreatorProfileActivity
import com.example.motionhack_2023_kel_5.data.Creators.Creator
import com.example.motionhack_2023_kel_5.data.Meetings.Meeting
import com.example.motionhack_2023_kel_5.data.Meetings.MeetingList
import com.example.motionhack_2023_kel_5.databinding.FragmentDiscoverBinding
import com.example.motionhack_2023_kel_5.databinding.FragmentHomeBinding
import com.example.motionhack_2023_kel_5.notification.NotificationActivity
import com.example.motionhack_2023_kel_5.viewModel.DiscoverViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
//Penerapan Firebase Firestore untuk booking dan follow creator tidak sempat terealisasi karena keterbatasan waktu
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var followingadapter: FollowingAdapter
    private lateinit var usermeetingadapter: UserMeetingAdapter
    private lateinit var followingCreatorList: ArrayList<Creator>
    private lateinit var meetingUserList: ArrayList<Meeting>
    private val followingCreatorDBRef = Firebase.firestore.collection("FollowingCreator")
    private val meetingUserDBRef = Firebase.firestore.collection("meetingUser")

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
       // onNotificationClick()

      //  auth = FirebaseAuth.getInstance()
       // followingCreatorList = ArrayList()
      //  followingadapter = FollowingAdapter(requireContext(),followingCreatorList,this,this)

      //  meetingUserList = ArrayList()
      //  usermeetingadapter = UserMeetingAdapter(requireContext(),meetingUserList,this,this)

       // val followingLayoutManager = GridLayoutManager(context, 2)
       // binding.rvFollowing.layoutManager = followingLayoutManager
       // binding.rvFollowing.adapter = followingadapter

       // val usermeetingLayoutManager = GridLayoutManager(context, 2)
       // binding.rvFollowing.layoutManager = usermeetingLayoutManager
       // binding.rvFollowing.adapter = usermeetingadapter

        //displayFollowing()
        //displayUserMeeting()
    }
/*
    private fun displayUserMeeting() {
        meetingUserDBRef.whereEqualTo("uid", auth.currentUser!!.uid)
            .get()
            .addOnSuccessListener { querySnapshot ->
                for (item in querySnapshot) {
                    val usermeeting = item.toObject<Meeting>()
                    meetingUserList.add(usermeeting)
                }
            }
    }

    private fun displayFollowing() {
        followingCreatorDBRef.whereEqualTo("uid", auth.currentUser!!.uid)
            .get()
            .addOnSuccessListener { querySnapshot ->
                for (item in querySnapshot) {
                    val followingcreator = item.toObject<Creator>()
                    followingCreatorList.add(followingcreator)
                }
            }
    }

    override fun onClickFollowing(item: Creator) {
        followingCreatorDBRef.whereEqualTo("uid",auth.currentUser!!.uid)
            .whereEqualTo("pid",item.pid)
            .get()
            .addOnSuccessListener { querySnapshot ->
                for (item in querySnapshot){
                    followingCreatorDBRef.document(item.id).delete()
                    followingCreatorList.remove(item.toObject<Creator>())
                    adapter.notifyDataSetChanged()
                    requireActivity().toast("Removed From the Liked Items")
                }
            }
            .addOnFailureListener {
                requireActivity().toast("Failed To Remove From Liked Items")
            }
    }

    private fun onNotificationClick() {
        binding.imgNotification.setOnClickListener {
            val intent = Intent(activity, NotificationActivity::class.java)
            startActivity(intent)
        }
    }*/
}