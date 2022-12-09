package com.example.mymcq.application

import android.app.Application
import com.example.mymcq.attend_exam.ExamRepository
import com.example.mymcq.create_exam.CreateExamRepository
import com.example.mymcq.edit_questions.EditQuestionRepository
import com.example.mymcq.home.HomeRepository
import com.example.mymcq.room.ExamDataBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MyApplication: Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { ExamDataBase.getDataBase(this, applicationScope) }
    val examRepository by lazy { ExamRepository(database.examDao(),)  }
    val createExamRepository by lazy { CreateExamRepository(database.examDao())  }
    val editQuestionsRepository by lazy { EditQuestionRepository(database.examDao())  }
    val homeRepository by lazy { HomeRepository(database.examDao())  }

}