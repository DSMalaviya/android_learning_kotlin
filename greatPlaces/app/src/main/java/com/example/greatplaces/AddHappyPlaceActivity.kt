package com.example.greatplaces

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.greatplaces.databinding.ActivityAddHappyPlaceBinding
import com.example.greatplaces.databinding.ActivityMainBinding

class AddHappyPlaceActivity : AppCompatActivity() {

    private var binding:ActivityAddHappyPlaceBinding?=null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddHappyPlaceBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarAddPlace)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding?.toolbarAddPlace?.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}