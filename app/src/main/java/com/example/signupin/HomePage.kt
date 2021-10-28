package com.example.signupin

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HomePage : AppCompatActivity() {
    lateinit var dbhr: DBHlpr
    lateinit var tv : TextView
    lateinit var tv2 : TextView
    lateinit var spf: SharedPreferences
    lateinit var d: String
    lateinit var s: String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        dbhr = DBHlpr(applicationContext)
        tv=findViewById(R.id.textView)
        tv2=findViewById(R.id.textView2)
        spf =  getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)

        s = spf.getString("mob","default value").toString()

        tv.setText("Welcome "+s+"!"+"\n")

        d = dbhr.getdet(s)

        tv2.setText("Your details are \n"+d+"\n")


    }
}