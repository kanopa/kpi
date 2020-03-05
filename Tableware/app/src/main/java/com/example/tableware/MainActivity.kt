package com.example.tableware

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Spinner
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    val EXTRA_MESSAGE = "EXTRA_MESSAGE"
    val EXTRA_MESSAGE2 = "EXTRA_MESSAGE2"
    val EXTRA_MESSAGE3 = "EXTRA_MESSAGE3"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val language = resources.getStringArray(R.array.Languages)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, language)
        val spinner = findViewById<Spinner>(R.id.spinner)

        if(spinner != null)
        {
            spinner.adapter = adapter
        }
        man1.setOnClickListener{
            man2.setChecked(false)
            man3.setChecked(false)
        }
        man2.setOnClickListener{
            man1.setChecked(false)
            man3.setChecked(false)
        }
        man3.setOnClickListener{
            man1.setChecked(false)
            man2.setChecked(false)
        }
        price1.setOnClickListener{
            price2.setChecked(false)
            price3.setChecked(false)
        }
        price2.setOnClickListener{
            price1.setChecked(false)
            price3.setChecked(false)
        }
        price3.setOnClickListener{
            price1.setChecked(false)
            price2.setChecked(false)
        }
        button.setOnClickListener {
            val activityIntent = Intent(this, ChooseActivity::class.java)
            val spinnerTxt = spinner.selectedItem.toString()
            val manufact1 = man1.getText().toString()
            val manufact2 = man2.getText().toString()
            val manufact3 = man3.getText().toString()
            val priceText = price1.getText().toString()
            val priceText2 = price2.getText().toString()
            val priceText3 = price3.getText().toString()

            if(man1.isChecked())
            {
                activityIntent.putExtra(EXTRA_MESSAGE2,manufact1)
            } else if(man2.isChecked())
            {
                activityIntent.putExtra(EXTRA_MESSAGE2,manufact2)
            } else{
                activityIntent.putExtra(EXTRA_MESSAGE2,manufact3)
            }
            if( price1.isChecked())
            {
                activityIntent.putExtra(EXTRA_MESSAGE3,priceText)
            } else if(price2.isChecked())
            {
                activityIntent.putExtra(EXTRA_MESSAGE3, priceText2)
            } else
            {
                activityIntent.putExtra(EXTRA_MESSAGE3, priceText3)
            }
            activityIntent.putExtra(EXTRA_MESSAGE, spinnerTxt)
            startActivity(activityIntent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
