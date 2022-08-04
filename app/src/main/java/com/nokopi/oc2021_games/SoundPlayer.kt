package com.nokopi.oc2021_games

import android.content.Context
import android.media.AudioAttributes
import com.nokopi.oc2021_games.SoundPlayer
import android.media.SoundPool
import android.os.Build
import android.media.AudioManager
import com.nokopi.oc2021_games.R

class SoundPlayer(context: Context?) {
    private var audioAttributes: AudioAttributes? = null
    fun playTouchLightSound() {
        soundpool!!.play(touchLightSound, 1.0f, 1.0f, 1, 0, 1.0f)
    }

    fun playStartSound() {
        soundpool!!.play(startSound, 1.0f, 1.0f, 1, 0, 1.0f)
    }

    fun playSelectGameSound() {
        soundpool!!.play(selectGameSound, 1.0f, 1.0f, 1, 0, 1.0f)
    }

    fun playGameClearSound() {
        soundpool!!.play(gameClearSound, 1.0f, 1.0f, 1, 0, 1.0f)
    }

    companion object {
        private var soundpool: SoundPool? = null
        private var touchLightSound: Int = 0
        private var startSound: Int = 0
        private var selectGameSound: Int = 0
        private var gameClearSound: Int = 0
    }

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            audioAttributes = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build()
            soundpool = SoundPool.Builder()
                .setAudioAttributes(audioAttributes)
                .setMaxStreams(2)
                .build()
        } else {
            soundpool = SoundPool(2, AudioManager.STREAM_MUSIC, 0)
        }
        touchLightSound = soundpool!!.load(context, R.raw.button02b, 1)
        startSound = soundpool!!.load(context, R.raw.start, 1)
        selectGameSound = soundpool!!.load(context, R.raw.selectgame, 1)
        gameClearSound = soundpool!!.load(context, R.raw.clear, 1)
    }
}