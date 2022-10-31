package com.example.roomdatabasedemo

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdatabasedemo.databinding.ActivityMainBinding
import com.example.roomdatabasedemo.databinding.DialogUpdateBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val employeeDao = (application as EmployeeApp).db.employeeDao()
        binding?.btnAdd?.setOnClickListener {
            addRecord(employeeDao)
        }

        lifecycleScope.launch {
            employeeDao.fetchAllEmpolyee().collect{
                Log.d("Len", "onCreate: ${it.size}")
                val arrList= ArrayList(it)
                setupListofDataInRecyclerView(arrList,employeeDao)
            }
        }

    }

    private fun setupListofDataInRecyclerView(employeeList:ArrayList<EmployeeEntity>,employeeDao: EmployeeDao)
    {
        if(employeeList.isNotEmpty()){
            val itemAdapter=ItemAdapter(employeeList,{
                updateId->updateRecordDialog(updateId,employeeDao)
            },{
                deleteitem->deleteRecordDialog(deleteitem,employeeDao)
            });
            binding?.rvItemsList?.adapter=itemAdapter
            binding?.rvItemsList?.layoutManager=LinearLayoutManager(this)
            binding?.rvItemsList?.visibility=View.VISIBLE
            binding?.tvNoRecordsAvailable?.visibility=View.GONE
        }else{
            binding?.rvItemsList?.visibility=View.GONE
            binding?.tvNoRecordsAvailable?.visibility=View.VISIBLE
        }
    }

    private fun deleteRecordDialog(deleteitem: EmployeeEntity, employeeDao: EmployeeDao) {
        val builder=AlertDialog.Builder(this);
        builder.setTitle("Delete Record")
        builder.setMessage("Are you want to sure you want to delete this item?")
        builder.setPositiveButton("Yes",){
            dialogInterface,_->
            lifecycleScope.launch {
                employeeDao.delete(deleteitem)
                Toast.makeText(this@MainActivity,"Record deleted",Toast.LENGTH_LONG).show()
                dialogInterface.dismiss()
            }
        }
        builder.setNegativeButton("No"){
            dialogInterface,_->
            dialogInterface.dismiss()
        }
        val alertDialog:AlertDialog=builder.create()
        alertDialog.show()
    }

    private fun updateRecordDialog(updateId: Int, employeeDao: EmployeeDao) {
        val updateDialog=Dialog(this,R.style.Theme_Dialog)
        updateDialog.setCancelable(false)
        val binding=DialogUpdateBinding.inflate(layoutInflater)
        updateDialog.setContentView(binding.root)

        lifecycleScope.launch {
            employeeDao.fetchEmployeeById(updateId).collect{
                if(it!=null){
                    binding.etUpdateName.setText(it.name)
                    binding.etUpdateEmailId.setText(it.email)
                }
            }
        }

        binding.tvUpdate.setOnClickListener {
            val name=binding.etUpdateName.text.toString()
            val email=binding.etUpdateEmailId.text.toString()
            if(name.isNotEmpty() && email.isNotEmpty()){
                lifecycleScope.launch {
                    employeeDao.update(EmployeeEntity(id = updateId,name,email))
                    Toast.makeText(this@MainActivity,"Record updated",Toast.LENGTH_LONG).show()
                    updateDialog.dismiss()
                }
            }else{
                Toast.makeText(this@MainActivity,"Please fill data",Toast.LENGTH_LONG).show()
            }
        }

        binding.tvCancel.setOnClickListener {
            updateDialog.dismiss()
        }

        updateDialog.show()
    }


    private fun addRecord(employeeDao: EmployeeDao) {
        val name = binding?.etName?.text.toString()
        val email = binding?.etEmailId?.text.toString()

        if (name.isNotEmpty() && email.isNotEmpty()) {


            lifecycleScope.launch {
                employeeDao.insert(EmployeeEntity(name = name, email = email))
                Toast.makeText(applicationContext, "Record saved", Toast.LENGTH_LONG).show()
                binding?.etName?.text?.clear()
                binding?.etEmailId?.text?.clear()
            }
        } else {
            Toast.makeText(applicationContext, "Name or email can not be blanks", Toast.LENGTH_LONG)
                .show()
        }
    }
}