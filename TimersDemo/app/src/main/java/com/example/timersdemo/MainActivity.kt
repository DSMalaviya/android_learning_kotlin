package com.example.timersdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import com.example.timersdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding:ActivityMainBinding?=null
    private var countDownTimer:CountDownTimer?=null
    private var timeDuration:Long=60000
    private var pauseOffset:Long=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding?.root)

        binding?.tvTimerText?.text = (timeDuration/1000).toString()
        binding?.btnStart?.setOnClickListener { 
            startTimer(pauseOffset)
        }
        
        binding?.btnPause?.setOnClickListener { 
            pauseTimer()
        }
        
        binding?.btnStop?.setOnClickListener { 
            resetTimer()
        }
    }

    private fun resetTimer() {
        if(countDownTimer!=null){
            countDownTimer?.cancel()
            binding?.tvTimerText?.text=(timeDuration/1000).toString()
            countDownTimer=null
            pauseOffset=0
        }
    }

    private fun pauseTimer() {
       if(countDownTimer!=null){
           countDownTimer?.cancel()
       }
    }

    private fun startTimer(pauseOffsetL: Long) {
        countDownTimer=object :CountDownTimer(timeDuration-pauseOffsetL,1000){
            override fun onTick(millisUntilFinished: Long) {
                pauseOffset=timeDuration-millisUntilFinished
                binding?.tvTimerText?.text = (millisUntilFinished/1000).toString()
            }

            override fun onFinish() {
                Toast.makeText(this@MainActivity,"Timer is finshed",Toast.LENGTH_SHORT).show()
            }
        }.start()
    }
}