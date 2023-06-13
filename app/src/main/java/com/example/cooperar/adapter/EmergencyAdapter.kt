package com.example.cooperar.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cooperar.databinding.CardEmergencyBinding

class EmergencyAdapter() :
    RecyclerView.Adapter<EmergencyAdapter.MyViewHolder>() {

    class MyViewHolder(private val binding: CardEmergencyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var itemTitle: TextView = binding.emergencyCardTitleTv
        var itemAddress: TextView = binding.emergencyCardAddressTv
    }

    // 1. Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EmergencyAdapter.MyViewHolder {
        val binding =
            CardEmergencyBinding.inflate(LayoutInflater.from(parent.context), parent, false)


        return MyViewHolder(binding)
    }

    // 2. Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemTitle.setText("타이틀입니다")
        holder.itemAddress.setText("주소입니다.")
    }

    // 3. Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return 5
    }
}