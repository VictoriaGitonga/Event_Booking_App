package com.vicky.project2.ui.dashboard_user


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.vicky.project2.R
import com.vicky.project2.qrcode.QrCodeGeneratorActivity
import com.vicky.project2.ui.contacts.ContactUsActivity
import com.vicky.project2.ui.main.auth.LoginActivity
import com.vicky.project2.ui.welcome.WelcomeActivity

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var eventList: ArrayList<EventItem>
    private lateinit var eventAdapter: EventAdapter
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //initialize auth
        auth = FirebaseAuth.getInstance()

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        //initialize event list
        eventList = ArrayList()

        eventList.add(EventItem(R.drawable.rave, "Rave", "20-05-23", "Ksh 500","Shelter, Westlands","A taste of Nairobi Youth Culture!"))
        eventList.add(EventItem(R.drawable.shoke, "Shoke Concert", "04-03-23", "Ksh 3000","K.I.C.C","Flipping the narrative for tomorrow"))
        eventList.add(EventItem(R.drawable.anime, "Anime", "04-06-23", "Ksh 500","Diamond Anga Plaza","For the love of anime"))
        eventList.add(EventItem(R.drawable.green_fest, "Green Festival", "22-06-23", "Ksh 800","The Mayur","Green is the new Black"))
        eventList.add(EventItem(R.drawable.movie, "Fast X movie", "12-05-23", "Ksh 1200","Likoni","Fun event"))
        eventList.add(EventItem(R.drawable.jazz, "Jazz Concert", "03-07-23", "Ksh 1000","NSK","Bring out the horns"))
        eventList.add(EventItem(R.drawable.streetwear, "Streetwear Talk Expo ", "20-05-23", "Ksh 700","Likoni close","Pop-Up Shopping Experience"))

        eventAdapter = EventAdapter(eventList)
        recyclerView.adapter = eventAdapter

        eventAdapter.onItemClick = {
            val intent = Intent(this, QrCodeGeneratorActivity::class.java)
                intent.putExtra("event", it)

            startActivity(intent)
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
                val contacts = Intent(this, ContactUsActivity::class.java)
                startActivity(contacts)
                Toast.makeText(this, "Contact us:)", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.log_out -> {
                auth.signOut()
                val logout = Intent(this, WelcomeActivity::class.java)
                startActivity(logout)
                Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}


