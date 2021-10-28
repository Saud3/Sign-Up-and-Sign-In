package com.example.signupin

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignUp : AppCompatActivity() {
    lateinit var edname: EditText
    lateinit var edloc: EditText
    lateinit var edmob: EditText
    lateinit var edpassword: EditText
    lateinit var dbhr: DBHlpr
    lateinit var btnsigniup: Button
    lateinit var spf: SharedPreferences
    lateinit var editr:SharedPreferences.Editor


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        edname = findViewById(R.id.editTextTextPersonName)
        edloc = findViewById(R.id.editTextTextPersonName2)
        edmob = findViewById(R.id.editTextTextPersonName3)
        edpassword = findViewById(R.id.editTextTextPersonName4)
        btnsigniup = findViewById(R.id.button3)
        dbhr = DBHlpr(applicationContext)
        spf =  getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        editr = spf.edit()

        btnsigniup.setOnClickListener {
            var s1 = edname.text.toString()
            var s2 = edloc.text.toString()
            var s3 = edmob.text.toString()
            var s4 = edpassword.text.toString()

            var k = dbhr.savedet(s1, s2, s3, s4)

            Toast.makeText(applicationContext, "data saved successfully! ", Toast.LENGTH_SHORT)
                .show();
            if (k.equals("-1")) {
                Toast.makeText(applicationContext, "Error data not saved!", Toast.LENGTH_SHORT)
                    .show();
            } else {
                editr.putString("mob",s3).commit()
                Toast.makeText(applicationContext, "save success!", Toast.LENGTH_SHORT).show();
                Intent(this, HomePage::class.java).apply {
                    startActivity(this)
                }
            }
        }
    }
}