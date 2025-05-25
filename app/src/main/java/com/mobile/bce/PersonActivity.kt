package com.mobile.bce


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobile.bce.adapter.PersonAdapter
import com.mobile.bce.model.Person

class PersonActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var personAdapter: PersonAdapter
    private val personList = listOf(
        Person("Ram Limbu", "9876543210", "https://www.example.com"),
        Person("Sita Ghimire", "1234567890", "https://www.google.com"),
        Person("Kishor Kumar", "555666777", "https://www.android.com")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person)



        // Set up the toolbar as action bar
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "Person List"
            setDisplayHomeAsUpEnabled(true) // show back button
        }

        // Handle back button click
        toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed() // navigate back
        }

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        personAdapter = PersonAdapter(this, personList)
        recyclerView.adapter = personAdapter
    }
}
