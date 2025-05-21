package com.mobile.bce

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FormActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)


        val btnRegister = findViewById<Button>(R.id.btnSubmit)
        val txtResultName = findViewById<TextView>(R.id.tvResultName)
        val txtResultAddress = findViewById<TextView>(R.id.tvResultAddress)
        val txtResultEmail = findViewById<TextView>(R.id.tvResultEmail)
        val name = findViewById<EditText>(R.id.edtName)
        val address = findViewById<EditText>(R.id.edtAddress)
        val email = findViewById<EditText>(R.id.edtEmail)
        val country = findViewById<Spinner>(R.id.spCountry)
        val terms = findViewById<CheckBox>(R.id.cbTerms)
        val genderGroup = findViewById<RadioGroup>(R.id.rdGroup)
        val male = findViewById<RadioButton>(R.id.rdMale)
        val female = findViewById<RadioButton>(R.id.rdFemale)

        val toolbar = findViewById<Toolbar>(R.id.form_toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        // Optional: Set a custom title from code
        supportActionBar?.title = "Form"

        toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed() // Modern approach
        }

        //code to display and pass form data
        btnRegister.setOnClickListener {

            val nameData = name.text.toString()
            val addressData = address.text.toString()
            val emailData = email.text.toString()
            val genderId = genderGroup.checkedRadioButtonId
            val genderText = findViewById<RadioButton>(genderId)?.text.toString()
            val countryText = country.selectedItem.toString()
            val acceptedTerms = terms.isChecked.toString()


//            txtResultName.setText("Name:  "+ nameData + ", Country: "+countryText)
//            txtResultAddress.setText("Address:  "+ addressData + ", Gender: "+genderText)
//            txtResultEmail.setText("Email:  "+ emailData+ ", Terms: "+acceptedTerms)

            val intent = Intent(this, ReasultActivity::class.java).apply {
                putExtra("name", nameData)
                putExtra("address", addressData)
                putExtra("email", emailData)
                putExtra("gender", genderText)
                putExtra("country", countryText)
                putExtra("terms", acceptedTerms)
            }

            startActivity(intent)



        }




    }
}