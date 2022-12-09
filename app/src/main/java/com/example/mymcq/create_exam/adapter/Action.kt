package com.example.mymcq.create_exam.adapter

import com.example.mymcq.room.Questions

class Action {
    companion object{
        val DEFAULT= -1
        val CLICK_QUESTIONS= 1
        val ERROR=2
    }

    var action:Int =0
    var error: String= ""
    lateinit var questions: Questions

    constructor(action: Int) {
        this.action = action
    }

    constructor(action: Int, error: String) {
        this.action = action
        this.error = error
    }

    constructor(action: Int, questions: Questions) {
        this.action = action
        this.questions = questions
    }


}