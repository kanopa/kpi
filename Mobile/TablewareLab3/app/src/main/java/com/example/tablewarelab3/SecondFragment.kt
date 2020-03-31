package com.example.tablewarelab3

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_second.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_second, container, false)
        // Inflate the layout for this fragment
        val spinner = rootView.findViewById(R.id.choose1) as TextView
        val textView2 = rootView.findViewById(R.id.choose2) as TextView
        val textView3 = rootView.findViewById(R.id.choose3) as TextView
        val textView4 = rootView.findViewById(R.id.choose4) as TextView
        val textView5 = rootView.findViewById(R.id.choose5) as TextView
        val textView6 = rootView.findViewById(R.id.choose6) as TextView
        val textView7 = rootView.findViewById(R.id.choose7) as TextView

        val show_button = rootView.findViewById(R.id.show_all) as Button

        showAll(show_button)
        setTextView(spinner, textView2, textView3, textView4, textView5, textView6, textView7)

        return rootView
    }

    fun setTextView(spinner: TextView, textView2: TextView, textView3: TextView,
                    textView4: TextView, textView5: TextView, textView6: TextView, textView7: TextView
    )
    {
        spinner.setText(this.getArguments()?.getString("Key"))
        textView2.setText(this.getArguments()?.getString("KeyManufac1"))
        textView3.setText(this.getArguments()?.getString("KeyManufac2"))
        textView4.setText(this.getArguments()?.getString("KeyManufac3"))
        textView5.setText(this.getArguments()?.getString("KeyPrice1"))
        textView6.setText(this.getArguments()?.getString("KeyPrice2"))
        textView7.setText(this.getArguments()?.getString("KeyPrice3"))
    }

    fun showAll(show_button: Button) {
        show_button.setOnClickListener {
            val intent = Intent(context, ShowActivity::class.java)
            startActivity(intent)
        }
    }
}
