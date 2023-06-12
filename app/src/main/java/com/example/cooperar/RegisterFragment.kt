package com.example.cooperar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.cooperar.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class RegisterFragment: Fragment() {
    /* View */
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var navController: NavController
    private lateinit var onBackPressedCallback: OnBackPressedCallback
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)
        // FirebaseAuth 인스턴스를 가져옵니다.
        auth = FirebaseAuth.getInstance()
        /* Bottom Menu */
        val bottomMenu = (requireActivity() as MainActivity).binding.bottomNav
        bottomMenu.visibility = View.GONE

        val toolbarBackBtn = (requireActivity() as MainActivity).binding.toolbarBackBtn
        toolbarBackBtn.visibility = View.GONE
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        binding.registerBtn.setOnClickListener {
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
                        Toast.makeText(requireContext(), exception.message, Toast.LENGTH_LONG).show()
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