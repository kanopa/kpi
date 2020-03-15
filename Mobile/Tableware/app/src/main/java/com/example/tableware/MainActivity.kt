package com.example.tableware

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    val EXTRA_MESSAGE = "EXTRA_MESSAGE"
    val EXTRA_MESSAGE2 = "EXTRA_MESSAGE2"
    val EXTRA_MESSAGE3 = "EXTRA_MESSAGE3"
    val EXTRA_MESSAGE4 = "EXTRA_MESSAGE4"
    val EXTRA_MESSAGE5 = "EXTRA_MESSAGE5"
    val EXTRA_MESSAGE6 = "EXTRA_MESSAGE6"
    val EXTRA_MESSAGE7 = "EXTRA_MESSAGE7"

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
        button.setOnClickListener {view ->
            val activityIntent = Intent(this, ChooseActivity::class.java)
            val spinnerTxt = spinner.selectedItem.toString()
            val manufact1 = man1.getText().toString()
            val manufact2 = man2.getText().toString()
            val manufact3 = man3.getText().toString()
            val priceText = price1.getText().toString()
            val priceText2 = price2.getText().toString()
            val priceText3 = price3.getText().toString()

            Snackbar.make(view, "Choose something please", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()

            if(man1.isChecked())
            {
                activityIntent.putExtra(EXTRA_MESSAGE2,manufact1)
            }
            if(man2.isChecked())
            {
                activityIntent.putExtra(EXTRA_MESSAGE3,manufact2)
            }
            if(man3.isChecked()){
                activityIntent.putExtra(EXTRA_MESSAGE4,manufact3)
            }
            if( price1.isChecked())
            {
                activityIntent.putExtra(EXTRA_MESSAGE5,priceText)
            }
            if(price2.isChecked())
            {
                activityIntent.putExtra(EXTRA_MESSAGE6, priceText2)
            }
            if(price3.isChecked())
            {
                activityIntent.putExtra(EXTRA_MESSAGE7, priceText3)
            }
            if (man1.isChecked() || man2.isChecked() || man3.isChecked() && price1.isChecked() || price2.isChecked() || price3.isChecked())
            {
                activityIntent.putExtra(EXTRA_MESSAGE, spinnerTxt)
                startActivity(activityIntent)
            }
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
