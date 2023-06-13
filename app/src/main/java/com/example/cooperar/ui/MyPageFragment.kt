package com.example.cooperar.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cooperar.MainActivity
import com.example.cooperar.adapter.EmergencyAdapter
import com.example.cooperar.adapter.MypageAdapter
import com.example.cooperar.adapter.MypageManagementAdapter
import com.example.cooperar.databinding.FragmentMypageBinding

class MyPageFragment: Fragment() {
    /* View */
    private lateinit var binding: FragmentMypageBinding
    private lateinit var navController: NavController
    private lateinit var onBackPressedCallback: OnBackPressedCallback
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentMypageBinding.inflate(layoutInflater, container, false)
        /* Bottom Menu */
        val bottomMenu = (requireActivity() as MainActivity).binding.bottomNav
        bottomMenu.visibility = View.VISIBLE

        val toolbar = (requireActivity() as MainActivity).binding.toolbar
        toolbar.visibility = View.VISIBLE

        // 1
        viewManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, true)
        // 2
        viewAdapter = MypageAdapter()
        recyclerView = binding.processRv.apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)
            // use a linear layout manager
            layoutManager = viewManager
            // specify an viewAdapter (see also next example)
            adapter = viewAdapter
        }

        // 1
        viewManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, true)
        // 2
        viewAdapter = MypageManagementAdapter()
        recyclerView = binding.notProcessRv.apply {
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

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}