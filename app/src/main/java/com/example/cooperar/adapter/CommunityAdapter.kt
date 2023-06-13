package com.example.cooperar.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cooperar.databinding.CommunityCardBinding

class CommunityAdapter() :
    RecyclerView.Adapter<CommunityAdapter.MyViewHolder>() {

    class MyViewHolder(private val binding: CommunityCardBinding) :
        RecyclerView.ViewHolder(binding.root) {


        var itemCategory: TextView = binding.categoryTv
        var itemTitle: TextView = binding.itemTitleTv

    }

    // 1. Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding = CommunityCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)


        return MyViewHolder(binding)
    }

    // 2. Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemTitle.text = "타이틀입니다"
        holder.itemCategory.text = "세부사항 입니다"
    }

    // 3. Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return 5
    }
}