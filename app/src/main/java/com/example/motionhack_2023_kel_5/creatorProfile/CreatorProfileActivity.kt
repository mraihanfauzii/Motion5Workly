package com.example.motionhack_2023_kel_5.creatorProfile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TableLayout
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.motionhack_2023_kel_5.DiscoverFragment
import com.example.motionhack_2023_kel_5.MainActivity
import com.example.motionhack_2023_kel_5.R
import com.example.motionhack_2023_kel_5.databinding.ActivityCreatorProfileBinding
import com.example.motionhack_2023_kel_5.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class CreatorProfileActivity : AppCompatActivity() {
    private lateinit var creatorId:String
    private lateinit var creatorName:String
    private lateinit var creatorPicture:String

    private lateinit var binding: ActivityCreatorProfileBinding
    private lateinit var viewPagerAdapter: CPFragmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreatorProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgCreatorProfileBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        getCreatorInformationFromIntent()

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

    private fun getCreatorInformationFromIntent() {
        val intent = intent
        creatorId = intent.getStringExtra(DiscoverFragment.Creator_ID)!!
        creatorName = intent.getStringExtra(DiscoverFragment.Creator_Name)!!
        creatorPicture = intent.getStringExtra(DiscoverFragment.Creator_Picture)!!
    }
}