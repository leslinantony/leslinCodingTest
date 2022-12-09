package com.example.mymcq.edit_questions

import android.app.Application
import android.content.Context
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.databinding.*
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mymcq.BR
import com.example.mymcq.application.MyApplication
import com.example.mymcq.room.Questions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class EditQuestionsViewModel(application: Application) : AndroidViewModel(application), Observable {
    var tvQuestionNumber = ObservableField("")
    var tvQuestion = ObservableField("")
    var optionA = ObservableField("")
    var optionB = ObservableField("")
    var optionC = ObservableField("")
    var optionD = ObservableField("")
    var answer = ObservableField("")


    var question = MutableLiveData<Questions>()
    var questionId = ObservableField(0)
    var job: Job? = null
    val repository = (application as MyApplication).editQuestionsRepository
    var selectedAnswer: Int = 0
    var answersList: ObservableArrayList<String> = ObservableArrayList()
    private val registry = PropertyChangeRegistry()
    var currentQuestion: ObservableField<Questions> = ObservableField()

    @JvmName("getSelectedAnswer1")
    @Bindable
    fun getSelectedAnswer(): Int {
        return selectedAnswer
    }

    @JvmName("setSelectedAnswer1")
    @Bindable
    fun setSelectedAnswer(selectedAnswer: Int) {
        this.selectedAnswer = selectedAnswer
        registry.notifyChange(this, BR.selectedAnswer1);

    }

    fun onSelectedAnswer(parent: AdapterView<*>?, view: View, pos: Int, id: Long) {

        if (pos != 0) {
            answer.set(answersList[pos])
        }

    }

    fun onOptionAChanged(
        s: CharSequence?, start: Int,
        before: Int, count: Int
    ) {
        answersList.clear()
        optionA.set(s.toString())
        answersList.add("--select an answer--")
        answersList.add(s.toString())
        answersList.add(optionB.get())
        answersList.add(optionC.get())
        answersList.add(optionD.get())
    }

    fun onOptionBChanged(
        s: CharSequence?, start: Int,
        before: Int, count: Int
    ) {
        answersList.clear()
        optionB.set(s.toString())
        answersList.add("--select an answer--")
        answersList.add(optionA.get())
        answersList.add(s.toString())
        answersList.add(optionC.get())
        answersList.add(optionD.get())
    }

    fun onOptionCChanged(
        s: CharSequence?, start: Int,
        before: Int, count: Int
    ) {
        answersList.clear()
        optionC.set(s.toString())
        answersList.add("--select an answer--")
        answersList.add(optionA.get())
        answersList.add(optionB.get())
        answersList.add(s.toString())
        answersList.add(optionD.get())
    }

    fun onOptionDChanged(
        s: CharSequence?, start: Int,
        before: Int, count: Int
    ) {
        answersList.clear()
        optionD.set(s.toString())
        answersList.add("--select an answer--")
        answersList.add(optionA.get())
        answersList.add(optionB.get())
        answersList.add(optionC.get())
        answersList.add(s.toString())
    }
  fun onQuestionChanged(
        s: CharSequence?, start: Int,
        before: Int, count: Int
    ) {
        tvQuestion.set(s.toString())
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        registry.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        registry.remove(callback)
    }

    fun clickSubmit(view: View) {
        if (optionA.get().equals("")) {
            Toast.makeText(view.context, "option A cannot be empty", Toast.LENGTH_SHORT).show()
        } else if (optionB.get().equals("")) {
            Toast.makeText(view.context, "option B cannot be empty", Toast.LENGTH_SHORT).show()
        } else if (optionC.get().equals("")) {
            Toast.makeText(view.context, "option C cannot be empty", Toast.LENGTH_SHORT).show()
        } else if (optionD.get().equals("")) {
            Toast.makeText(view.context, "option D cannot be empty", Toast.LENGTH_SHORT).show()
        } else if (answer.get().equals("")) {
            Toast.makeText(view.context, "Please select an answer", Toast.LENGTH_SHORT).show()
        } else {



                currentQuestion.get()?.let {
                    var result = tvQuestion.get()?.let { it1 ->
                        optionA.get()?.let { it2 ->
                            optionB.get()?.let { it3 ->
                                optionC.get()?.let { it4 ->
                                    answer.get()?.let { it5 ->
                                        optionD.get()?.let { it6 ->
                                            updateQuestion(it.questionId,
                                                it1,
                                                it2,
                                                it3,
                                                it4,
                                                it6,
                                                it5,view.context
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                //Toast.makeText(view.context, result.toString(), Toast.LENGTH_SHORT).show()
            }
        }


    fun getQuestionById(id: Int): LiveData<Questions> {
        CoroutineScope(Dispatchers.Main).launch {
            if (repository != null) {

                var response = repository.getQuestionById(id)
                response.collect {
                    question.value = it
                }
            }
        }
        return question
    }

    fun updateQuestion(
        questionId: Int,
        question: String,
        optionA: String,
        optionB: String,
        optionC: String,
        optionD: String,
        answer: String,
        context: Context
    ) {

        CoroutineScope(Dispatchers.IO).launch {
            if (repository != null) {

                val response =
                    repository.updateQuestions(Questions(questionId=questionId,question=question,optionA=optionA,optionB=optionB,optionC=optionC,optionD=optionD,answer=answer))


            }
        }
        Toast.makeText(context, "Question updated successfully!", Toast.LENGTH_SHORT).show()

    }

    fun setData(it: Questions?) {

        if (it != null) {
            tvQuestionNumber.set("Q. " + it.questionId.toString())
            optionA.set(it.optionA)
            optionB.set(it.optionB)
            optionC.set(it.optionC)
            optionD.set(it.optionD)
            tvQuestion.set(it.question)

            answersList.clear()
            answersList.add("--select an answer--")
            answersList.add(it.optionA)
            answersList.add(it.optionB)
            answersList.add(it.optionC)
            answersList.add(it.optionD)

            setSelectedAnswer(answersList.indexOf(it.answer))

        }

    }
}