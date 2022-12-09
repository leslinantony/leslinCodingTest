package com.example.mymcq.edit_questions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mymcq.R
import com.example.mymcq.databinding.ActivityEditQuestionsBinding

class EditQuestionsActivity : AppCompatActivity() {
    lateinit var viewModel: EditQuestionsViewModel
    lateinit var binding:ActivityEditQuestionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_edit_questions)
        viewModel= ViewModelProvider(this)[EditQuestionsViewModel::class.java]
        binding.viewModel=viewModel

        var intent = intent
        viewModel.questionId.set(intent.getIntExtra("questionId",0))
        if (viewModel.questionId.get()!=0)
        {
            viewModel.questionId.get()?.let { viewModel.getQuestionById(it).observe(
                this, Observer {

                    viewModel.setData(it)
                    viewModel.currentQuestion.set(it)
                }
            ) }
        }

    }
}