package com.example.sevenminworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sevenminworkout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding:ActivityMainBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        binding?.flStart?.setOnClickListener{
            //Toast.makeText(this@MainActivity,"Here we will start the exercise",Toast.LENGTH_LONG).show()
            val intent=Intent(this,ExerciseActivity::class.java)
            startActivity(intent)
        }

        binding?.flBMI?.setOnClickListener{
            val intent=Intent(this,BMIActivity::class.java)
            startActivity(intent)
        }

        binding?.flHistry?.setOnClickListener {
            val intent=Intent(this,HistryActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding=null
    }
}