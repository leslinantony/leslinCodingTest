package com.example.mymcq.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ExamDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestions (question: Questions) : Long

    @Query( "select * from questionsTable ")
     fun getQuestions() : Flow<List<Questions>>

     @Query( "select * from examTable ")
     fun getExamData() : Flow<List<Exam>>

     @Query( "select * from questionsTable where questionId=:questionId ")
     fun getQuestionById(questionId: Int) : Flow<Questions>

//    @Query("UPDATE questionsTable" +
//            " SET  question=:question and" +
//            " optionA=:optionA and " +
//            "optionB=:optionB and " +
//            "optionC=:optionC and " +
//            "optionD=:optionD and answer=:answer WHERE questionId = :questionId")
//     fun updateQuestion
//                (questionId: Int,
//                 question: String,
//                 optionA: String,
//                 optionB:String,
//                 optionC:String,
//                 optionD:String,
//                 answer: String)

    @Update
     fun updateQuestion(question: Questions)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExam (exam: Exam) : Long

    @Query("UPDATE examTable SET selectedAnswer=:answer WHERE questionId = :questionId")
    fun updateExam(questionId: Int, answer: String)

    @Query( "select * from examTable where questionId=:questionId ")
    fun getExamDetailsById(questionId: Int) : Flow<Exam>

    @Query("delete from examTable")
    fun clearExam()

    @Query("SELECT EXISTS (SELECT 1 FROM examTable WHERE questionId = :questionId)")
    fun isItemAdded(questionId: Int): Boolean


}