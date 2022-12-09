package com.example.mymcq.create_exam.adapter

import android.view.View
import androidx.databinding.BaseObservable
import androidx.lifecycle.MutableLiveData
import com.example.mymcq.room.Questions

class QuestionsRowViewModel(val questions: Questions, var mAction: MutableLiveData<Action>) : BaseObservable() {

    var question: String? = questions.question
    var questionNumber: String = "Q ${questions.questionId.toString()} ."

    fun clickQuestion(view: View)
    {
        mAction.value= Action(Action.CLICK_QUESTIONS,questions)
    }

}