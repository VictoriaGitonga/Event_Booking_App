package com.vicky.project2.ui.dashboard_user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.database.FirebaseDatabase
import com.vicky.project2.R
import com.vicky.project2.ui.event_org.EventOrganizerViewModel
import com.vicky.project2.utils.EVENT_ID

class EventDetailActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_detail)
        val viewModel = ViewModelProvider(this).get(EventOrganizerViewModel::class.java)
        // Assuming you have the event ID
        val eventId = EVENT_ID

//        val eventDetails = intent.getParcelableExtra<EventItem>("eventDetails")
//        if (eventDetails != null){
//            val textView: TextView = findViewById(R.id.tv_event_details)
//            val imageView: ImageView = findViewById(R.id.iv_event_details)
//
//            textView.text = eventDetails.name
//            imageView.setImageResource(eventDetails.image)
//        }

        //fetches data from firebase
        viewModel.fetchEventsCreated()
        viewModel.event_created.observe(this, Observer { eventsCreateds ->
            val singleItem = eventsCreateds.filter {
                it.id == EVENT_ID
            }
            val stringBuilder = StringBuilder()
            stringBuilder.append("Event Name: ${singleItem.first().eventName.toString()}\n")
            stringBuilder.append("Event Date: ${singleItem.first().eventDate.toString()}\n")
            stringBuilder.append("Event Ticket Price: ${singleItem.first().eventTicketPrice.toString()}\n")
            stringBuilder.append("Event Location: ${singleItem.first().eventLocation.toString()}\n")
            stringBuilder.append("Event Poster: ${singleItem.first().eventPoster.toString()}\n")
            stringBuilder.append("Event Description: ${singleItem.first().eventDescription.toString()}\n")
            findViewById<TextView>(R.id.tv_event_details).text = stringBuilder.toString()
            //findViewById<ImageView>(R.id.iv_event_details).setImageResource(R.drawable.register)
        })

        //add update and delete buttons
        //and their click listeners
        val btnUpdate = findViewById<Button>(R.id.btn_update)
        val btnDelete = findViewById<Button>(R.id.btn_delete)

        // Get a reference to the Firebase Realtime Database
        val database = FirebaseDatabase.getInstance()
        val reference = database.getReference("EventsCreated")

        btnUpdate.setOnClickListener {
            // Handle button click event here

            // Create a map with the fields you want to update
            val updatedData = hashMapOf<String, Any>(
                "eventName" to "Micah",
                "eventDate" to "12-05-23",
                "eventTicketPrice" to "2500",
                "eventLocation" to "Likoni place",
                "eventPoster" to "None",
                "eventDescription" to "Fun",
                // Add more fields as needed
            )


            // Perform the update operation
            reference.child("EventsCreated").updateChildren(updatedData)
                .addOnSuccessListener {
                    // Update operation successful
                    Toast.makeText(this, "Data updated successfully", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e ->
                    // Update operation failed
                    Toast.makeText(this, "Failed to update data: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }

        btnDelete.setOnClickListener {
            database.getReference("EventsCreated").child(eventId).removeValue()
                    .addOnSuccessListener {
                        // Update operation successful
                        Toast.makeText(this, "Data updated successfully", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener { e ->
                        // Update operation failed
                        Toast.makeText(this, "Failed to update data: ${e.message}", Toast.LENGTH_SHORT).show()
                    }

        }

    }
}