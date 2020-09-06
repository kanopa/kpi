package com.example.tablewarelab3

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_first, container, false)
        val language = resources.getStringArray(R.array.Languages)
        val adapter = ArrayAdapter(rootView.context, android.R.layout.simple_spinner_item, language)
        val spinner = rootView.findViewById<Spinner>(R.id.spinner)
        val fm = fragmentManager;

        val mainButton = rootView.findViewById(R.id.show_all) as Button
        if(spinner != null)
        {
            spinner.adapter = adapter
        }
        mainButton.setOnClickListener { view ->
            val bundle = Bundle()
            val myFragment = SecondFragment()
            if ((man1.isChecked() || man2.isChecked() || man3.isChecked()) && (price1.isChecked() || price2.isChecked() || price3.isChecked()))
            {
                input(bundle, myFragment)
                fm!!.beginTransaction()
                    .replace(R.id.fragment,myFragment)
                    .show(myFragment)
                    .commit()
                saveDB(bundle, context)
                Toast.makeText(context, "Add to base", Toast.LENGTH_LONG).show()
            } else {
                fm!!.beginTransaction().replace(R.id.fragment,myFragment)
                    .hide(myFragment)
                    .commit();
                Toast.makeText(context, "Choose something, please", Toast.LENGTH_LONG).show()
            }
        }
        return rootView
    }

    fun saveDB(bundle: Bundle, context: Context?) {
        val db = BaseHelper(context,null)
        val add = ModelTableware(spinnerText = bundle["Key"] as String?,
            Manufactory1 = bundle["KeyManufac1"] as String?,
            Manufactory2 = bundle["KeyManufac2"] as String?,
            Manufactory3 = bundle["KeyManufac3"] as String?,
            Price1 = bundle["KeyPrice1"] as String?,
            Price2 = bundle["KeyPrice2"] as String?,
            Price3 = bundle["KeyPrice3"] as String?)
        db.addInformation(add)
    }

    fun input( bundle: Bundle, myFragment: SecondFragment) {
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
