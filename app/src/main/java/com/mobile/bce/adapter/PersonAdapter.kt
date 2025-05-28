package com.mobile.bce.adapter
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mobile.bce.R
import com.mobile.bce.model.Person

class PersonAdapter(
    private val context: Context,
    private val personList: List<Person>
) : RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    inner class PersonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.nameTextView)
        val phoneTextView: TextView = view.findViewById(R.id.phoneTextView)
        val callButton: Button = view.findViewById(R.id.callButton)
        val smsButton: Button = view.findViewById(R.id.smsButton)
        val websiteButton: Button = view.findViewById(R.id.websiteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_person, parent, false)
        return PersonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = personList[position]
        holder.nameTextView.text = person.name
        holder.phoneTextView.text = person.phone

        holder.callButton.setOnClickListener{
            val intentCall = Intent(Intent.ACTION_DIAL)
            intentCall.data = Uri.parse("tel:${person.phone}")
            context.startActivity(intentCall)
        }

        holder.smsButton.setOnClickListener{
            val intentSms = Intent(Intent.ACTION_VIEW)
            intentSms.data = Uri.parse("sms:${person.phone}")
            context.startActivity(intentSms)
        }

        holder.websiteButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(person.website)
            context.startActivity(intent)
        }

        holder.itemView.setOnClickListener{
            val intentShare = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT,"Name: ${person.name} Phone: ${person.phone}")
            }
            context.startActivity(Intent.createChooser(intentShare,"Share on:"))
        }
    }

    override fun getItemCount(): Int = personList.size
}
