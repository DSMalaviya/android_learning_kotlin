package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    //Repreasent whether the lastly pressed key is numeric or not
    var lastNumeric: Boolean = false;

    //If true don't allow to add second dot
    var lastDot: Boolean = false;

    private var tvInput: TextView? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInput = findViewById(R.id.tvInput)
    }

    fun onDigit(view: View) {
        tvInput?.append(
            (view as Button).text
        )
        lastNumeric = true
    }

    fun onOperator(view: View) {
        tvInput?.text?.let {
            if (lastNumeric && !isOperatorAdded(it.toString())) {
                tvInput?.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }
        }
    }

    fun onClear(view: View) {
        tvInput?.text = ""
        lastNumeric = false
        lastDot = false
    }


    fun onEqual(view: View) {
        if (lastNumeric) {
            var tvValue = tvInput?.text.toString()
            var prefix = ""
            try {
                if (tvValue.startsWith("-")) {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }

                when {
                    tvValue.contains("/") -> {
                        val splitedValue = tvValue.split("/")
                        var one = splitedValue[0]
                        var two = splitedValue[1]

                        if (prefix.isNotEmpty()) {
                            one = prefix + one
                        }
                        tvInput?.text =
                            removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                    }
                    tvValue.contains("*") -> {
                        val splitedValue = tvValue.split("*")
                        var one = splitedValue[0]
                        var two = splitedValue[1]

                        if (prefix.isNotEmpty()) {
                            one = prefix + one
                        }
                        tvInput?.text =
                            removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                    }
                    tvValue.contains("+") -> {
                        val splitedValue = tvValue.split("+")
                        var one = splitedValue[0]
                        var two = splitedValue[1]

                        if (prefix.isNotEmpty()) {
                            one = prefix + one
                        }
                        tvInput?.text =
                            removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                    }
                    tvValue.contains("-") -> {
                        val splitedValue = tvValue.split("-")
                        var one = splitedValue[0]
                        var two = splitedValue[1]

                        if (prefix.isNotEmpty()) {
                            one = prefix + one
                        }
                        println(one);
                        tvInput?.text =
                            removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())

                    }


                }
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
    }

    fun onDecimalPoint(view: View) {
        if (lastNumeric && !lastDot) {
            tvInput?.append(".")
            lastNumeric = false
            lastDot = true
        }
    }


    private fun removeZeroAfterDot(result: String): String {
        var value = result
        if (result.contains(".0")) {
            value = result.substring(0, result.length - 2)
        }
        return value;
    }

    private fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            (value.contains("/") || value.contains("*") || value.contains("-") || value.contains("+"))
        }
    }
}