package com.example.auriapp

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.net.toUri
import java.util.Calendar

class PreorderInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_preorder_info)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // timer
        val txtTimer = findViewById<TextView>(R.id.text_countDownTimer)

        val targetDate = Calendar.getInstance()
        targetDate.set(2025, Calendar.JULY, 22)
        val timeLeftInMillis = targetDate.timeInMillis - System.currentTimeMillis()

        object : CountDownTimer(timeLeftInMillis, 1000){
            override fun onTick(millisUntilFinished: Long) {
                val days = millisUntilFinished / (1000 * 60 * 60 * 24)
                val hours = (millisUntilFinished / (1000 * 60 * 60)) % 24
                val minutes = (millisUntilFinished / (1000 * 60)) % 60
                val seconds = (millisUntilFinished / 1000) % 60

                txtTimer.text = "$days días, $hours h $minutes m $seconds s"
            }

            override fun onFinish() {
                txtTimer.text = "¡Ya a la venta!"
            }
        }.start()

        // open link in browser
        val btnShop = findViewById<Button>(R.id.btn_shop)
        btnShop.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW,
                "https://yenpress.com/titles/9798400903526-omniscient-reader-s-viewpoint-novel-vol-1".toUri())
            startActivity(intent)
        }

        // go back to previous screen
        val btnBack = findViewById<ImageButton>(R.id.btn_back)
        btnBack.setOnClickListener {
            finish()
        }
    }
}