package com.example.mymcq.attend_exam

import android.R
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import cn.pedant.SweetAlert.SweetAlertDialog

import com.example.mymcq.databinding.ActivityExamBinding
import kotlinx.android.synthetic.main.activity_exam.*


class ExamActivity : AppCompatActivity() {

    lateinit var viewModel: ExamViewModel
    lateinit var binding: ActivityExamBinding
    var currentQuestion = 1
    var score = 0
    var questionsAttended = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, com.example.mymcq.R.layout.activity_exam)
        viewModel = ViewModelProvider(this)[ExamViewModel::class.java]
        binding.viewModel = viewModel


        viewModel.tvQuestionNumber.set("Q. " + currentQuestion.toString())
        viewModel.getQuestionById(currentQuestion)
        viewModel.clearExam()


        button_next.setOnClickListener {

            if (currentQuestion <= 15) {
                if (viewModel.isAnswerSelected.get() == false) {
                    Toast.makeText(this, "please select an answer", Toast.LENGTH_SHORT).show()
                } else {
                    currentQuestion++
                    viewModel.getQuestionById(currentQuestion)
                }
            }

        }

        buttonSubmitExam.setOnClickListener {
            if (questionsAttended < 15) {
                Toast.makeText(
                    this,
                    "Please answer all question before submitting",
                    Toast.LENGTH_SHORT
                ).show()
            } else {

                showResult()

            }
        }

        button_previous.setOnClickListener {
            currentQuestion--
            if (currentQuestion < 1) {
                currentQuestion = 1
            }
            if (currentQuestion > 0) {
                viewModel.tvQuestionNumber.set("Q. $currentQuestion")
                viewModel.getQuestionById(currentQuestion)
                if (viewModel.isItemAdded(currentQuestion)) {
                    viewModel.getExamDetailsById(currentQuestion)
                }

            }
        }
        viewModel.optionSelected.observe(this, Observer {

            viewModel.isAnswerSelected.set(true)

            viewModel.currentQuestion.get()?.let { it1 -> viewModel.insertExam(it1, it) }

        })

        viewModel.getExamData().observe(this, Observer {

            score = 0
            questionsAttended = 0
            for (i in it.indices) {
                if (it[i].selectedAnswer == it[i].answer) {
                    score++
                }

                if (it[i].selectedAnswer != "") {
                    questionsAttended++
                }
            }

        })
        viewModel.examDetails.observe(this, Observer {

            if (it != null) {
                if (it.questionId == currentQuestion) {
                    viewModel.setAnswer(it)
                }
            }
        })




        viewModel.question.observe(this, Observer {
            if(it!=null) {
                viewModel.currentQuestion.set(it)
                viewModel.optionSelect()

                viewModel.tvOptionA.set(it.optionA)
                viewModel.tvOptionB.set(it.optionB)
                viewModel.tvOptionC.set(it.optionC)
                viewModel.tvOptionD.set(it.optionD)
                viewModel.tvQuestion.set(it.question)
                viewModel.tvQuestionNumber.set(currentQuestion.toString())

                viewModel.getExamDetailsById(currentQuestion)
            }
        })
    }

    private fun showResult() {
//
        val dialog = SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
        dialog.setTitleText("Exam completed Successfully !")
        dialog.setContentText("total score is $score")
        dialog.setConfirmText("OK")
        //dialog.setCustomView()
        dialog.setConfirmClickListener { sDialog ->
            sDialog.dismissWithAnimation()
            finish()
        }
        dialog.show()

    }

}