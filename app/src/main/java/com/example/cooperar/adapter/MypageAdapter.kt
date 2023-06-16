package com.example.cooperar.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.cooperar.data.MatchingData
import com.example.cooperar.data.MypageData
import com.example.cooperar.databinding.CardMatchingBinding
import com.example.cooperar.databinding.CardMypageBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MypageAdapter(private val parentFragment: Fragment) : RecyclerView.Adapter<MypageAdapter.MyViewHolder>() {
    var mypageyList: ArrayList<MypageData> = arrayListOf()
    /* User Authentication */
    private var auth: FirebaseAuth? = null
    private var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    init {
        auth = FirebaseAuth.getInstance()

        firestore.collection("mypage").addSnapshotListener { querySnapshot, _ ->

            // ArrayList 비워줌
            mypageyList.clear()

            querySnapshot?.let { snapshot ->
                for (document in snapshot.documents) {
                    val item = document.toObject(MypageData::class.java)
                    item?.let {
                        mypageyList.add(it)
                    }
                }
            }
            Log.e("match match match", mypageyList.toString())
            notifyDataSetChanged()
        }
    }
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
        val mypage: MypageData = mypageyList[position]
        holder.itemTitle.text = mypage.title
        holder.itemTodo.text = mypage.todo
    }

    // 3. Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return mypageyList.size
    }
}