package com.example.tablewarelab2

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.fragment_choose.*


class ChooseFragment() : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_choose, container, false)
        val language = resources.getStringArray(R.array.Languages)
        val adapter = ArrayAdapter(rootView.context, android.R.layout.simple_spinner_item, language)
        val spinner = rootView.findViewById<Spinner>(R.id.spinner)
        val fm = fragmentManager;

        val mainButton = rootView.findViewById(R.id.button) as Button
        if(spinner != null)
        {
            spinner.adapter = adapter
        }
        mainButton.setOnClickListener { view ->
            val bundle = Bundle()
            val myFragment = ContentFragment()
            if ((man1.isChecked() || man2.isChecked() || man3.isChecked()) && (price1.isChecked() || price2.isChecked() || price3.isChecked()))
            {
                input(bundle, myFragment)
                fm!!.beginTransaction()
                    .replace(R.id.fragment,myFragment)
                    .show(myFragment)
                    .commit()
            } else {
                fm!!.beginTransaction().replace(R.id.fragment,myFragment)
                    .hide(myFragment)
                    .commit();
                Toast.makeText(context, "Choose something, please", Toast.LENGTH_LONG).show()
            }
        }
        return rootView
    }

    fun input( bundle: Bundle, myFragment: ContentFragment) {
        val spinnerTxt = spinner.selectedItem?.toString()
        bundle.putString("Key",spinnerTxt)
        if(man1.isChecked())
            bundle.putString("KeyManufac1",man1.text.toString())
        if(man2.isChecked())
            bundle.putString("KeyManufac2",man2.text.toString())
        if(man3.isChecked())
            bundle.putString("KeyManufac3",man3.text.toString())
        if(price1.isChecked())
            bundle.putString("KeyPrice1",price1.text.toString())
        if(price2.isChecked())
            bundle.putString("KeyPrice2",price2.text.toString())
        if(price3.isChecked())
            bundle.putString("KeyPrice3",price3.text.toString())
        myFragment.setArguments(bundle)
    }
}