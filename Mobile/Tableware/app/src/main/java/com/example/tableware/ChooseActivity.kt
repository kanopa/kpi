package com.example.tableware

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_choose.*
import kotlinx.android.synthetic.main.content_choose.*

class ChooseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose)
        setSupportActionBar(toolbar)

        val intent = getIntent()
        val message = intent.getStringExtra(MainActivity().EXTRA_MESSAGE)
        val message2 = intent.getStringExtra(MainActivity().EXTRA_MESSAGE2)
        val message3 = intent.getStringExtra(MainActivity().EXTRA_MESSAGE3)
        val message4 = intent.getStringExtra(MainActivity().EXTRA_MESSAGE4)
        val message5 = intent.getStringExtra(MainActivity().EXTRA_MESSAGE5)
        val message6 = intent.getStringExtra(MainActivity().EXTRA_MESSAGE6)
        val message7 = intent.getStringExtra(MainActivity().EXTRA_MESSAGE7)


        val textView = findViewById<TextView>(R.id.choose1)
        val textView2 = findViewById<TextView>(R.id.choose2)
        val textView3 = findViewById<TextView>(R.id.choose3)
        val textView4 = findViewById<TextView>(R.id.choose4)
        val textView5 = findViewById<TextView>(R.id.choose5)
        val textView6 = findViewById<TextView>(R.id.choose6)
        val textView7 = findViewById<TextView>(R.id.choose7)

        textView.setText(message)
        textView2.setText(message2)
        textView3.setText(message3)
        textView4.setText(message4)
        textView5.setText(message5)
        textView6.setText(message6)
        textView7.setText(message7)

        button_back.setOnClickListener {
            val activityIntent = Intent(this, MainActivity::class.java)
            startActivity(activityIntent)
        }

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own $message", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

}
