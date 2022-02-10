package com.example.bmiapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import com.example.bmiapp.databinding.ActivityMainBinding
import android.text.TextWatcher
import com.example.bmiapp.R
import model.BmiCalculation

open class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var height = ""
    var weight = ""
    private var heightMatched = true
    private var weightMatched = false
    var bmiCalculation = BmiCalculation()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val measurementButton: Button =findViewById(R.id.measurementButton)
        val resetButton: Button =findViewById(R.id.resetButton)

        measurementButton.isEnabled = false
        resetButton.isEnabled = false

        binding.heightEditTextNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                val regex = Regex(pattern = "[\\d]")

                height = binding.heightEditTextNumber.text.toString()
                heightMatched = regex.containsMatchIn(height)

                //入力欄が記号のみだと測定ボタンを押せないようにする。
                measurementButton.isEnabled = heightMatched == weightMatched

                //身長体重両方入力されていないと測定ボタンを押せないようにする。
                if (height == "" || weight == "") {
                    measurementButton.isEnabled = false
                }

                resetButton.isEnabled = !(height == "" && weight == "")
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

                //入力欄が記号のみだと測定ボタンを押せないようにする。
                measurementButton.isEnabled = heightMatched == weightMatched

                //身長体重両方入力されていないと測定ボタンを押せないようにする。
                if (height == "" || weight == "") {
                    measurementButton.isEnabled = false
                }

                resetButton.isEnabled = !(height == "" && weight == "")
            }
        })

        binding.measurementButton.setOnClickListener {
            val height = height.toDouble()
            val weight = weight.toDouble()
            val bmiCalculation = bmiCalculation.calculate(height,weight )
            val intent = Intent(this, ResultActivity::class.java)

            intent.putExtra("BMI",bmiCalculation.bmi)
            intent.putExtra("BODY_TYPE",bmiCalculation.bodyType)
            startActivity(intent)
        }

        binding.resetButton.setOnClickListener {
            binding.heightEditTextNumber.text = null
            binding.weightEditTextNumber.text = null

            measurementButton.isEnabled = false
        }
    }
}