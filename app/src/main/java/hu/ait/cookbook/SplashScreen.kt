package hu.ait.cookbook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()

        val handler = Handler()

        handler.postDelayed({
            val intent = Intent(
                this,
                ScrollingActivity::class.java
            )
            startActivity(intent)
            finish()
        }, 3000)

    }
}


