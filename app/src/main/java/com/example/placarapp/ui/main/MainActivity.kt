package com.example.placarapp.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.placarapp.R
import com.example.placarapp.ui.game.GameActivity
import kotlinx.android.synthetic.main.activity_main.btExit
import kotlinx.android.synthetic.main.activity_main.btNewGame

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btNewGame.setOnClickListener {
            val nextScreen = Intent(this, GameActivity::class.java)
            startActivity(nextScreen)
        }

        btExit.setOnClickListener {
            finish()
        }

    }


}
