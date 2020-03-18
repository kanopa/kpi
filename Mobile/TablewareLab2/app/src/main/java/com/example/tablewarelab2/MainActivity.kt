package com.example.tablewarelab2

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val language = resources.getStringArray(R.array.Languages)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, language)
        val spinner = findViewById<Spinner>(R.id.spinner)
        val fm = getSupportFragmentManager()
        val manufact1 = man1?.getText()?.toString()
        val manufact2 = man2.getText()?.toString()
        val manufact3 = man3.getText()?.toString()
        val priceText1 = price1.getText()?.toString()
        val priceText2 = price2.getText()?.toString()
        val priceText3 = price3.getText()?.toString()
        if(spinner != null)
        {
            spinner.adapter = adapter
        }
        button.setOnClickListener { view ->
            val bundle = Bundle()
            val myFragment = ContentFragment()
            val spinnerTxt = spinner.selectedItem?.toString()

            if ( (man1.isChecked() || man2.isChecked() || man3.isChecked()) && (price1.isChecked() || price2.isChecked() || price3.isChecked()))
            {
                try {
                    bundle.putString("Key",spinnerTxt)
                    if(man1.isChecked())
                        bundle.putString("KeyManufac1",manufact1)
                    if(man2.isChecked())
                        bundle.putString("KeyManufac2",manufact2)
                    if(man3.isChecked())
                        bundle.putString("KeyManufac3",manufact3)
                    if(price1.isChecked())
                        bundle.putString("KeyPrice1",priceText1)
                    if(price2.isChecked())
                        bundle.putString("KeyPrice2",priceText2)
                    if(price3.isChecked())
                        bundle.putString("KeyPrice3",priceText3)
                    myFragment.setArguments(bundle)
                    fm.beginTransaction()
                        .replace(R.id.fragment,myFragment)
                        .show(myFragment)
                        .commit()
                }
                catch ( exception : Exception)
                {
                    println(exception)
                }
            } else {
                fm.beginTransaction().replace(R.id.fragment,myFragment)
                    .hide(myFragment)
                    .commit();
                Snackbar.make(view, "Choose something please", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
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
