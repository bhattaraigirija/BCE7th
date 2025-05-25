package com.mobile.bce.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobile.bce.model.Person

class TestAdapter(
    private val context: Context,
    private val personList: List<Person>
): RecyclerView.Adapter<TestAdapter.TestViewHolder>() {
    inner class TestViewHolder {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}