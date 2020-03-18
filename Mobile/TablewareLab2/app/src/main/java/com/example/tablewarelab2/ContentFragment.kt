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

        val str = this.getArguments()?.getString("Key").toString()
        val KeyManufac1 = this.getArguments()?.getString("KeyManufac1")
        val KeyManufac2 = this.getArguments()?.getString("KeyManufac2")
        val KeyManufac3 = this.getArguments()?.getString("KeyManufac3")
        val KeyPrice1 = this.getArguments()?.getString("KeyPrice1")
        val KeyPrice2 = this.getArguments()?.getString("KeyPrice2")
        val KeyPrice3 = this.getArguments()?.getString("KeyPrice3")

        spinner.setText(str)
        textView2.setText(KeyManufac1)
        textView3.setText(KeyManufac2)
        textView4.setText(KeyManufac3)
        textView5.setText(KeyPrice1)
        textView6.setText(KeyPrice2)
        textView7.setText(KeyPrice3)

        return rootView
    }
}
