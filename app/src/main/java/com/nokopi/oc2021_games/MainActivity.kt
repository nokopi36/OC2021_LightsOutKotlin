package com.nokopi.oc2021_games

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var soundPlayer: SoundPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lightOutButton5.text = "5×5"
        lightOutButton4.text = "4×4"
        lightOutButton3.text = "3×3"

        soundPlayer = SoundPlayer(this)

        lightOutButton5.setOnClickListener {
            val lightsout5 = Intent(this, LightsOutjava5::class.java)
            soundPlayer.playSelectGameSound()
            startActivity(lightsout5)
        }

        lightOutButton4.setOnClickListener {
            val lightsout = Intent(this, LightsOutjava4::class.java)
            soundPlayer.playSelectGameSound()
            startActivity(lightsout)
        }

        lightOutButton3.setOnClickListener {
            val lighsout3 = Intent(this, LightsOutjava3::class.java)
            soundPlayer.playSelectGameSound()
            startActivity(lighsout3)
        }
    }
}