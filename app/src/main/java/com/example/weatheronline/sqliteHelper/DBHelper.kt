package com.example.weatheronline.sqliteHelper

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.weatheronline.model.sqlite.CitySql

class DBHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VER){
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_QUERY =
            ("CREATE TABLE $TABLE_NAME($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,$COL_KEY TEXT,$COL_LOCALIZEDNAME TEXT)")
        db!!.execSQL(CREATE_TABLE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
    val allCity:List<CitySql>
        @SuppressLint("Recycle")
        get() {
            val lstPersons= ArrayList<CitySql>()
            val selectQuery= "SELECT * FROM $TABLE_NAME"
            val db=this.writableDatabase
            val cursor=db.rawQuery(selectQuery,null)
            if (cursor.moveToFirst()){
                do {
                    val person = CitySql()
                    person.id= cursor.getInt(cursor.getColumnIndex(COL_ID))
                    person.key= cursor.getString(cursor.getColumnIndex(COL_KEY))
                    person.localizedName= cursor.getString(cursor.getColumnIndex(COL_LOCALIZEDNAME))

                    lstPersons.add(person)
                }while(cursor.moveToNext())
            }
            return lstPersons
        }

    fun addCity(citySql: CitySql){
        val db= this.writableDatabase
        val values= ContentValues()
        values.put(COL_KEY,citySql.key)
        values.put(COL_LOCALIZEDNAME,citySql.localizedName)
        db.insert(TABLE_NAME,null,values)
        db.close()
    }

    companion object {
        private val DATABASE_VER = 1
        private val DATABASE_NAME = "NAM.db"
        //TABLE
        private val TABLE_NAME = "City"
        private val COL_ID = "Id"
        private val COL_KEY = "key"
        private val COL_LOCALIZEDNAME = "localizedName"
    }
}