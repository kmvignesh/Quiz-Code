package com.example.vicky.quiz

import android.app.ProgressDialog
import android.content.Context
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
import android.net.ConnectivityManager


class QuestionActivity : AppCompatActivity() {


    var Questionlist: MutableList<Question> = ArrayList()
    lateinit var context: Context
    var index = -1
    var score = 0
    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        context = this
        btn_next.isEnabled = false
        btn_next.alpha = 0.5.toFloat()
        getQuestions().execute()
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

        var hasInternet = false
        lateinit var progressDialog : ProgressDialog

        override fun onPreExecute() {
            super.onPreExecute()
            progressDialog = ProgressDialog(context)
            progressDialog.setMessage("Downloading Questions...")
            progressDialog.show()
        }

        override fun doInBackground(vararg params: Void?): String {
            if (isNetworkAvailable()) {
                hasInternet = true
                val client = OkHttpClient()
                val url = "https://script.googleusercontent.com/macros/echo?user_content_key=1tgBN-ES-vsiLin8Lggs7R094sUSEWlBY3Lv7yLt0KnrexUuaTvreORsTenxGH0HaPDQ0rUkXVqmkc903P_gQrpXCbi98gcsm5_BxDlH2jW0nuo2oDemN9CCS2h10ox_1xSncGQajx_ryfhECjZEnBg4Wj9So2Q_mI0_S0Bm21-AGmcRnplmVaRcxvVzvCi9cnQQJegsnVb9TgJzPufw35cdv3aNHr6K&lib=MKMzvVvSFmMa3ZLOyg67WCThf1WVRYg6Z"
//                val request = Request.Builder().url("http://192.168.56.1/getQuestions.php").build()
                val request = Request.Builder().url(url).build()
                val response = client.newCall(request).execute()
                return response.body()?.string().toString()
            } else {
                return ""
            }
        }

        override fun onPostExecute(result: String?) {
            progressDialog.dismiss()
            if (hasInternet) {
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

                    btn_next.isEnabled = true
                    btn_next.alpha = 1.toFloat()
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
                    Log.d("result", "result : " + result)
                } catch (e: JSONException) {
                    Log.d("result", "JSONException result : " + result)
                } catch (e: ClassCastException) {
                    Log.d("result", "ClassCastException result : " + result)
                }
            }
            super.onPostExecute(result)
        }
    }

}