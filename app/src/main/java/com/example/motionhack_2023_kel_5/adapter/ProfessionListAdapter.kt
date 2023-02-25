package com.example.motionhack_2023_kel_5.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.motionhack_2023_kel_5.data.Creators.Creator
import com.example.motionhack_2023_kel_5.data.Creators.Profession
import com.example.motionhack_2023_kel_5.databinding.ProfessionlistBinding

class ProfessionListAdapter(): RecyclerView.Adapter<ProfessionListAdapter.ProfessionListViewHolder>() {
    private var creatorsList = ArrayList<Creator>()

    fun setCreators(creatorsList:ArrayList<Creator>){
        this.creatorsList = creatorsList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfessionListViewHolder {
        return ProfessionListViewHolder(
            ProfessionlistBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ProfessionListViewHolder, position: Int) {
        holder.binding.tvProfession.text = creatorsList[position].professions[0].name
    }

    override fun getItemCount(): Int {
        return creatorsList.size
    }

    class ProfessionListViewHolder(val binding: ProfessionlistBinding):RecyclerView.ViewHolder(binding.root)
}