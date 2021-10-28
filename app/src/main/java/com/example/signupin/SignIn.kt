package com.example.signupin

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignIn : AppCompatActivity() {
    lateinit var edmob: EditText
    lateinit var edpassword: EditText
    lateinit var dbhr: DBHlpr
    lateinit var btnsignin: Button
    lateinit var pw: String
    lateinit var spf: SharedPreferences
    lateinit var editr: SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        edmob = findViewById(R.id.editTextTextPersonName5)
        edpassword = findViewById(R.id.editTextTextPersonName6)
        btnsignin = findViewById(R.id.button4)
        dbhr = DBHlpr(applicationContext)
        spf =  getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        editr = spf.edit()

        btnsignin.setOnClickListener {
            var s1=edmob.text.toString()
            var s2=edpassword.text.toString()
            pw = dbhr.checkpassword(s1)
            if (pw.equals(s2)){
                editr.putString("mob",s1).commit()

                Toast.makeText(applicationContext, "Sign in success!", Toast.LENGTH_SHORT).show();


                Intent(this, HomePage::class.java).apply {
                    startActivity(this)
                }

            } else{
                Toast.makeText(applicationContext, "Invaild details", Toast.LENGTH_SHORT).show();
            }
        }

    }
}