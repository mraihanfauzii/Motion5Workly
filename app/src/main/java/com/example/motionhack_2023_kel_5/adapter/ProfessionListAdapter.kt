package com.example.motionhack_2023_kel_5.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.motionhack_2023_kel_5.data.Creators.Profession
import com.example.motionhack_2023_kel_5.databinding.MeetinglistBinding
import com.example.motionhack_2023_kel_5.databinding.ProfessionlistBinding

class ProfessionListAdapter(): RecyclerView.Adapter<ProfessionListAdapter.ProfessionListViewHolder>() {
    private var professionsList = ArrayList<Profession>()

    fun setProfessions(professionsList:ArrayList<Profession>){
        this.professionsList = professionsList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfessionListAdapter.ProfessionListViewHolder {
        return ProfessionListAdapter.ProfessionListViewHolder(
            ProfessionlistBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ProfessionListAdapter.ProfessionListViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(professionsList[position].name)
            .into(holder.binding.ivProfession)
    }

    override fun getItemCount(): Int {
        return professionsList.size
    }

    class ProfessionListViewHolder(val binding: ProfessionlistBinding):RecyclerView.ViewHolder(binding.root)
}