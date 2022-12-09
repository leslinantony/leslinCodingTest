package com.example.mymcq.create_exam.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.mymcq.R
import com.example.mymcq.databinding.LayoutRowQuestionsBinding
import com.example.mymcq.room.Questions
import java.util.Collections

class QuestionsAdapter: RecyclerView.Adapter<QuestionsAdapter.ViewHolder>() {

    var questionsList: List<Questions> = Collections.emptyList()
    var mAction: MutableLiveData<Action> = MutableLiveData()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding:LayoutRowQuestionsBinding= DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.layout_row_questions,parent,false)
        return  ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(questionsList[position],mAction)
    }

    override fun getItemCount(): Int {
        return questionsList.size
    }

    fun setData(questionData: List<Questions>)
    {
        this.questionsList= questionData
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: LayoutRowQuestionsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setData(questions: Questions, mAction: MutableLiveData<Action>) {
            binding.apply {
                this.viewModel= QuestionsRowViewModel(questions,mAction)
            }
        }

    }


}