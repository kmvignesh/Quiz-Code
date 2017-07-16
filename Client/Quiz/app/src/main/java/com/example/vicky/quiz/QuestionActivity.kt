package com.example.vicky.quiz

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AlertDialog
import android.util.Log
import android.widget.*
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONException
import kotlinx.android.synthetic.main.activity_question.*
import java.util.*

class QuestionActivity : AppCompatActivity() {


    var Questionlist: MutableList<Question> = ArrayList()
    lateinit var context: Context
    var index = -1
    var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        context = this
        getQuestions().execute()
        btn_next.setOnClickListener({
            if (index == -1) {
                index++
                tv_question.text = Questionlist[index].Question
                rb_choice1.text = Questionlist[index].Option1
                rb_choice2.text = Questionlist[index].Option2
                rb_choice3.text = Questionlist[index].Option3
                rb_choice4.text = Questionlist[index].Option4
            } else {
                UpdateQuestion()
            }
        })
    }

    override fun onBackPressed() {
        val dialog = AlertDialog.Builder(context)
        dialog.setTitle("Are you sure !")
        dialog.setMessage("Do you want to quit the application ?")
        dialog.setPositiveButton("Yes") { dialog, which ->
            dialog?.dismiss()
            super.onBackPressed()
        }
        dialog.setNegativeButton("No") { dialog, which -> dialog?.dismiss() }
        dialog.show()
    }

    fun UpdateQuestion() {
        val selected = rg_choice.checkedRadioButtonId
        if (selected == -1) {
            Toast.makeText(this, "Please select a option.", Toast.LENGTH_SHORT).show()
            return
        }
        if (index < Questionlist.size) {
            when (selected) {
                rb_choice1.id -> {
                    if (Questionlist[index].Answer == 1)
                        score++
                }
                rb_choice2.id -> {
                    if (Questionlist[index].Answer == 2)
                        score++
                }
                rb_choice3.id -> {
                    if (Questionlist[index].Answer == 3)
                        score++
                }
                rb_choice4.id -> {
                    if (Questionlist[index].Answer == 4)
                        score++
                }
            }
            index++
            if (index < Questionlist.size) {
                tv_question.text = Questionlist[index].Question
                rb_choice1.text = Questionlist[index].Option1
                rb_choice2.text = Questionlist[index].Option2
                rb_choice3.text = Questionlist[index].Option3
                rb_choice4.text = Questionlist[index].Option4
                rg_choice.clearCheck()
                if ((index + 1) == Questionlist.size)
                    btn_next.text = "Finish"
            } else {
                val preferences = PreferenceManager.getDefaultSharedPreferences(context)
                val editor = preferences.edit()
                editor.putInt("pastScore", score)
                editor.apply()
                val i = Intent(context, Page1::class.java)
                startActivity(i)
                finish()
            }
        }
    }

    internal inner class getQuestions : AsyncTask<Void, Void, String>() {
        override fun doInBackground(vararg params: Void?): String {
            val client = OkHttpClient()
            val request = Request.Builder().url("http://192.168.56.1/getQuestions.php").build()
            val response = client.newCall(request).execute()
            return response.body()?.string().toString()
        }

        override fun onPostExecute(result: String?) {
            try {
                val resultArray = JSONArray(result)
                for (i in 0..(resultArray.length() - 1)) {
                    val currentObject = resultArray.getJSONObject(i)
                    val obj = Question()
                    obj.Question = currentObject.getString("Question")
                    obj.Option1 = currentObject.getString("Option1")
                    obj.Option2 = currentObject.getString("Option2")
                    obj.Option3 = currentObject.getString("Option3")
                    obj.Option4 = currentObject.getString("Option4")
                    obj.Answer = currentObject.getInt("Answer")
                    Questionlist.add(obj)
                }
                if (index == -1) {
                    index++
                    Log.d("result", "Question : " + Questionlist[index].Question)
                    tv_question.text = Questionlist[index].Question
                    rb_choice1.text = Questionlist[index].Option1
                    rb_choice2.text = Questionlist[index].Option2
                    rb_choice3.text = Questionlist[index].Option3
                    rb_choice4.text = Questionlist[index].Option4
                } else {
                    Log.d("result", "index : " + index)
                    UpdateQuestion()
                }
                Log.d("result", "result : " + result)
            } catch (e: JSONException) {
                Log.d("result", "JSONException result : " + result)
            } catch (e: ClassCastException) {
                Log.d("result", "ClassCastException result : " + result)
            }
            super.onPostExecute(result)
        }
    }

}