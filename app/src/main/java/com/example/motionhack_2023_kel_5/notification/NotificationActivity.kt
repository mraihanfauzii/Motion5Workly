package com.example.motionhack_2023_kel_5.notification

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.viewpager.widget.ViewPager
import com.example.motionhack_2023_kel_5.HomeFragment
import com.example.motionhack_2023_kel_5.MainActivity
import com.example.motionhack_2023_kel_5.R
import com.example.motionhack_2023_kel_5.creatorProfile.CPFragmentAdapter
import com.google.android.material.tabs.TabLayout

class NotificationActivity : AppCompatActivity() {
    lateinit var imgNotificationBack : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        imgNotificationBack = findViewById(R.id.imgNotificationBack)

        imgNotificationBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        var viewPager = findViewById(R.id.notificationViewPager) as ViewPager
        var tabLayout = findViewById(R.id.notificationTabLayout) as TabLayout

        val fragmentAdapter = NotificationFragmentAdapter(supportFragmentManager)
        fragmentAdapter.addFragment(GeneralFragment(),"General")
        fragmentAdapter.addFragment(PurchaseFragment(),"Purchase")

        viewPager.adapter = fragmentAdapter
        tabLayout.setupWithViewPager(viewPager)
    }
}