package com.example.cooperar.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.cooperar.MainActivity
import com.example.cooperar.data.UserData
import com.example.cooperar.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User

class HomeFragment: Fragment() {
    /* View */
    private lateinit var binding: FragmentHomeBinding
    private lateinit var navController: NavController
    private lateinit var onBackPressedCallback: OnBackPressedCallback
    private lateinit var auth: FirebaseAuth
    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        /* Bottom Menu */
        val bottomMenu = (requireActivity() as MainActivity).binding.bottomNav
        bottomMenu.visibility = View.VISIBLE

        val toolbar = (requireActivity() as MainActivity).binding.toolbar
        toolbar.visibility = View.VISIBLE

        auth = FirebaseAuth.getInstance()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        val currentUser: FirebaseUser? = auth.currentUser
        val uid = currentUser?.uid
        val name = currentUser?.displayName
        binding.nameTv.text = "박도훈"
//        if (name.isNullOrEmpty()) {
//            if (uid != null) {
//                db.collection("users")
//                    .document(uid)
//                    .get()
//                    .addOnSuccessListener { documentSnapshot ->
//                        val user = documentSnapshot.toObject(UserData::class.java)
//                        user?.let { userData ->
//                            binding.nameTv.text = userData.name
//                        }
//                    }
//                    .addOnFailureListener { exception ->
//                        // 추가 정보 가져오기 실패 시 처리
//                    }
//            }
//        } else {
//            binding.nameTv.text = name
//        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}