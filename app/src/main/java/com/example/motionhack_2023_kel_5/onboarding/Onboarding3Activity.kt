package com.example.motionhack_2023_kel_5.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.motionhack_2023_kel_5.LoginActivity
import com.example.motionhack_2023_kel_5.R

class Onboarding3Activity : AppCompatActivity() {

    var btnStart: Button? = null
    var btnBack: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding3)

//        btnStart = findViewById(R.id.btn_Start)
        btnBack = findViewById(R.id.btn_Back2)

        btnStart?.setOnClickListener {
            finishAffinity()
            startActivity(Intent(this, LoginActivity::class.java))
        }

        btnBack?.setOnClickListener {
            startActivity(Intent(this, Onboarding2Activity::class.java))
        }

    }
}