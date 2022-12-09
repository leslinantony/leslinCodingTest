package com.example.mymcq.attend_exam

import android.annotation.SuppressLint
import androidx.annotation.WorkerThread
import com.example.mymcq.room.Exam
import com.example.mymcq.room.ExamDao
import com.example.mymcq.room.Questions
import kotlinx.coroutines.flow.Flow

class ExamRepository
constructor(
    private val dao: ExamDao
)
{

    fun getQuestions(): Flow<List<Questions>> {
        return dao.getQuestions()
    }

    fun getExamData(): Flow<List<Exam>> {
        return dao.getExamData()
    }

    fun getQuestionById(id:Int): Flow<Questions> {
        return dao.getQuestionById(id)
    }

    fun getExamDEtailsById(id:Int): Flow<Exam> {
        return dao.getExamDetailsById(id)
    }



    fun clearExam() {
        return dao.clearExam()
    }

    fun isIteAdded(id:Int): Boolean {
        return dao.isItemAdded(id)
    }



    @SuppressLint("SuspiciousIndentation")
    @SuppressWarnings
    @WorkerThread
    suspend fun insertExam(exam: Exam?) {
        if (exam != null) {

                dao.insertExam(exam)

        }
    }


    @SuppressLint("SuspiciousIndentation")
    @SuppressWarnings
    @WorkerThread
    suspend fun updateExam(questionId: Int, answer: String) {

                dao.updateExam(questionId,answer)


    }
}