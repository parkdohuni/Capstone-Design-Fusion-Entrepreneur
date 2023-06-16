package com.example.cooperar.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.cooperar.MainActivity
import com.example.cooperar.R
import com.example.cooperar.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore

class RegisterFragment : Fragment() {
    /* View */
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var navController: NavController
    private lateinit var onBackPressedCallback: OnBackPressedCallback
    private lateinit var auth: FirebaseAuth
    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)
        // FirebaseAuth 인스턴스를 가져옵니다.
        auth = FirebaseAuth.getInstance()
        /* Bottom Menu */
        val bottomMenu = (requireActivity() as MainActivity).binding.bottomNav
        bottomMenu.visibility = View.GONE

        val toolbar = (requireActivity() as MainActivity).binding.toolbar
        toolbar.visibility = View.GONE
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        val sData = resources.getStringArray(R.array.my_array)
        val adapter =
            ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, sData)
        binding.spinner.adapter = adapter

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position != 0) {
                    val selectedString = sData[position]
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                // 아무 동작 없음
            }
        }
        binding.registerBtn.setOnClickListener {
            val id = binding.idEt.text.toString()
            val password = binding.pwEt.text.toString()
            val name = binding.nameEt.text.toString()
            val selectedSpinnerItem = binding.spinner.selectedItem.toString()
            val profileUpdates = userProfileChangeRequest {
                displayName = name
            }
            auth.currentUser!!.updateProfile(profileUpdates)

            // Firebase Firestore에 데이터 저장
            val user = hashMapOf(
                "id" to id,
                "password" to password,
                "name" to name,
                "selectedSpinnerItem" to selectedSpinnerItem

            )
            db.collection("users")
                .add(user)
                .addOnSuccessListener { documentReference ->
                    // 저장 성공 시 동작
                    // documentReference.id를 사용하여 새로 생성된 문서의 ID에 접근할 수 있음
                }
                .addOnFailureListener { e ->
                    // 저장 실패 시 동작
                }
            signUp(binding.idEt.text.toString(), binding.pwEt.text.toString())

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    // 회원가입 함수
    fun signUp(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Firebase DB에 저장되어 있는 계정이 아닐 경우
                    // 입력한 계정을 새로 등록한다
                    goToMainActivity(task.result?.user)
                    Toast.makeText(requireContext(), "회원 가입에 성공했습니다.", Toast.LENGTH_LONG).show()
                } else {
                    val exception = task.exception
                    if (exception != null) {
                        Toast.makeText(requireContext(), exception.message, Toast.LENGTH_LONG)
                            .show()
                    } else {
                        Toast.makeText(requireContext(), "회원 가입에 실패했습니다.", Toast.LENGTH_LONG).show()
                    }
                }
            }
    }

    // 회원가입 및 로그인 성공 시 메인 화면으로 이동하는 함수
    fun goToMainActivity(user: FirebaseUser?) {
        // Firebase에 등록된 계정일 경우에만 메인 화면으로 이동
        if (user != null) {
            navController.navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }
}