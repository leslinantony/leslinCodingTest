package com.example.mymcq.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mymcq.DataBinderMapperImpl
import com.example.mymcq.R
import com.example.mymcq.attend_exam.ExamActivity
import com.example.mymcq.create_exam.CreateExamActivity
import com.example.mymcq.databinding.ActivityEditQuestionsBinding
import com.example.mymcq.databinding.ActivityHomeBinding
import kotlinx.android.synthetic.main.activity_exam.*
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    lateinit var viewModel: HomeViewModel
    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_home)
        viewModel= ViewModelProvider(this)[HomeViewModel::class.java]
        binding.viewModel=viewModel



        buttonAttendExam.setOnClickListener{

            val intent= Intent( this, ExamActivity::class.java)
            startActivity(intent)
        }

        buttonCreateExam.setOnClickListener{

            val intent= Intent( this, CreateExamActivity::class.java)
            startActivity(intent)
        }
    }
}