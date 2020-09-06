package com.example.tablewarelab3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_show.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter

class ShowActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)

        val collection = getList()
        println(collection.size)
        if(collection.isNotEmpty())
        {
            list_recycler_view.apply {
                adapter = ListAdapter(collection.reversed())
                layoutManager = LinearLayoutManager(context)
            }
        }
        BackMain()
    }

    fun BackMain() {
        button_back.setOnClickListener{
            val activityIntent = Intent(this, MainActivity::class.java)
            startActivity(activityIntent)
        }
    }

    fun getList(): List<ModelTableware> {

        val list: ArrayList<ModelTableware> = ArrayList()

        val dbHandler = BaseHelper(this, null)
        val cursor = dbHandler.getAll()

        if(cursor!!.moveToFirst()) {
            val spinner = cursor.getString(cursor.getColumnIndex(BaseHelper.SpinnerText))
            val manufactory1 = cursor.getString(cursor.getColumnIndex(BaseHelper.Manufactory1))
            val manufactory2 = cursor.getString(cursor.getColumnIndex(BaseHelper.Manufactory2))
            val manufactory3 = cursor.getString(cursor.getColumnIndex(BaseHelper.Manufactory3))
            val price1 = cursor.getString(cursor.getColumnIndex(BaseHelper.Price1))
            val price2 = cursor.getString(cursor.getColumnIndex(BaseHelper.Price2))
            val price3 = cursor.getString(cursor.getColumnIndex(BaseHelper.Price3))
            list.add(ModelTableware(spinnerText = spinner,
                Manufactory1 = manufactory1,
                Manufactory2 = manufactory2,
                Manufactory3 = manufactory3,
                Price1 = price1,
                Price2 = price2,
                Price3 = price3))
        }
        else{
            return list
        }
        while (cursor.moveToNext()) {
            val spinner = cursor.getString(cursor.getColumnIndex(BaseHelper.SpinnerText))
            val manufactory1 = cursor.getString(cursor.getColumnIndex(BaseHelper.Manufactory1))
            val manufactory2 = cursor.getString(cursor.getColumnIndex(BaseHelper.Manufactory2))
            val manufactory3 = cursor.getString(cursor.getColumnIndex(BaseHelper.Manufactory3))
            val price1 = cursor.getString(cursor.getColumnIndex(BaseHelper.Price1))
            val price2 = cursor.getString(cursor.getColumnIndex(BaseHelper.Price2))
            val price3 = cursor.getString(cursor.getColumnIndex(BaseHelper.Price3))
            list.add(ModelTableware(spinnerText = spinner,
                Manufactory1 = manufactory1,
                Manufactory2 = manufactory2,
                Manufactory3 = manufactory3,
                Price1 = price1,
                Price2 = price2,
                Price3 = price3))
        }
        cursor.close()
        return list
    }
}
