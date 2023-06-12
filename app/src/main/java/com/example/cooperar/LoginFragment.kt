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
import com.example.cooperar.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginFragment: Fragment() {
    /* View */
    private lateinit var binding: FragmentLoginBinding
    private lateinit var navController: NavController
    private lateinit var onBackPressedCallback: OnBackPressedCallback
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
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
        binding.registerTv.setOnClickListener {
            navController.navigate(R.id.action_loginFragment_to_registerFragment)
        }
        binding.loginBtn.setOnClickListener {
            signIn(binding.idEt.text.toString(), binding.pwEt.text.toString())
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
    // 로그인 함수
    fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // 로그인에 성공한 경우 메인 화면으로 이동
                    goToMainActivity(task.result?.user)
                } else {
                    // 로그인에 실패한 경우 Toast 메시지로 에러를 띄워준다
                    val exception = task.exception
                    if (exception != null) {
                        Toast.makeText(requireContext(), exception.message, Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(requireContext(), "로그인에 실패했습니다.", Toast.LENGTH_LONG).show()
                    }
                }
            }
    }
    // 회원가입 및 로그인 성공 시 메인 화면으로 이동하는 함수
    fun goToMainActivity(user: FirebaseUser?) {
        // Firebase에 등록된 계정일 경우에만 메인 화면으로 이동
        if (user != null) {
            navController.navigate(R.id.action_loginFragment_to_homeFragment)
        }
    }
}