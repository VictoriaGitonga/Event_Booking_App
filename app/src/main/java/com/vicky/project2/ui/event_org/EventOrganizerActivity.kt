package com.vicky.project2.ui.event_org

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.vicky.project2.R
import com.vicky.project2.data.EventsCreated
import com.vicky.project2.databinding.ActivityEventOrganizerBinding
import com.vicky.project2.ui.dashboard_user.EventDetailActivity
import com.vicky.project2.ui.welcome.WelcomeActivity
import com.vicky.project2.utils.EVENT_ID


class EventOrganizerActivity : AppCompatActivity() {

    private lateinit var viewModel: EventOrganizerViewModel
    private lateinit var binding: ActivityEventOrganizerBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(EventOrganizerViewModel::class.java)

        //checks if data is saved in firebase
        viewModel.result.observe(this, Observer {
           val message =  if(it == null){
                //success and added to database
               getString(R.string.events_added)

            }else{
                //failure
                getString(R.string.error, it.message)
           }
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            // Assuming you have the event ID
            val eventId = EVENT_ID
            if (it == null){
               // Start EventDetailActivity and pass the event ID
                 val intent = Intent(this, EventDetailActivity::class.java)
                   intent.putExtra("eventId", eventId)
                  startActivity(intent)
            }

        })



        binding = ActivityEventOrganizerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //initialize auth
        auth = FirebaseAuth.getInstance()


       // setToolbarTitle(
        //    clToolBar = binding.toolBar,
        //    activity = this,
        //    title = "Create event",
        //    action = {

       //     }
      //  )

        val btn_create_event = binding.btnCreateEvent
        btn_create_event.setOnClickListener{

            val eventName = binding.etEventName.text.toString()
            val eventDate = binding.etEventDate.text.toString()
            val eventTicketPrice = binding.etEventPrice.text.toString()
            val eventLocation = binding.etEventLocation.text.toString()
            val eventPoster = binding.etEventPoster.text.toString()
            val eventDescription = binding.etEventDescription.text.toString()


            if (eventName.isEmpty()){
                binding.tlEnterEvent.error = "Field required"
            }
            if (eventDate.isEmpty()){
                binding.tlEnterEvent2.error = "Field required"
            }
            if (eventTicketPrice.isEmpty()){
                binding.tlEnterEvent3.error = "Field required"
            }
            if (eventLocation.isEmpty()){
                binding.tlEnterEvent4.error = "Field required"
            }
            if (eventPoster.isEmpty()){
                binding.tlEnterEvent5.error = "Field required"
            }
            if (eventDescription.isEmpty()){
                binding.tlEnterEvent6.error = "Field required"
            }

            val eventsCreated = EventsCreated()
            eventsCreated.eventName = eventName
            eventsCreated.eventDate = eventDate
            eventsCreated.eventTicketPrice = eventTicketPrice
            eventsCreated.eventLocation = eventLocation
            eventsCreated.eventPoster = eventPoster
            eventsCreated.eventDescription = eventDescription

            //saves data to firebase
            viewModel.addEventsCreated(eventsCreated)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val Inflater = menuInflater
        Inflater.inflate(R.menu.options_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.contact_us -> {
                Toast.makeText(this, "Contact us:)", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.log_out -> {
                auth.signOut()
                val intent = Intent(this, WelcomeActivity::class.java)
                startActivity(intent)

                Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}