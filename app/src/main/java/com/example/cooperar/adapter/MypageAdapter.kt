package com.example.cooperar.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cooperar.databinding.CardMatchingBinding
import com.example.cooperar.databinding.CardMypageBinding

class MypageAdapter() :
    RecyclerView.Adapter<MypageAdapter.MyViewHolder>() {

    class MyViewHolder(private val binding: CardMypageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var itemTitle: TextView = binding.mypageTitleTv
        var itemTodo: TextView = binding.mypageTodoListTv
    }

    // 1. Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MypageAdapter.MyViewHolder {
        val binding =
            CardMypageBinding.inflate(LayoutInflater.from(parent.context), parent, false)


        return MyViewHolder(binding)
    }

    // 2. Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemTitle.text = "가게 이름 하나 둘"
        holder.itemTodo.text = "업무 등등 등등 등등."
    }

    // 3. Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return 1
    }
}