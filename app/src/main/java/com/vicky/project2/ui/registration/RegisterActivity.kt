package com.vicky.project2.ui.registration

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.vicky.project2.data.CommonDataPreferences
import com.vicky.project2.databinding.ActivityRegisterBinding
import com.vicky.project2.ui.main.auth.LoginActivity
import com.vicky.project2.utils.SharedPreferencesConstants

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var commonDataPreferences: CommonDataPreferences
    //configure view binding
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    //set view binding
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // taking FirebaseAuth instance to initialize auth
        auth = FirebaseAuth.getInstance()

        commonDataPreferences = CommonDataPreferences(this.applicationContext)

        val btn_register = binding.btnRegister

        btn_register.setOnClickListener {

            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (binding.etEmail.text.toString().isEmpty()) {
                binding.etEmail.error = "Email required"
            }
            if (binding.etPassword.text.toString().isEmpty()) {
                binding.etPassword.error = "Password required"
            }
            // password should be at least 6 characters long
            if (binding.etPassword.length() <= 6){
                binding.etPassword.error = "Password should above 6 characters long"
            }
            else {

                // Handle register button click event here
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)


                //saves username and password to shared preferences
                commonDataPreferences.saveStringData(SharedPreferencesConstants.EMAIL, binding.etEmail.text.toString())
                commonDataPreferences.saveStringData(SharedPreferencesConstants.PASSWORD, binding.etPassword.text.toString())

                Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show()


                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    //if task is successful, account is registered
                    //then taken to login page


                        if (it.isSuccessful) {
                            Toast.makeText(applicationContext, "Registration Successful", Toast.LENGTH_SHORT).show()


                            val intent = Intent(this, LoginActivity::class.java)
                            startActivity(intent)
                        } else {

                            // Registration failed
                            Toast.makeText(applicationContext, "Failed Registration", Toast.LENGTH_SHORT).show()

                        }

                }
            }

        }

    }


}


