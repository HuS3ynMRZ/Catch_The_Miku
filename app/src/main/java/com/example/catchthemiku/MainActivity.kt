package com.example.catchthemiku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    var score = 0
    var imageArray = ArrayList<ImageView>()
    var handler = Handler()
    var runnable = Runnable { }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Animation 1#
        imageArray.add(imageView1)
        imageArray.add(imageView2)
        imageArray.add(imageView3)
        imageArray.add(imageView4)
        imageArray.add(imageView5)
        imageArray.add(imageView6)
        imageArray.add(imageView7)
        imageArray.add(imageView8)
        imageArray.add(imageView9)
        hideImages()

        // Count down timer
        object : CountDownTimer(15200, 1000){
            override fun onTick(p0: Long) {
                timeText.text = "Time: ${p0/1000}"
            }

            override fun onFinish() {
                handler.removeCallbacks(runnable)
                for (image in imageArray){
                    image.visibility = View.INVISIBLE
                }
                //Alert
                val alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Vaxt bitdi")
                alert.setMessage("Yenidən başlayaq? :D")
                alert.setPositiveButton("Aha"){dialog, which ->
                    //Restart main activity
                    val intent = intent
                    finish()
                    startActivity(intent)
                }
                alert.setNegativeButton("Yo"){dialog, which ->

                    Toast.makeText(this@MainActivity, "Oyun qutaydi :)", Toast.LENGTH_LONG).show()
                }
                alert.show()
            }

        }.start()

    }
    //Animation 2#
    fun hideImages(){

        runnable = object : Runnable{
            override fun run() {
                for (image in imageArray){
                    image.visibility = View.INVISIBLE
                }
                var random = Random()
                var randomIndex = random.nextInt(9)
                imageArray[randomIndex].visibility = View.VISIBLE
                handler.postDelayed(runnable, 500)
            }
        }
        handler.post(runnable)
    }

    fun upScore(view:View){
        score += 1
        scoreText.text = "Score: $score"
    }
}