package com.example.mymcq.edit_questions

import com.example.mymcq.room.ExamDao
import com.example.mymcq.room.Questions
import kotlinx.coroutines.flow.Flow

class EditQuestionRepository
constructor(
    private val dao: ExamDao
) {

    fun getQuestionById(id:Int): Flow<Questions> {
        return dao.getQuestionById(id)
    }

//    fun updateQuestions(questionId: Int,
//                        question: String,
//                        optionA: String,
//                        optionB:String,
//                        optionC:String,
//                        optionD:String,
//                        answer: String) {
//        return dao.updateQuestion(questionId=questionId,
//            question=question,
//            optionA=optionA,
//            optionB=optionB,
//            optionC=optionC,
//            optionD=optionD,
//            answer=answer)
//    }


    fun updateQuestions(question: Questions) {
        return dao.updateQuestion(question)
    }



}