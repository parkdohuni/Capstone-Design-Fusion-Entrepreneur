package com.example.cooperar.ui

import android.app.Dialog
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.cooperar.R
import com.example.cooperar.data.MypageData
import com.example.cooperar.databinding.DialogBinding
import com.example.cooperar.databinding.FragmentMatchingBinding
import com.google.firebase.firestore.FirebaseFirestore

class MyDialog (private val context: AppCompatActivity, private val navController: NavController, private val mypageData: MypageData) {

    private lateinit var binding : DialogBinding
    private val dlg = Dialog(context)   //부모 액티비티의 context 가 들어감

    fun showDialog(){
        binding = DialogBinding.inflate(context.layoutInflater)
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)   //타이틀바 제거
        dlg.setContentView(binding.root)     //다이얼로그에 사용할 xml 파일을 불러옴
        dlg.setCancelable(false)    //다이얼로그의 바깥 화면을 눌렀을 때 다이얼로그가 닫히지 않도록 함

        //ok 버튼 동작
        binding.dialogApplyTv.setOnClickListener {
            // 파이어베이스 Firestore에 데이터 저장
            val db = FirebaseFirestore.getInstance()
            db.collection("mypage")
                .add(mypageData)
                .addOnSuccessListener { documentReference ->
                    // 저장 성공 시 동작
                }
                .addOnFailureListener { e ->
                    // 저장 실패 시 동작
                }
            navController.navigate(R.id.action_detailFragment_to_mypageUserFragment)

            dlg.dismiss()
        }

        //cancel 버튼 동작
        binding.dialogCancelTv.setOnClickListener {
            dlg.dismiss()
        }

        dlg.show()
    }

}