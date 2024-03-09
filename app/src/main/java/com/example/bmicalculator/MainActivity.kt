package com.example.bmicalculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val weightText = findViewById<EditText>(R.id.etweight)
        val heightText = findViewById<EditText>(R.id.etheight)
        val calcbutton = findViewById<Button>(R.id.btncalc)
        calcbutton.setOnClickListener {
            val weight = weightText.text.toString()
            val height = heightText.text.toString()
            if (validate(weight, height)) {
                val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))
                val formatbmi = String.format("%.2f", bmi).toFloat()
                displayBmi(formatbmi)
            }
        }
    }

    private fun validate(weight: String?, height: String?): Boolean {
        when {
            weight.isNullOrEmpty() -> {
                Toast.makeText(this, "Please enter weight details", Toast.LENGTH_LONG).show()
                return false
            }

            height.isNullOrEmpty() -> {
                Toast.makeText(this, "Please enter height details", Toast.LENGTH_LONG).show()
                return false
            }

            else -> {
                return true

            }
        }
    }

    private fun displayBmi(formatbmi: Float) {
        val index = findViewById<TextView>(R.id.tvindex)
        val result = findViewById<TextView>(R.id.tvresult)
        val info = findViewById<TextView>(R.id.tvinfo)
        index.text = formatbmi.toString()
        info.text = "(Normal range is 18.5 to 24.9)"
        val resulttext:String
        val color: Int
        when (formatbmi){
              in 0.00..18.5 -> {
                resulttext = "UNDER WEIGHT"
                color = R.color.underweight
            }

             in 18.5..24.99 -> {
                resulttext = "NORMAL"
                color = R.color.normal
            }

            in 25.00..29.99 -> {
                resulttext = "OVERWEIGHT"
                color = R.color.overweight
            }

             else  -> {
                resulttext = "OBESE"
                color = R.color.obese
            }
        }
        result.setTextColor(ContextCompat.getColor(this, color))
        result.text = resulttext

    }

}