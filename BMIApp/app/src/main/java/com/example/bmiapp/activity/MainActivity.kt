package com.example.bmiapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import com.example.bmiapp.databinding.ActivityMainBinding
import android.text.TextWatcher
import com.example.bmiapp.R

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var height = ""
    private var weight = ""
    private var heightMatched = false
    private var weightMatched = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)

        val measurementButton: Button =findViewById(R.id.measurementButton)
        val resetButton: Button =findViewById(R.id.resetButton)
        measurementButton.isEnabled = false

        binding.heightEditTextNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                height = binding.heightEditTextNumber.text.toString()
                val regex = Regex(pattern = "[\\d]")
                heightMatched = regex.containsMatchIn(height)

                measurementButton.isEnabled = heightMatched == weightMatched
            }
        })

        binding.weightEditTextNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                weight = binding.weightEditTextNumber.text.toString()
                val regex = Regex(pattern = "[\\d]")
                weightMatched = regex.containsMatchIn(weight)

                measurementButton.isEnabled = heightMatched == weightMatched
            }
        })

        binding.measurementButton.setOnClickListener {

        }
    }
}