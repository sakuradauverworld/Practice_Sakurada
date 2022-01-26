package com.example.countrecord

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.countrecord.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var number = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)

        binding.plusButton.setOnClickListener {
            number += 1
            var stringNumber = number.toString()
            binding.textView.text = stringNumber
        }

        binding.minusButton.setOnClickListener {
            if (0 < number) {
                number -= 1
                var stringNumber = number.toString()
                binding.textView.text = stringNumber
            }
        }
    }
}