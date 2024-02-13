package com.example.roberthannontemperatureconverter

import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val celsiusSeekBar = findViewById<SeekBar>(R.id.celsiusSeekBar)
        val fahrenheitSeekBar = findViewById<SeekBar>(R.id.fahrenheitSeekBar)
        val celsiusValueTextView = findViewById<TextView>(R.id.celsiusValue)
        val fahrenheitValueTextView = findViewById<TextView>(R.id.fahrenheitValue)

        celsiusSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val fahrenheitValue = celsiusToFahrenheit(progress)
                fahrenheitSeekBar.progress = fahrenheitValue
                celsiusValueTextView.text = "$progress"
                fahrenheitValueTextView.text = "$fahrenheitValue"
                setMessageText(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })

        fahrenheitSeekBar.apply {
            max = 212
            progress = 32
            setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    val fahrenheitValue = if (progress < 32) 32 else progress
                    val celsiusValue = fahrenheitToCelsius(fahrenheitValue)
                    fahrenheitSeekBar.progress = fahrenheitValue
                    celsiusSeekBar.progress = celsiusValue
                    celsiusValueTextView.text = "$celsiusValue"
                    fahrenheitValueTextView.text = "$fahrenheitValue"
                    setMessageText(celsiusValue)
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                }

            })
        }
    }

    private fun celsiusToFahrenheit(celsius: Int): Int {
        return (celsius * 9 / 5) + 32
    }

    private fun fahrenheitToCelsius(fahrenheit: Int): Int {
        return ((fahrenheit - 32) * 5 / 9)
    }

    private fun setMessageText(celsiusValue: Int) {
        val messageTextView = findViewById<TextView>(R.id.messageTextView)
        val message = if (celsiusValue <= 20) {
            "I wish it were warmer."
        } else {
            "I wish it were colder."
        }
        messageTextView.text = message
    }
}
