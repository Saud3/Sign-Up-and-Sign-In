package com.example.signupin

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHlpr(context: Context) : SQLiteOpenHelper(context, "reg.db", null, 1) {

    var sqLiteDatabase: SQLiteDatabase = writableDatabase


    override fun onCreate(db: SQLiteDatabase?) {

        if (db != null) {
            db.execSQL("create table students (Name text , Location text, Mobile text, Password text)")
        }

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}

    fun savedet(name: String, location: String, mobile: String, password: String): Long {
        sqLiteDatabase=writableDatabase

        val cv = ContentValues()
        cv.put("Name", name)
        cv.put("Location", location)
        cv.put("Mobile", mobile)
        cv.put("Password", password)
        return sqLiteDatabase.insert( "students", null, cv)

    }

    fun checkpassword(mobile: String): String {
        //  sqLiteDatabase=writableDatabase

        var c: Cursor = sqLiteDatabase.query(
            "students", null, "Mobile=?", arrayOf(mobile), null, null, null
        )
        if (c.count < 1) {
            return "name not exists"
        }
        c.moveToFirst()
        var loc = c.getString(c.getColumnIndex("Password"))
        return loc
    }
    fun getdet(mobile: String?): String {
        //  sqLiteDatabase=writableDatabase

        var c: Cursor = sqLiteDatabase.query(
            "students", null, "Mobile=?", arrayOf(mobile), null, null, null
        )
        if (c.count < 1) {
            return "name not exists"
        }
        c.moveToFirst()
        var loc = c.getString(c.getColumnIndex("Name"))+"\n"+ c.getString(c.getColumnIndex("Location"))
        return loc
    }

    fun updateloc(s1: String, s2: String): Int {
        sqLiteDatabase=writableDatabase
        val cv = ContentValues()
        cv.put("Location", s2)
        var j = sqLiteDatabase.update("students", cv, "Name = ?", arrayOf(s1))

        // update table students set Location = s2 where Name = s1
        return j
    }

    fun delname(s1: String) {
        sqLiteDatabase=writableDatabase
        sqLiteDatabase.delete("students", "Name =?",  arrayOf(s1))
    }

}