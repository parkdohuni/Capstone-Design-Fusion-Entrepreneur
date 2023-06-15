package com.example.cooperar.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cooperar.data.EmergencyData
import com.example.cooperar.data.MatchingData
import com.example.cooperar.databinding.CardEmergencyBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class EmergencyAdapter : RecyclerView.Adapter<EmergencyAdapter.MyViewHolder>() {
    var emergencyList: ArrayList<EmergencyData> = arrayListOf()

    /* User Authentication */
    private var auth: FirebaseAuth? = null
    private var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private var storage: FirebaseStorage = FirebaseStorage.getInstance()

    init {
        auth = FirebaseAuth.getInstance()

        firestore.collection("emergency").addSnapshotListener { querySnapshot, _ ->

            // ArrayList 비워줌
            emergencyList.clear()

            querySnapshot?.let { snapshot ->
                for (document in snapshot.documents) {
                    val item = document.toObject(EmergencyData::class.java)
                    item?.let {
                        emergencyList.add(it)
                    }
                }
            }
            Log.e("match match match", emergencyList.toString())
            notifyDataSetChanged()
        }
    }

    class MyViewHolder(private val binding: CardEmergencyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var itemTitle: TextView = binding.emergencyCardTitleTv
        var itemAddress: TextView = binding.emergencyCardAddressTv
        var itemImage: ImageView = binding.emergencyCardIv

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
        val emergency: EmergencyData = emergencyList[position]
        holder.itemTitle.text = emergency.title
        holder.itemAddress.text = emergency.address
        // 이미지를 Glide를 사용하여 가져와서 설정합니다.
        Glide.with(holder.itemView.context)
            .load(emergency.imageUrl)
            .override(600, 600) // 이미지의 크기를 원하는 크기로 조정
            .fitCenter() // 이미지를 디바이스 화면에 맞게 조정
            .into(holder.itemImage)
    }

    // 3. Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return emergencyList.size
    }
}