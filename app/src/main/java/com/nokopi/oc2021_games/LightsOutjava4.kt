package com.nokopi.oc2021_games

import androidx.appcompat.app.AppCompatActivity
import com.nokopi.oc2021_games.SoundPlayer
import android.os.Bundle
import com.nokopi.oc2021_games.R
import android.widget.TableLayout
import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import java.util.*

class LightsOutjava4 : AppCompatActivity() {
    val SIZE = 4
    val LIMIT = 0
    private var soundPlayer: SoundPlayer? = null
    var lights = Array(SIZE) { arrayOfNulls<ImageView>(SIZE) }

    /* HashMap は ImageView の ID（R.id.）と，画像（off：-1，on:0）のペア*/
    var status = HashMap<Int, Int>()

    //任意の盤面を設定 0:off 1:on
    //    int[] panel = {1,1,0,0,
    //                   1,0,0,0,
    //                   0,0,0,0,
    //                   0,0,0,0};
    override fun onCreate(saveInstanceState: Bundle?) {
        super.onCreate(saveInstanceState)
        setContentView(R.layout.lightsoutjava)
        val tbl = findViewById<TableLayout>(R.id.tablelayout)
        val startReset = findViewById<Button>(R.id.startreset)
        val returnButton = findViewById<Button>(R.id.returnButton)
        val ruleImageView = findViewById<ImageView>(R.id.ruleImage)
        ruleImageView.setImageResource(R.drawable.rule)
        soundPlayer = SoundPlayer(this)
        returnButton.setOnClickListener { v: View? ->
            soundPlayer!!.playStartSound()
            finish()
        }
        startReset.setOnClickListener { v: View? ->
            soundPlayer!!.playStartSound()
            startReset.text = "RESET"
            tbl.visibility = View.VISIBLE
            ruleImageView.visibility = View.GONE
            for (i in 0 until SIZE) {
                for (j in 0 until SIZE) {
                    val Id = lights[i][j]!!.id
                    val rand = Random()
                    val num = rand.nextInt(2) - 1
                    if (num == 0) {
                        lights[i][j]!!.setImageResource(R.drawable.teston)
                        status[Id] = 0
                    }
                    if (num == -1) {
                        lights[i][j]!!.setImageResource(R.drawable.testoff)
                        status[Id] = -1
                    }
                }
            }
        }


        //検証用プログラム
//        startReset.setOnClickListener(v -> {
//            int a = 0;
//            tbl.setVisibility(View.VISIBLE);
//            for (int i = 0; i < SIZE; i++){
//                for (int j = 0; j < SIZE; j++){
//                    int Id = lights[i][j].getId();
//                    if (panel[a] == 0){
//                        lights[i][j].setImageResource(R.drawable.teston);
//                        status.put(Id, 0);
//                    }
//                    if (panel[a] == 1){
//                        lights[i][j].setImageResource(R.drawable.testoff);
//                        status.put(Id, -1);
//                    }
//                    a++;
//                }
//            }
//        });
        val listener = View.OnClickListener { v: View ->
            val touchImage = v as ImageView
            val touchImageId = touchImage.id
            val touchImageXY = SearchImagePlace(touchImage.id)
            val x = touchImageXY / 10
            val y = touchImageXY % 10
            val right: Int
            val left: Int
            val top: Int
            val bottom: Int
            soundPlayer!!.playTouchLightSound()

            //その場のlightsを変える
            if (status[touchImageId] == 0) {
                lights[x][y]!!.setImageResource(R.drawable.testoff)
                status[lights[x][y]!!.id] = -1
            } else {
                lights[x][y]!!.setImageResource(R.drawable.teston)
                status[lights[x][y]!!.id] = 0
            }

            //right
            right = y + 1
            if (right < SIZE) {
                if (status[lights[x][right]!!.id] == 0) {
                    lights[x][right]!!.setImageResource(R.drawable.testoff)
                    status[lights[x][right]!!.id] = -1
                } else {
                    lights[x][right]!!.setImageResource(R.drawable.teston)
                    status[lights[x][right]!!.id] = 0
                }
            }

            //left
            left = y - 1
            if (left >= LIMIT) {
                if (status[lights[x][left]!!.id] == 0) {
                    lights[x][left]!!.setImageResource(R.drawable.testoff)
                    status[lights[x][left]!!.id] = -1
                } else {
                    lights[x][left]!!.setImageResource(R.drawable.teston)
                    status[lights[x][left]!!.id] = 0
                }
            }

            //top
            top = x - 1
            if (top >= LIMIT) {
                if (status[lights[top][y]!!.id] == 0) {
                    lights[top][y]!!.setImageResource(R.drawable.testoff)
                    status[lights[top][y]!!.id] = -1
                } else {
                    lights[top][y]!!.setImageResource(R.drawable.teston)
                    status[lights[top][y]!!.id] = 0
                }
            }

            //bottom
            bottom = x + 1
            if (bottom < SIZE) {
                if (status[lights[bottom][y]!!.id] == 0) {
                    lights[bottom][y]!!.setImageResource(R.drawable.testoff)
                    status[lights[bottom][y]!!.id] = -1
                } else {
                    lights[bottom][y]!!.setImageResource(R.drawable.teston)
                    status[lights[bottom][y]!!.id] = 0
                }
            }
            if (AllOff()) {
                soundPlayer!!.playGameClearSound()
                val success = Intent(this@LightsOutjava4, Success::class.java)
                startActivity(success)
            }
        }
        lights[0][0] = findViewById(R.id.imageButton1)
        lights[0][1] = findViewById(R.id.imageButton2)
        lights[0][2] = findViewById(R.id.imageButton3)
        lights[0][3] = findViewById(R.id.imageButton4)
        lights[1][0] = findViewById(R.id.imageButton5)
        lights[1][1] = findViewById(R.id.imageButton6)
        lights[1][2] = findViewById(R.id.imageButton7)
        lights[1][3] = findViewById(R.id.imageButton8)
        lights[2][0] = findViewById(R.id.imageButton9)
        lights[2][1] = findViewById(R.id.imageButton10)
        lights[2][2] = findViewById(R.id.imageButton11)
        lights[2][3] = findViewById(R.id.imageButton12)
        lights[3][0] = findViewById(R.id.imageButton13)
        lights[3][1] = findViewById(R.id.imageButton14)
        lights[3][2] = findViewById(R.id.imageButton15)
        lights[3][3] = findViewById(R.id.imageButton16)
        for (i in 0 until SIZE) {
            for (j in 0 until SIZE) {
                lights[i][j]?.setImageResource(R.drawable.empty)
                status[lights[i][j]!!.getId()] = -1
                lights[i][j]?.setOnClickListener(listener)
            }
        }
    }

    fun SearchImagePlace(id: Int): Int {
        for (i in 0 until SIZE) {
            for (j in 0 until SIZE) {
                if (lights[i][j]!!.id == id) {
                    return i * 10 + j
                }
            }
        }
        return 0
    }

    fun AllOff(): Boolean {
        var answer = 0
        for (i in 0 until SIZE) {
            for (j in 0 until SIZE) {
                if (status[lights[i][j]!!.id] == 0) {
                    answer++
                }
            }
        }
        return answer == SIZE * SIZE
    }
}