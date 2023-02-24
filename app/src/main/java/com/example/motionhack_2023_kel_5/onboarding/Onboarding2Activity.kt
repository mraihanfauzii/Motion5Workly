package com.example.motionhack_2023_kel_5.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.motionhack_2023_kel_5.LoginActivity
import com.example.motionhack_2023_kel_5.R

class Onboarding2Activity : AppCompatActivity() {

//    var btnNext: Button? = null
//    var btnBack: Button? = null
//    var btnSkip: Button? = null
    var btnStart: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding2)

//        btnNext = findViewById(R.id.btn_Next2)
//        btnBack = findViewById(R.id.btn_Back1)
        btnStart = findViewById(R.id.btn_Start)

//        btnNext?.setOnClickListener {
//            startActivity(Intent(this, Onboarding3Activity::class.java))
//        }

//        btnBack?.setOnClickListener {
//            startActivity(Intent(this, Onboarding1Activity::class.java))
//        }
        btnStart?.setOnClickListener {
            finishAffinity()
            startActivity(Intent(this, LoginActivity::class.java))
        }

    }
}