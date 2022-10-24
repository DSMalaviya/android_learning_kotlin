package com.example.coroutiines

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    var customProgressDialog:Dialog?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonExecute: Button = findViewById(R.id.button);
        buttonExecute.setOnClickListener {
            showProgressDialog()
            lifecycleScope.launch {
                execute("Tasked executed sucessfully")
            }
        }
    }

    private suspend fun execute(result: String) {
        withContext(Dispatchers.IO) {
            for (i in 1..100000) {
                Log.e("delay", "" + i)
            }
            runOnUiThread {
                cancelProgressDialog()
                Toast.makeText(this@MainActivity, result, Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun cancelProgressDialog(){
        customProgressDialog?.cancel()
    }

    private fun showProgressDialog(){
        customProgressDialog= Dialog(this@MainActivity)
        customProgressDialog?.setContentView(R.layout.custom_progress_bar)
        customProgressDialog?.show()
    }
}