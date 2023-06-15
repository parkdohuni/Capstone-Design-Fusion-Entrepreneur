package com.example.cooperar.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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

        binding.detailCallTv.setOnClickListener {
            val permission = android.Manifest.permission.CALL_PHONE
            val requestCode = 1

            if (ContextCompat.checkSelfPermission(requireContext(), permission) == PackageManager.PERMISSION_GRANTED) {
                // 이미 권한이 허용되었을 경우 전화를 걸 수 있습니다.
                makePhoneCall()
            } else {
                // 권한이 허용되지 않았을 경우 권한을 요청합니다.
                ActivityCompat.requestPermissions(requireActivity(), arrayOf(permission), requestCode)
            }
        }
        binding.detailApplyTv.setOnClickListener {
            val dlg = MyDialog(requireActivity() as AppCompatActivity, navController)
            dlg.showDialog()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
    private fun makePhoneCall() {
        val tel = "tel:01056986965"
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse(tel))

        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            startActivity(intent)
        }
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