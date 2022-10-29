package com.example.sevenminworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.sevenminworkout.databinding.ActivityBmiactivityBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {
    companion object{
        private const val MATRIC_UNITS_VIEW="Matric units view"
        private const val US_UNITS_VIEW="Us units view"
    }

    private var currentVisibleView= MATRIC_UNITS_VIEW

    private var binding: ActivityBmiactivityBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiactivityBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarBmiActivity)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "CALCULATE BMI"
        }
        binding?.toolbarBmiActivity?.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        makeVisibleMatricUnitsView()

        binding?.rgUnits?.setOnCheckedChangeListener { _, checkedId ->
            if(checkedId==R.id.rbMatricUnits){
                makeVisibleMatricUnitsView()
            }else{
                makeVisibleUScUnitsView()
            }
        }


        binding?.btnCalculateUnits?.setOnClickListener {
            calculateBMI()

        }
    }

    private fun calculateBMI(){
        if (currentVisibleView == MATRIC_UNITS_VIEW){
            if (validateMetricUnits()) {
                val heightValue: Float =
                    binding?.etMetricUnitHeight?.text.toString().toFloat() / 100
                val weightValue: Float = binding?.etMetricUnitWeight?.text.toString().toFloat()
                val bmi = weightValue / (heightValue * heightValue)
                diaplayBmiResult(bmi)
            } else {
                Toast.makeText(this@BMIActivity, "Please enter all values", Toast.LENGTH_SHORT)
                    .show()
            }
        }else if(currentVisibleView== US_UNITS_VIEW){
            if(validateUSUnits()){
                val usUnitHeightValueFeet: String =
                    binding?.etUsMetricUnitHeightFeet?.text.toString() // Height Feet value entered in EditText component.
                val usUnitHeightValueInch: String =
                    binding?.etUsMetricUnitHeightInch?.text.toString() // Height Inch value entered in EditText component.
                val usUnitWeightValue: Float = binding?.etUsMetricUnitWeight?.text.toString()
                    .toFloat() // Weight value entered in EditText component.

                // Here the Height Feet and Inch values are merged and multiplied by 12 for converting it to inches.
                val heightValue =
                    usUnitHeightValueInch.toFloat() + usUnitHeightValueFeet.toFloat() * 12

                val bmi=703*(usUnitWeightValue/(heightValue*heightValue))
                diaplayBmiResult(bmi)
            }else{
                Toast.makeText(this@BMIActivity, "Please enter all values", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun makeVisibleMatricUnitsView(){
        currentVisibleView= MATRIC_UNITS_VIEW
        binding?.tilMetricUnitHeight?.visibility=View.VISIBLE
        binding?.tilMetricUnitWeight?.visibility=View.VISIBLE
        binding?.tilMetricUsUnitHeightFeet?.visibility=View.INVISIBLE
        binding?.tilUsMetricUnitWeight?.visibility=View.INVISIBLE
        binding?.tilMetricUsUnitHeightInch?.visibility=View.INVISIBLE

        binding?.etMetricUnitHeight?.text?.clear()
        binding?.etMetricUnitWeight?.text?.clear()

        binding?.llDisplayBMIResult?.visibility=View.INVISIBLE
    }

    private fun makeVisibleUScUnitsView(){
        currentVisibleView= US_UNITS_VIEW
        binding?.tilMetricUnitHeight?.visibility=View.INVISIBLE
        binding?.tilMetricUnitWeight?.visibility=View.INVISIBLE
        binding?.tilMetricUsUnitHeightFeet?.visibility=View.VISIBLE
        binding?.tilUsMetricUnitWeight?.visibility=View.VISIBLE
        binding?.tilMetricUsUnitHeightInch?.visibility=View.VISIBLE

        binding?.etUsMetricUnitHeightFeet?.text?.clear()
        binding?.etUsMetricUnitWeight?.text?.clear()
        binding?.etUsMetricUnitHeightInch?.text?.clear()

        binding?.llDisplayBMIResult?.visibility=View.INVISIBLE
    }

    private fun diaplayBmiResult(bmi: Float) {
        val bmiLabel: String
        val bmiDescription: String

        if (bmi.compareTo(15f) <= 0) {
            bmiLabel = "Very severely underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(15f) > 0 && bmi.compareTo(16f) <= 0
        ) {
            bmiLabel = "Severely underweight"
            bmiDescription = "Oops!You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <= 0
        ) {
            bmiLabel = "Underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <= 0
        ) {
            bmiLabel = "Normal"
            bmiDescription = "Congratulations! You are in a good shape!"
        } else if (bmi.compareTo(25f) > 0 && bmi.compareTo(30f) <= 0
        ) {
            bmiLabel = "Overweight"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        } else if (bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <= 0
        ) {
            bmiLabel = "Obese Class | (Moderately obese)"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        } else if (bmi.compareTo(35f) > 0 && bmi.compareTo(40f) <= 0
        ) {
            bmiLabel = "Obese Class || (Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        } else {
            bmiLabel = "Obese Class ||| (Very Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        }

        binding?.llDisplayBMIResult?.visibility = View.VISIBLE
        val bmiValue = BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()
        binding?.tvBMIValue?.text = bmiValue
        binding?.tvBMIType?.text = bmiLabel
        binding?.tvBMIDescription?.text = bmiDescription
    }

    private fun validateMetricUnits(): Boolean {
        var isValid = true
        if (binding?.etMetricUnitWeight?.text.toString().isEmpty()) {
            isValid = false
        } else if (binding?.etMetricUnitHeight?.text.toString().isEmpty()) {
            isValid = false
        }
        return isValid
    }

    private fun validateUSUnits():Boolean{
        var isValid=true
        if (binding?.etUsMetricUnitHeightFeet?.text.toString().isEmpty()){
            isValid=false
        }else if(binding?.etUsMetricUnitWeight?.text.toString().isEmpty()){
            isValid=false
        }else if(binding?.etUsMetricUnitHeightInch?.text.toString().isEmpty()){
            isValid=false
        }
        return isValid
    }
}