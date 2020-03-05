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


        val textView = findViewById<TextView>(R.id.Choose1)
        val textView2 = findViewById<TextView>(R.id.Choose2)
        val textView3 = findViewById<TextView>(R.id.Choose3)

        textView.setText(message)
        textView2.setText(message2)
        textView3.setText(message3)

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
