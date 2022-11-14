package com.example.sevenminworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.lifecycle.lifecycleScope
import com.example.sevenminworkout.Database.HistryDao
import com.example.sevenminworkout.Database.HistryEntity
import com.example.sevenminworkout.databinding.ActivityFinishBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class FinishActivity : AppCompatActivity() {
    private var binding:ActivityFinishBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarFinishActivity)
        if(supportActionBar!=null){
           supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolbarFinishActivity?.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        binding?.btnFinish?.setOnClickListener {
            finish()
        }

        val dao=(application as WorkOutApp).db.histryDao()
        addToDatabase(dao)
    }

    private fun addToDatabase(histryDao: HistryDao){
        val c:Calendar=Calendar.getInstance()
        val dateTime=c.time
        Log.e("Date :","$dateTime")

        val sdf=SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault())
        val date=sdf.format(dateTime)

        lifecycleScope.launch {
            histryDao.insert(HistryEntity(date))
            Log.e("entry added","Entry added")
        }
    }
}