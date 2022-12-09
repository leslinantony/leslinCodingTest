package com.example.mymcq.create_exam

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mymcq.application.MyApplication
import com.example.mymcq.room.Questions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CreateExamViewModel(application: Application) : AndroidViewModel(application) {

    var job: Job? = null
    val repository = (application as MyApplication).createExamRepository
    var questions = MutableLiveData<List<Questions>>()

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


}