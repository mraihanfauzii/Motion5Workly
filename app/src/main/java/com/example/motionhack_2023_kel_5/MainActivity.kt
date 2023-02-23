package com.example.motionhack_2023_kel_5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.motionhack_2023_kel_5.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    val firebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser!=null){
            navController = Navigation.findNavController(this, R.id.activity_main_nav_host_fragment)
            setupWithNavController(binding.bottomNavigationView, navController)
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}