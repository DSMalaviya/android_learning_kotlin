package com.example.sevenminworkout

import HistryAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sevenminworkout.Database.HistryDao
import com.example.sevenminworkout.databinding.ActivityHistryBinding
import kotlinx.coroutines.launch

class HistryActivity : AppCompatActivity() {
    private var binding:ActivityHistryBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHistryBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolbarHistoryActivity)
        val dao=(application as WorkOutApp).db.histryDao()
        getAllCompletedDates(dao)
    }

    override fun onDestroy() {
        binding=null
        super.onDestroy()
    }

    private fun getAllCompletedDates(histryDao: HistryDao){
        lifecycleScope.launch {
            histryDao.fetchAllDates().collect{allCompletedDatesList->
               if(allCompletedDatesList.isNotEmpty()){
                    binding?.tvHistory?.visibility=View.VISIBLE
                   binding?.rvHistory?.visibility=View.VISIBLE
                   binding?.tvNoDataAvailable?.visibility=View.GONE

                   binding?.rvHistory?.layoutManager=LinearLayoutManager(this@HistryActivity)
                   val dates=ArrayList<String>()
                   for(i in allCompletedDatesList){
                       dates.add(i.Date)
                   }
                   val histryAdapter=HistryAdapter(dates)
                   binding?.rvHistory?.adapter=histryAdapter
               }else{
                   binding?.tvHistory?.visibility=View.GONE
                   binding?.rvHistory?.visibility=View.GONE
                   binding?.tvNoDataAvailable?.visibility=View.VISIBLE
               }
            }
        }
    }
}