package com.example.cooperar.ui

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.cooperar.MainActivity
import com.example.cooperar.data.MatchingData
import com.example.cooperar.databinding.FragmentDetailBinding

class DetailFragment: Fragment() {
    /* View */
    private lateinit var binding: FragmentDetailBinding
    private lateinit var navController: NavController
    private lateinit var onBackPressedCallback: OnBackPressedCallback

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        /* Bottom Menu */
        val bottomMenu = (requireActivity() as MainActivity).binding.bottomNav
        bottomMenu.visibility = View.GONE

        val toolbar = (requireActivity() as MainActivity).binding.toolbar
        toolbar.visibility = View.VISIBLE
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        val args: DetailFragmentArgs by navArgs()

        binding.detailTitleTv.text = args.item.title.toString()
        binding.detailAddressTv.text = args.item.address.toString()
        binding.detailMoneyTv.text = args.item.money.toString()
        binding.detailTodoTv.text = args.item.todo.toString()
        val imageUrl = args.item.imageUrl
        Glide.with(requireContext())
            .load(imageUrl)
            .override(600, 600)
            .fitCenter()
            .into(binding.detailIv)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
    companion object {
        private const val EXTRA_MATCHING_DATA = "extra_matching_data"

        fun newInstance(matchingData: MatchingData): DetailFragment {
            val fragment = DetailFragment()
            val args = Bundle()
            args.putParcelable(EXTRA_MATCHING_DATA, matchingData)
            fragment.arguments = args
            return fragment
        }
    }
}