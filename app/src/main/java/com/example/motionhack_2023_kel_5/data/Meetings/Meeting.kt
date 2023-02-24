package com.example.motionhack_2023_kel_5.data.Meetings

data class Meeting(
    val creator: Creator,
    val creatorId: String,
    val description: String,
    val endAt: String,
    val id: String,
    val isPrivate: Boolean,
    val price: Int,
    val slots: Int,
    val startAt: String,
    val title: String
)