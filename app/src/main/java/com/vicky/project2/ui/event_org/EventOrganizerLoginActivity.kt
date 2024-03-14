package com.vicky.project2.ui.event_org

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.vicky.project2.R
import com.vicky.project2.data.CommonDataPreferences
import com.vicky.project2.databinding.ActivityEventOrganizerLoginBinding
import com.vicky.project2.ui.dashboard_user.MainActivity
import com.vicky.project2.ui.registration.RegisterActivity
import com.vicky.project2.utils.SharedPreferencesConstants

class EventOrganizerLoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var commonDataPreferences: CommonDataPreferences
    private lateinit var binding: ActivityEventOrganizerLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEventOrganizerLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //initialize auth
        auth = FirebaseAuth.getInstance()

        commonDataPreferences = CommonDataPreferences(this.applicationContext)
        val btn_login = binding.btnLogin
        btn_login.setOnClickListener {
            // Handle register button click event here
            //directs us to dashboard which is main activity

            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            //validates username, password
            if (binding.etEmail.text.toString().isEmpty()){
                binding.etEmail.error= "Username required"
            }
            if (binding.etPassword.text.toString().isEmpty()){
                binding.etPassword.error= "Password required"
            }
            // password should be at least 6 characters long
            if (binding.etPassword.length() <= 6){
                binding.etPassword.error = "Password should above 6 characters long"
            } else{

                //saves username and password to shared preferences
                commonDataPreferences.saveStringData(SharedPreferencesConstants.EMAIL, binding.etEmail.text.toString())
                commonDataPreferences.saveStringData(SharedPreferencesConstants.PASSWORD, binding.etPassword.text.toString())

                Toast.makeText(this, "You are now logged in!", Toast.LENGTH_SHORT).show()

                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {

                    if (it.isSuccessful) {
                        Toast.makeText(applicationContext, "Successful", Toast.LENGTH_SHORT)
                            .show()

                        // if sign-in is successful
                        // intent to home activity
                        val intent = Intent(this, EventOrganizerActivity::class.java)
                        startActivity(intent)

                    } else {
                        Toast.makeText(applicationContext, "Unsuccessful", Toast.LENGTH_SHORT)
                            .show()

                    }

                }
            }

        }

      }
    }
