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
import com.mobile.bce.database.MyDatabaseHelper

class FormActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)


        val btnRegister = findViewById<Button>(R.id.btnSubmit)
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

            // Insert data into the database
            val dbHelper = MyDatabaseHelper(this)
            dbHelper.insertUserData(
                name = nameData,
                address = addressData,
                email = emailData,
                gender = genderText,
                country = countryText,
                terms = acceptedTerms
            )

            // Redirect to the ResultActivity (no data in Intent)
            val intent = Intent(this, ReasultActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
//            val transaction = supportFragmentManager.beginTransaction()
//            transaction.setCustomAnimations(
//                R.anim.slide_in_and_out,     // enter
//                R.anim.slide_out_and_fade,   // exit
//                R.anim.slide_in_and_out,     // popEnter
//                R.anim.slide_out_and_fade    // popExit
//            )
//            transaction.replace(R.id.fragment_container, MyFragment())
//            transaction.addToBackStack(null)
//            transaction.commit()
        }





    }
}