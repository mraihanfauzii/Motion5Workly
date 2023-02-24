package com.example.motionhack_2023_kel_5.data.Meetings

data class Creator(
    val id: String,
    val isVerified: Boolean,
    val name: String,
    val professions: List<Profession>,
    val profilePhoto: String,
    val username: String
)