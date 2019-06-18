package com.example.questiongame.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import com.example.questiongame.Questions

import com.example.questiongame.R
import kotlinx.android.synthetic.main.fragment_question.*


class QuestionFragment : Fragment() {


    lateinit var choices: List<Int>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        choices = listOf(op1_radioButton.id, op2_radioButton.id, op3_radioButton.id)

        nextQuestion()
    }

    fun nextQuestion(){
        val arguments = arguments
        if (arguments != null) {
            question_textView.text = arguments.getString(BODY_KEY)

            var pic = arguments.getInt(IMAGE_KEY)
                image_imageView.setImageResource(pic)

            val options = arguments.getStringArray(OPTIONS_KEY)
            for (item in options.indices) {
                activity?.findViewById<RadioButton>(choices[item])?.text = options[item]
            }
        }
    }


    companion object {
        val BODY_KEY = "title"
        val IMAGE_KEY = "image"
        val OPTIONS_KEY = "answers"
        val ANSWER_KEY = "correct"

        fun newInstance(question: Questions): QuestionFragment{
            val questionFragment = QuestionFragment()
            questionFragment.arguments = Bundle().apply {
                putString(BODY_KEY, question.title)
                putInt(IMAGE_KEY, question.image)
                putStringArray(OPTIONS_KEY, question.answers.toTypedArray())
                putInt(ANSWER_KEY, question.correct)
            }
            return questionFragment
        }
    }
}
