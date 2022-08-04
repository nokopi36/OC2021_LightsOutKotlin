package com.nokopi.oc2021_games

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.lightsoutjava5.*
import kotlinx.android.synthetic.main.success.*

class Success : AppCompatActivity() {

    private lateinit var soundPlayer: SoundPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.success)
        returnHome.text = "タイトルへ戻る"

        soundPlayer = SoundPlayer(this)

        successImage.setImageResource(R.drawable.success)

        returnHome.setOnClickListener {
            soundPlayer.playSelectGameSound()
            val home = Intent(this, MainActivity::class.java)
            startActivity(home)
        }
    }
}