package com.example.motionhack_2023_kel_5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.motionhack_2023_kel_5.onboarding.Onboarding1Activity
import com.google.firebase.auth.FirebaseAuth

class SplashScreenActivity : AppCompatActivity() {
    //Lama waktu splash screen muncul
    private val SPLASH_TIME_OUT:Long = 1500 //1.5 detik

    val firebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val firebaseUser = firebaseAuth.currentUser

        if (firebaseUser!=null){
            //Habis waktunya abis program dibawah ini bakal jalan
            Handler().postDelayed({
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }, SPLASH_TIME_OUT)
        } else {
            //Habis waktunya abis program dibawah ini bakal jalan
            Handler().postDelayed({
                startActivity(Intent(this, Onboarding1Activity::class.java))
                finish()
            }, SPLASH_TIME_OUT)
        }
    }
}