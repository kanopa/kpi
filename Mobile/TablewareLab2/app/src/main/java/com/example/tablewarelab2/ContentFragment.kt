package com.example.tablewarelab2


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_content.*

/**
 * A simple [Fragment] subclass.
 */
class ContentFragment() : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_content, container, false)
        // Inflate the layout for this fragment

        val spinner = rootView.findViewById<TextView>(R.id.choose1)
        val textView2 = rootView.findViewById<TextView>(R.id.choose2)
        val textView3 = rootView.findViewById<TextView>(R.id.choose3)
        val textView4 = rootView.findViewById<TextView>(R.id.choose4)
        val textView5 = rootView.findViewById<TextView>(R.id.choose5)
        val textView6 = rootView.findViewById<TextView>(R.id.choose6)
        val textView7 = rootView.findViewById<TextView>(R.id.choose7)

        setTextView(spinner, textView2, textView3, textView4, textView5, textView6, textView7)

        return rootView
    }

    fun setTextView(spinner: TextView, textView2: TextView, textView3: TextView,
                    textView4: TextView, textView5: TextView, textView6: TextView, textView7: TextView)
    {
        spinner.setText(this.getArguments()?.getString("Key"))
        textView2.setText(this.getArguments()?.getString("KeyManufac1"))
        textView3.setText(this.getArguments()?.getString("KeyManufac2"))
        textView4.setText(this.getArguments()?.getString("KeyManufac3"))
        textView5.setText(this.getArguments()?.getString("KeyPrice1"))
        textView6.setText(this.getArguments()?.getString("KeyPrice2"))
        textView7.setText(this.getArguments()?.getString("KeyPrice3"))
    }
}
