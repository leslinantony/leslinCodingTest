package com.example.mymcq.create_exam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymcq.R
import com.example.mymcq.create_exam.adapter.QuestionsAdapter
import com.example.mymcq.databinding.ActivityCreateExamBinding
import com.example.mymcq.edit_questions.EditQuestionsActivity

class CreateExamActivity : AppCompatActivity() {

    lateinit var viewModel: CreateExamViewModel
    lateinit var binding:ActivityCreateExamBinding
    lateinit var adapter: QuestionsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_create_exam)
        viewModel= ViewModelProvider(this).get(CreateExamViewModel::class.java)
        binding.viewModel=viewModel

        Toast.makeText(this, "Please click on the questions you want to edit!", Toast.LENGTH_LONG).show()

        setRecyclerView(binding.rvQuestions)

        viewModel.getQuestions().observe(this, Observer {

            adapter.setData(it)
            adapter.notifyDataSetChanged()

        })

    }

    override fun onResume() {
        super.onResume()
        viewModel.getQuestions()
    }

    private fun setRecyclerView(rvQuestions: RecyclerView) {

        rvQuestions.setHasFixedSize(true)
        rvQuestions.layoutManager= LinearLayoutManager(this)
        adapter= QuestionsAdapter()
        rvQuestions.adapter= adapter
        setObservable(adapter)

    }

    private fun setObservable(adapter: QuestionsAdapter) {

        adapter.mAction.observe(this, Observer {

            var intent= Intent(this, EditQuestionsActivity::class.java)
            intent.putExtra("questionId",it.questions.questionId)
            startActivity(intent)

        })

    }
}