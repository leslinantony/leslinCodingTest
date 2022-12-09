package com.example.mymcq.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.mymcq.application.MyApplication
import com.example.mymcq.room.Questions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    var job: Job? = null
    val repository = (application as MyApplication).homeRepository

    fun insertQuestions() {


        var questions = listOf(
            Questions(1, "what is 1+1", "1", "2", "3", "1", "2"),
            Questions(2, "what is 2+3", "1", "2", "5", "2", "5"),
            Questions(3, "what is 3+3", "1", "6", "3", "33", "6"),
            Questions(4, "what is 4+3", "1", "7", "3", "5", "7"),
            Questions(5, "what is 5+3", "1", "8", "3", "7", "8"),
            Questions(6, "what is 6+3", "9", "2", "3", "3", "9"),
            Questions(7, "what is 7+3", "1", "2", "3", "7", "7"),
            Questions(8, "what is 8+3", "11", "2", "3", "8", "11"),
            Questions(9, "what is 9+3", "12", "11", "3", "6", "12"),
            Questions(10, "what is 2+3", "1", "2", "3", "5", "5"),
            Questions(11, "what is 7+3", "1", "10", "3", "5", "10"),
            Questions(12, "what is 2+3", "1", "2", "3", "5", "5"),
            Questions(13, "what is 8+3", "11", "2", "3", "5", "11"),
            Questions(14, "what is 2+1", "1", "2", "3", "5", "3"),
            Questions(15, "what is 2+3", "1", "2", "3", "5", "5"),
        )

        job = CoroutineScope(Dispatchers.IO).launch {

            repository.insertQuestion(questions)
        }

    }

}