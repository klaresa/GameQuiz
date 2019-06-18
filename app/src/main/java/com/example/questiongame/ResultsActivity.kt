package com.example.questiongame

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_results.*

class ResultsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        var finalPoints = this.intent.getIntExtra("finalPoints", 0)
        yourResult_textView.text = "Voce acertou $finalPoints/5"

        if (finalPoints >= 3){
            acertos_textView.text = "Aeeee!! Voce acertou mais do que errou!"
        } else {
            acertos_textView.text = "Poxa, que droga hein.."
        }
    }
}
