package com.example.mymcq.home

import android.annotation.SuppressLint
import androidx.annotation.WorkerThread
import com.example.mymcq.room.ExamDao
import com.example.mymcq.room.Questions

class HomeRepository
constructor(
    private val dao: ExamDao
) {
    @SuppressLint("SuspiciousIndentation")
    @SuppressWarnings
    @WorkerThread
    suspend fun insertQuestion(questions: List<Questions>?) {
        if (questions != null) {
            for (question in questions) {
                dao.insertQuestions(question)
            }
        }
    }

}