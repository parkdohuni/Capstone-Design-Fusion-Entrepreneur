package com.example.cooperar.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cooperar.data.CommunityData
import com.example.cooperar.data.MatchingData
import com.example.cooperar.databinding.CardCommunityBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class CommunityAdapter : RecyclerView.Adapter<CommunityAdapter.MyViewHolder>() {
    var communityList : ArrayList<CommunityData> = arrayListOf()
    /* User Authentication */
    private var auth: FirebaseAuth? = null
    private var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private var storage: FirebaseStorage = FirebaseStorage.getInstance()

    init {
        auth = FirebaseAuth.getInstance()

        firestore.collection("community").addSnapshotListener { querySnapshot, _ ->

            // ArrayList 비워줌
            communityList.clear()

            querySnapshot?.let { snapshot ->
                for (document in snapshot.documents) {
                    val item = document.toObject(CommunityData::class.java)
                    item?.let {
                        communityList.add(it)
                    }
                }
            }
            Log.e("match match match", communityList.toString())
            notifyDataSetChanged()
        }
    }
    class MyViewHolder(private val binding: CardCommunityBinding) :
        RecyclerView.ViewHolder(binding.root) {


        var itemCategory: TextView = binding.categoryTv
        var itemTitle: TextView = binding.itemTitleTv

    }

    // 1. Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding = CardCommunityBinding.inflate(LayoutInflater.from(parent.context), parent, false)


        return MyViewHolder(binding)
    }

    // 2. Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val community: CommunityData = communityList[position]
        holder.itemTitle.text = community.title
        holder.itemCategory.text = community.category
    }

    // 3. Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return communityList.size
    }
}