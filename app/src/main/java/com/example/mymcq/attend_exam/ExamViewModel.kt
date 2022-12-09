package com.example.mymcq.attend_exam

import android.app.Application
import android.view.View
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mymcq.application.MyApplication
import com.example.mymcq.room.Exam
import com.example.mymcq.room.Questions
import kotlinx.coroutines.*

class ExamViewModel(application: Application) : AndroidViewModel(application) {

    val repository = (application as MyApplication).examRepository
    var questions = MutableLiveData<List<Questions>>()
    var question = MutableLiveData<Questions>()
    var examData = MutableLiveData<List<Exam>>()
    var examDetails = MutableLiveData<Exam?>()
    var currentQuestion: ObservableField<Questions> = ObservableField()
    var currentExamData: ObservableField<Exam> = ObservableField()
    var optionSelected = MutableLiveData<String>()

    var exam: ObservableArrayList<Exam> = ObservableArrayList()
    var isAnswerSelected = ObservableField(false)


    var job: Job? = null
    var optionASelected = ObservableField(View.GONE)
    var optionBSelected = ObservableField(View.GONE)
    var optionCSelected = ObservableField(View.GONE)
    var optionDSelected = ObservableField(View.GONE)


    var tvOptionA = ObservableField("")
    var tvOptionB = ObservableField("")
    var tvOptionC = ObservableField("")
    var tvOptionD = ObservableField("")
    var tvQuestion = ObservableField("")
    var tvQuestionNumber = ObservableField("")


    fun getQuestions(): LiveData<List<Questions>> {
        CoroutineScope(Dispatchers.Main).launch {
            if (repository != null) {

                var response = repository.getQuestions()
                response.collect {
                    questions.value = it
                }
            }
        }
        return questions
    }


    fun getExamData(): LiveData<List<Exam>> {
        CoroutineScope(Dispatchers.Main).launch {
            if (repository != null) {

                var response = repository.getExamData()
                response.collect {
                    examData.value = it
                }
            }
        }
        return examData
    }

    fun getQuestionById(id: Int): LiveData<List<Questions>> {
        CoroutineScope(Dispatchers.Main).launch {
            if (repository != null) {

                var response = repository.getQuestionById(id)
                response.collect {
                    question.value = it
                }
            }
        }
        return questions
    }

    fun getExamDetailsById(id: Int): MutableLiveData<Exam?> {
        CoroutineScope(Dispatchers.Main).launch {
            if (repository != null) {

                var response = repository.getExamDEtailsById(id)
                response.collect {
                    examDetails.value = it
                }
            }
        }
        return examDetails
    }






    fun clearExam() {
        job = CoroutineScope(Dispatchers.IO).launch {

            repository.clearExam()
        }


    }

    fun insertExam(question: Questions, s: String) {
        var selectedAnswer = ""

        when (s) {
            "a" -> {
                selectedAnswer = question.optionA.toString()
            }
            "b" -> {
                selectedAnswer = question.optionB.toString()
            }
            "c" -> {
                selectedAnswer = question.optionC.toString()
            }
            "d" -> {
                selectedAnswer = question.optionD.toString()
            }
        }

        var examData = Exam(
            questionId = question.questionId,
            question = question.question,
            optionA = question.optionA,
            optionB = question.optionB,
            optionC = question.optionC,
            optionD = question.optionD,
            answer = question.answer,
            selectedAnswer = selectedAnswer
        )


        job = CoroutineScope(Dispatchers.IO).launch {

            if (repository.isIteAdded(question.questionId)) {
                repository.updateExam(question.questionId, selectedAnswer)
            } else {
                repository.insertExam(examData)
            }

        }

    }

    fun isItemAdded(questionId: Int): Boolean {
        var result = false
        job = CoroutineScope(Dispatchers.IO).launch {

            result = repository.isIteAdded(questionId)


        }
        return result
    }


    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

    fun clickOptionA(view: View) {
        optionSelect()
        optionSelected.value = "a"

        optionASelected.set(View.VISIBLE)
    }

    fun clickOptionB(view: View) {
        optionSelect()
        optionSelected.value = "b"

        optionBSelected.set(View.VISIBLE)
    }

    fun clickOptionC(view: View) {
        optionSelect()
        optionSelected.value = "c"

        optionCSelected.set(View.VISIBLE)
    }

    fun clickOptionD(view: View) {
        optionSelect()
        optionSelected.value = "d"

        optionDSelected.set(View.VISIBLE)
    }

    fun optionSelect() {

        isAnswerSelected.set(false)
        optionASelected.set(View.GONE)
        optionBSelected.set(View.GONE)
        optionCSelected.set(View.GONE)
        optionDSelected.set(View.GONE)

    }

    fun setAnswer(exam: Exam) {

        isAnswerSelected.set(true)
        when(exam.selectedAnswer)
        {
            exam.optionA->{
                optionASelected.set(View.VISIBLE)
                optionBSelected.set(View.GONE)
                optionCSelected.set(View.GONE)
                optionDSelected.set(View.GONE)
            }
            exam.optionB->{
                optionBSelected.set(View.VISIBLE)
                optionASelected.set(View.GONE)
                optionCSelected.set(View.GONE)
                optionDSelected.set(View.GONE)
            }
            exam.optionC->{
                optionCSelected.set(View.VISIBLE)
                optionASelected.set(View.GONE)
                optionBSelected.set(View.GONE)
                optionDSelected.set(View.GONE)
            }
            exam.optionD->{

                optionASelected.set(View.GONE)
                optionBSelected.set(View.GONE)
                optionCSelected.set(View.GONE)
                optionDSelected.set(View.VISIBLE)
            }
        }
    }

}