package com.dawmlab.tema1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val exitButton = findViewById<View>(R.id.exitButton)
        exitButton.setOnClickListener {
            finish()
        }

        val fragment1 = Fragment_1()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.FrLayout, fragment1)
        transaction.commit()

    }


}