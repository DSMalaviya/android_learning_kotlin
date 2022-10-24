package com.example.dialogs_demo

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    var snackbarBtn: Button? = null
    var btnAlertDialog: Button? = null
    var btnCustomDialog:Button?=null
    var btnCustomProgess:Button?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        snackbarBtn = findViewById(R.id.btn_snackBar)
        snackbarBtn!!.setOnClickListener {
            Snackbar.make(it, "You have clicked button", Snackbar.LENGTH_LONG).show()
        }

        btnAlertDialog = findViewById(R.id.btn_alertDialog)
        btnAlertDialog!!.setOnClickListener {
            alertDialogFunction()
        }

        btnCustomDialog=findViewById(R.id.btn_customDialog)
        btnCustomDialog!!.setOnClickListener {
            customDialogFunction()
        }

        btnCustomProgess=findViewById(R.id.btn_custom_progress)
        btnCustomProgess!!.setOnClickListener {
            customProgessFunction()
        }
    }

    private fun customProgessFunction() {
        val customProgressDialog=Dialog(this)
        customProgressDialog.setContentView(R.layout.dialog_custom_progress)
        customProgressDialog.show()
    }

    private fun customDialogFunction() {
        val customDialog=Dialog(this)
        customDialog.setContentView( R.layout.dialog_custom)
        val tvSubmit= customDialog.findViewById<TextView>(R.id.tv_submit)
        tvSubmit.setOnClickListener{
            Toast.makeText(applicationContext,"clicked submit",Toast.LENGTH_SHORT).show()
            customDialog.dismiss()
        }

        val tvCancel= customDialog.findViewById<TextView>(R.id.tv_cancel)
        tvCancel.setOnClickListener{
            Toast.makeText(applicationContext,"clicked cancel",Toast.LENGTH_SHORT).show()
            customDialog.dismiss()
        }
        customDialog.show()
    }

    private fun alertDialogFunction() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Alert")
        builder.setMessage("This is Alert Dialog,which is used to show dialog")
        builder.setIcon(androidx.constraintlayout.widget.R.drawable.notification_icon_background)
        builder.setPositiveButton("Yes") { dialogInterface, which ->
            Toast.makeText(applicationContext, "clicked yes", Toast.LENGTH_LONG).show()
            dialogInterface.dismiss()
        }
        builder.setNeutralButton("Cancel") { dialogInterface, which ->
            Toast.makeText(applicationContext, "clicked cancel", Toast.LENGTH_LONG).show()
            dialogInterface.dismiss()
        }
        builder.setNegativeButton("No") { dialogInterface, which ->
            Toast.makeText(applicationContext, "clicked no", Toast.LENGTH_LONG).show()
            dialogInterface.dismiss()
        }
        val alertDialog:AlertDialog=builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }
}