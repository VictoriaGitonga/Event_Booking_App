package com.vicky.project2.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.ImageView
import com.vicky.project2.R
import com.vicky.project2.ui.main.auth.LoginActivity
import com.vicky.project2.ui.welcome.WelcomeActivity
import com.vicky.project2.utils.SharedPreferencesConstants

class SplashOnboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_onboard)

        val SPLASH_DELAY: Long = 4000 // 4 seconds


        val splash_Screen : ImageView = findViewById(R.id.iv_splash)
        splash_Screen.animate().translationY(-2000F).setDuration(1000).setStartDelay(SPLASH_DELAY)

        Handler().postDelayed({
            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_DELAY)


    }
}