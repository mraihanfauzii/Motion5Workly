package com.example.motionhack_2023_kel_5.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.motionhack_2023_kel_5.LoginActivity
import com.example.motionhack_2023_kel_5.R

class Onboarding1Activity : AppCompatActivity() {

    lateinit var btnNext: TextView
    var btnSkip: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding1)

        btnNext = findViewById(R.id.btn_next)
//        btnSkip = findViewById(R.id.btn_Skip1)

        btnNext.setOnClickListener {
            startActivity(Intent(this, Onboarding2Activity::class.java))
        }

        btnSkip?.setOnClickListener {
            finishAffinity()
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}