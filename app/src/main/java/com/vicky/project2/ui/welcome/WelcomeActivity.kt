package com.vicky.project2.ui.welcome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.vicky.project2.R
import com.vicky.project2.data.CommonDataPreferences
import com.vicky.project2.ui.event_org.EventOrganizerLoginActivity
import com.vicky.project2.ui.main.auth.LoginActivity
import com.vicky.project2.utils.SharedPreferencesConstants

class WelcomeActivity : AppCompatActivity() {

    private lateinit var commonDataPreferences: CommonDataPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        commonDataPreferences = CommonDataPreferences(this.applicationContext)
        val btn_user: Button = findViewById(R.id.btn_user)
        btn_user.setOnClickListener {
            // Handle button click event here

            commonDataPreferences.saveStringData(SharedPreferencesConstants.CURRENT_PROFILE, "NormalUser")
            commonDataPreferences.checkIsEventOrganizer(false)
            // Navigate to the login page here
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        val btn_organizer: Button = findViewById(R.id.btn_organizer)
        btn_organizer.setOnClickListener {
            // Handle button click event here

            //saves in shared preferences with key name CurrentUser
            commonDataPreferences.saveStringData(SharedPreferencesConstants.CURRENT_PROFILE, "EventUser")
            commonDataPreferences.checkIsEventOrganizer(true)

            // Navigate to the login page here
            val intent = Intent(this, EventOrganizerLoginActivity::class.java)
            startActivity(intent)

        }
    }
}