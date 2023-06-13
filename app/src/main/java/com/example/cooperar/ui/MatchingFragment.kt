package com.example.cooperar.ui

import MatchingAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cooperar.MainActivity
import com.example.cooperar.R
import com.example.cooperar.databinding.FragmentMatchingBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class MatchingFragment: Fragment() {
    /* View */
    private lateinit var binding: FragmentMatchingBinding
    private lateinit var navController: NavController
    private lateinit var onBackPressedCallback: OnBackPressedCallback
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private var auth: FirebaseAuth? = null
    private var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private var storage: FirebaseStorage = FirebaseStorage.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentMatchingBinding.inflate(layoutInflater, container, false)
        /* Bottom Menu */
        val bottomMenu = (requireActivity() as MainActivity).binding.bottomNav
        bottomMenu.visibility = View.VISIBLE

        val toolbar = (requireActivity() as MainActivity).binding.toolbar
        toolbar.visibility = View.VISIBLE
        // 1
        viewManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, true)
        // 2
        viewAdapter = MatchingAdapter()
        recyclerView = binding.matchingRv.apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)
            // use a linear layout manager
            layoutManager = viewManager
            // specify an viewAdapter (see also next example)
            adapter = viewAdapter
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        binding.emergencyIv.setOnClickListener {
            navController.navigate(R.id.action_matchingFragment_to_emergencyFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}