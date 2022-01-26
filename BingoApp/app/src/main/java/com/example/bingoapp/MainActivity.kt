package com.example.bingoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import com.example.bingoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val bingo = BingoManager()
    private val startTime:Long = 10000 //10秒

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)

        val startButton: Button =findViewById(R.id.startButton)
        val stopButton: Button =findViewById(R.id.stopButton)

        val timer = object : CountDownTimer(startTime,100) {
            override fun onTick(p0: Long) {
                if (bingo.drawingBox.size == 0) {
                    binding.lotteryResultText.text = ""
                } else {
                    binding.lotteryResultText.text = bingo.drawingBox.random().toString()
                }

                startButton.isEnabled = false
            }

            override fun onFinish() {
                binding.lotteryResultText.text = ""
            }
        }

        binding.startButton.setOnClickListener {
            timer.start()
            stopButton.isEnabled = true

            if (bingo.drawingBox.size == 0) {
                stopButton.isEnabled = false
            }
        }

        binding.stopButton.setOnClickListener {
            if (!(startButton.isEnabled)) {
                timer.cancel()
                startButton.isEnabled = true
                stopButton.isEnabled = false

                val callNumber = bingo.callNumber()
                val lotteryResult = BingoManager.LotteryResult(callNumber.isNextFlag, callNumber.number)
                binding.lotteryResultText.text = lotteryResult.number.toString()

                val bingoHistory = bingo.bingoHistory()
                val stringBingoHistory = bingoHistory.toString().replace("[", "").replace("]", "")
                binding.bingoHistoryText.text = stringBingoHistory

                if (!(lotteryResult.isNextFlag)) {
                    stopButton.isEnabled = false
                }
            }
        }
    }
}

