package com.mobile.bce

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mobile.bce.database.MyDatabaseHelper

class ReasultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reasult)

        val dbHelper = MyDatabaseHelper(this)
        val cursor = dbHelper.getAllUsers()
        val userList = mutableListOf<String>()

        if (cursor.moveToFirst()) {
            do {
                val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
                val address = cursor.getString(cursor.getColumnIndexOrThrow("address"))
                val email = cursor.getString(cursor.getColumnIndexOrThrow("email"))
                val gender = cursor.getString(cursor.getColumnIndexOrThrow("gender"))
                val country = cursor.getString(cursor.getColumnIndexOrThrow("country"))
                val terms = cursor.getString(cursor.getColumnIndexOrThrow("terms"))

                userList.add("Name: $name, Address: $address, Email: $email, Gender: $gender, Country: $country, Terms: $terms")
            } while (cursor.moveToNext())
        }

        cursor.close()

        // Set up a ListView or RecyclerView to show userList
        val listView = findViewById<ListView>(R.id.listViewUsers)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, userList)
        listView.adapter = adapter
    }
}