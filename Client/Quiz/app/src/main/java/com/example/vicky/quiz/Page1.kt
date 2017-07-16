package com.example.vicky.quiz

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Context
import android.content.Intent
import android.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_main.*


class Page1 : AppCompatActivity() {

    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val highScore = preferences.getInt("pastScore", 0)
        val text = "Past Score : " + highScore
        tv_score.text = text
//        ratingBar.rating = (highScore.toFloat() / 2.0).toFloat()
        ratingBar.rating = highScore.toFloat()
        btn_start.setOnClickListener({
            var i = Intent(this, QuestionActivity::class.java)
            startActivity(i)
            finish()
        })
    }
}
