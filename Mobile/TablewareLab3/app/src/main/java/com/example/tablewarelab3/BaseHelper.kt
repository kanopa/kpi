package com.example.tablewarelab3

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BaseHelper(context: Context?, factory: SQLiteDatabase.CursorFactory?) : SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE = ("CREATE TABLE " +
                TABLE_NAME + "("
                + Id + " INTEGER PRIMARY KEY,"
                + SpinnerText + " TEXT,"
                + Manufactory1 + " TEXT,"
                + Manufactory2 + " TEXT,"
                + Manufactory3 + " TEXT,"
                + Price1 + " TEXT,"
                + Price2 + " TEXT,"
                + Price3 + " TEXT" + ")")
        db.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun getAll(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }

    fun dropData() {
        val db = this.writableDatabase;
        db.delete(TABLE_NAME, null, null);
    }

    fun addInformation(tableware: ModelTableware) {
        val values = ContentValues()
        values.put(SpinnerText, tableware.spinnerText)
        values.put(Manufactory1, tableware.Manufactory1)
        values.put(Manufactory2, tableware.Manufactory2)
        values.put(Manufactory3, tableware.Manufactory3)
        values.put(Price1, tableware.Price1)
        values.put(Price2, tableware.Price2)
        values.put(Price3, tableware.Price3)
        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "Tableware.db"
        val TABLE_NAME = "Ware"
        val Id = "_id"
        val SpinnerText = "SpinnerText"
        val Manufactory1 = "Manufactory1"
        val Manufactory2 = "Manufactory2"
        val Manufactory3 = "Manufactory3"
        val Price1 = "Price1"
        val Price2 = "Price2"
        val Price3 = "Price3"
    }
}