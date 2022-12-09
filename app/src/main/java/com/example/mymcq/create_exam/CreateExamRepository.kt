package com.example.mymcq.create_exam

import com.example.mymcq.room.ExamDao
import com.example.mymcq.room.Questions
import kotlinx.coroutines.flow.Flow

class CreateExamRepository
constructor(
    private val dao: ExamDao
) {

    fun getQuestions(): Flow<List<Questions>> {
        return dao.getQuestions()
    }

}