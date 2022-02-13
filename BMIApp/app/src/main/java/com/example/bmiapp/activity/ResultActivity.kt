package com.example.bmiapp.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bmiapp.R
import com.example.bmiapp.databinding.ActivityMainBinding
import com.example.bmiapp.databinding.ActivityResultBinding
import model.BmiInfo

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bmiInfo = intent.getSerializableExtra("BMI_INFO")

        binding.bmi.text = "%.1f".format(bmiInfo).toString()

        when (bmiInfo) {
            "LEPTOSOMACTIC" -> binding.bodyType.text = "痩せ型"
            "STANDARD"  -> binding.bodyType.text = "標準"
            "OBESITY"  -> binding.bodyType.text = "肥満"
        }

        when (bmiInfo) {
            "LEPTOSOMACTIC" -> binding.bmiDescription.text = "あなたは痩せ気味な体重です。\n現状の体重を増やし健康的な体型\n目指してください。"
            "STANDARD"  -> binding.bmiDescription.text = "あなたは標準的な体重です。\n現状の体重を維持しましょう。"
            "OBESITY"  -> binding.bmiDescription.text = "あなたは肥満です。\n現状の体重を減らし健康的な体型を\n目指してください。"
        }

        binding.backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}