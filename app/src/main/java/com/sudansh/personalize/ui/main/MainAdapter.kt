package com.sudansh.personalize.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import com.sudansh.personalize.databinding.ItemLayoutBinding
import io.realm.Realm
import io.realm.RealmList


class MainAdapter(
    private val context: Context
) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    val realm: Realm = Realm.getDefaultInstance()
    private var questionData: QuestionData? = null
    private var questionsList = mutableListOf<Questions>()
    private var hardfactList = mutableListOf<Questions>()
    private var lifestyleList = mutableListOf<Questions>()
    private var introversionList = mutableListOf<Questions>()
    private var passionList = mutableListOf<Questions>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val binding = viewHolder.binding
        binding.question.text = questionsList[position].question.toString()
        val options = questionsList[position].question_type?.options.orEmpty()
        val selected = questionsList[position].question_type?.selectedValue
        binding.radioGroup.removeAllViews()
        options.forEachIndexed { index, s ->
            val rb = RadioButton(context).apply {
                text = s
                id = index
                isChecked = selected == s
            }
            binding.radioGroup.addView(rb)
        }
        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            realm.executeTransaction {
                questionsList[position].question_type?.selectedValue = options[checkedId]
            }
        }
        binding.counter.text = "${position + 1} / ${questionsList.size}"

    }

    override fun getItemCount(): Int {
        return questionsList.size
    }

    fun setData(
        questionData: QuestionData?
    ) {
        this.questionData = questionData
        separateListByCategory(questionData?.questions)
        questionsList.addAll(hardfactList)
        questionsList.addAll(lifestyleList)
        questionsList.addAll(introversionList)
        questionsList.addAll(passionList)
        notifyDataSetChanged()
    }

    private fun separateListByCategory(questions: RealmList<Questions>?) {
        for (i in 0..(questions?.size?.minus(1) ?: 0)) {
            when {
                questions?.get(i)?.category.equals("hard_fact") -> questions?.get(i)?.let {
                    hardfactList.add(
                        it
                    )
                }
                questions?.get(i)?.category.equals("lifestyle") -> questions?.get(i)?.let {
                    lifestyleList.add(
                        it
                    )
                }
                questions?.get(i)?.category.equals("introversion") -> questions?.get(i)?.let {
                    introversionList.add(
                        it
                    )
                }
                else -> questions?.get(i)?.let { passionList.add(it) }
            }
        }
    }

    inner class ViewHolder(val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

}