package com.example.motionhack_2023_kel_5.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.motionhack_2023_kel_5.data.Creators.Creator
import com.example.motionhack_2023_kel_5.data.Meetings.Meeting
import com.example.motionhack_2023_kel_5.data.Professions.Profession
import com.example.motionhack_2023_kel_5.databinding.CreatorlistBinding

class CreatorListAdapter():RecyclerView.Adapter<CreatorListAdapter.CreatorListViewHolder>() {
    lateinit var onItemClick:((Creator) -> Unit)
    private var creatorsList = ArrayList<Creator>()

    fun setCreators(creatorsList:ArrayList<Creator>){
        this.creatorsList = creatorsList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreatorListViewHolder {
        return CreatorListViewHolder(CreatorlistBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CreatorListViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(creatorsList[position].profilePhoto)
            .into(holder.binding.ivCreator)

        holder.itemView.setOnClickListener {
            onItemClick.invoke(creatorsList[position])
        }
    }

    override fun getItemCount(): Int {
        return creatorsList.size
    }

    class CreatorListViewHolder(val binding: CreatorlistBinding):RecyclerView.ViewHolder(binding.root)
}