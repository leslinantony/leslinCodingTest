package com.example.mymcq.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "examTable")
data class Exam(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "questionId")
    var questionId: Int,

    @ColumnInfo(name = "question")
    var question: String?,

    @ColumnInfo(name = "optionA")
    var optionA: String?,

    @ColumnInfo(name = "optionB")
    var optionB: String?,

    @ColumnInfo(name = "optionC")
    var optionC: String?,

    @ColumnInfo(name = "optionD")
    var optionD: String?,

    @ColumnInfo(name = "answer")
    var answer: String?,

    @ColumnInfo(name = "selectedAnswer")
    var selectedAnswer: String?,
)