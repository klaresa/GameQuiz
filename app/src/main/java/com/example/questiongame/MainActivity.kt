package com.example.questiongame

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.example.questiongame.fragments.QuestionFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_question.*

class MainActivity : AppCompatActivity() {

    var indexCounter = 0
    var questions = listOf<Questions>()
    var finalPoints = 0
    var errou = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        questions = listOf(
            Questions(getString(R.string.planta), R.drawable.planta , resources.getStringArray(R.array.planta_res).toList(), 1),
            Questions(getString(R.string.yoshi), R.drawable.yoshi , resources.getStringArray(R.array.yoshi_res).toList(), 3),
            Questions(getString(R.string.livro), R.drawable.book1984 , resources.getStringArray(R.array.livro_res).toList(), 3),
            Questions(getString(R.string.halflife), R.drawable.halflife , resources.getStringArray(R.array.halflife_res).toList(), 2),
            Questions(getString(R.string.marco), R.drawable.guerra, resources.getStringArray(R.array.marco_res).toList(), 1)

        )

        // para operar com fragments
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val questionFragment = QuestionFragment.newInstance(questions[indexCounter])
        fragmentTransaction.add(R.id.container, questionFragment)
        fragmentTransaction.commit()

        setUpListeners()
    }
    fun setUpListeners(){
        ok_button.setOnClickListener {
            val choices = findViewById<RadioGroup>(R.id.group_radioGroup)
            var guessed = choices.checkedRadioButtonId

            if (guessed != -1){
                checking(guessed)
                nextQuestion()
            }
        }
    }

    fun nextQuestion(){
        indexCounter ++
        if (indexCounter < questions.size) {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            val questionFragment = QuestionFragment.newInstance(questions[indexCounter])
            fragmentTransaction.replace(R.id.container, questionFragment)
            fragmentTransaction.commit()

            sizing()
        } else {
            val intent = Intent(this, ResultsActivity::class.java)
            intent.putExtra("finalPoints", finalPoints)
            startActivity(intent)

        }
    }

    fun checking(guessed:Int):Int{
        val answer = questions[indexCounter].correct
        val checking = when (guessed){
            R.id.op1_radioButton -> 1
            R.id.op2_radioButton -> 2
            R.id.op3_radioButton -> 3
            else -> 0
        }
        if (checking == answer){
            //Toast.makeText(this, "acertou", Toast.LENGTH_SHORT).show()
            return finalPoints++
        } else {
            //Toast.makeText(this, "errou", Toast.LENGTH_SHORT).show()
            return errou++
        }
    }

    fun sizing() {
        if (indexCounter == questions.size-1) {
            ok_button.setText("Concluir")
        }
    }

}