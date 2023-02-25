package com.example.motionhack_2023_kel_5.creatorProfile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TableLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.example.motionhack_2023_kel_5.DiscoverFragment
import com.example.motionhack_2023_kel_5.MainActivity
import com.example.motionhack_2023_kel_5.R
import com.example.motionhack_2023_kel_5.data.Creators.Creator
import com.example.motionhack_2023_kel_5.databinding.ActivityCreatorProfileBinding
import com.example.motionhack_2023_kel_5.databinding.ActivityMainBinding
import com.example.motionhack_2023_kel_5.viewModel.CreatorViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class CreatorProfileActivity : AppCompatActivity() {
    private lateinit var creatorId:String
    private lateinit var creatorUserName:String
    private lateinit var creatorPicture:String
    private lateinit var creatorMvvm:CreatorViewModel

    private lateinit var binding: ActivityCreatorProfileBinding
    private lateinit var viewPagerAdapter: CPFragmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreatorProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        creatorMvvm = ViewModelProviders.of(this)[CreatorViewModel::class.java]

        binding.imgCreatorProfileBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        getCreatorInformationFromIntent()
        setInformationInViews()

        creatorMvvm.getCreatorDetail(creatorUserName)
        observerCreatorDetailLiveData()

        viewPagerAdapter = CPFragmentAdapter(supportFragmentManager, lifecycle)
        with(binding){
            cpViewPager.adapter = viewPagerAdapter
            TabLayoutMediator(cpTabLayout, cpViewPager){ tab, position ->
                when(position){
                    0 -> tab.text = "Information"
                    1 -> tab.text = "Schedule"
                    2 -> tab.text = "Forum"
                }
            } .attach()
        }
    }

    private fun observerCreatorDetailLiveData() {
        creatorMvvm.observeCreatorDetailsLiveData().observe(this,object : Observer<Creator>{
            override fun onChanged(t: Creator?) {
                val creator = t

                binding.edtCategory.text = creator!!.professions[0].name
                binding.edtCreatorName.text = creator!!.name
                binding.edtCreatorJob.text = creator!!.username
                Glide.with(this@CreatorProfileActivity)
                    .load(creatorPicture)
                    .error(R.drawable.profile)
                    .into(binding.imgCreatorProfilePicture)
            }

        })
    }

    private fun setInformationInViews() {
       Glide.with(applicationContext)
           .load(creatorUserName)
           .into(binding.imgCreatorProfilePicture)
    }

    private fun getCreatorInformationFromIntent() {
        val intent = intent
        creatorId = intent.getStringExtra(DiscoverFragment.Creator_ID)!!
        creatorUserName = intent.getStringExtra(DiscoverFragment.Creator_Name)!!
        creatorPicture = intent.getStringExtra(DiscoverFragment.Creator_Picture)!!
    }
}