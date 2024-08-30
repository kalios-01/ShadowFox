package com.kaliostech.mycalculator

import android.content.res.Configuration
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var tvDisplay: TextView
    private lateinit var themeSwitch: ImageView
    private var currentNumber = ""
    private var firstOperand = ""
    private var secondOperand = ""
    private var operator = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)



        tvDisplay = findViewById(R.id.tvDisplay)
        themeSwitch = findViewById(R.id.theme_switch)

        val buttonIds = arrayOf(
            R.id.btnOne, R.id.btnTwo, R.id.btnThree,
            R.id.btnFour, R.id.btnFive, R.id.btnSix,
            R.id.btnSeven, R.id.btnEight, R.id.btnNine,
            R.id.btnZero, R.id.btnAdd, R.id.btnSubtract,
            R.id.btnMultiply, R.id.btnDivide, R.id.btnEquals,
            R.id.btnClear, R.id.btnPlusMinus, R.id.btnPercentage,
            R.id.btnDot
        )

        for (id in buttonIds) {
            findViewById<TextView>(id).setOnClickListener {
                onButtonClick(it.id)
            }
        }
        if (isDarkModeActive()) {
            // Dark mode is active, set light image
            themeSwitch.setImageResource(R.drawable.dark_mode)
        } else {
            // Light mode is active, set dark image
            themeSwitch.setImageResource(R.drawable.light_mode)
        }
        // theme change
        themeSwitch.setOnClickListener {
            // Toggle between light and dark images
            if (isDarkModeActive()) {
                // Dark mode is active, set light image
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            } else {
                // Light mode is active, set dark image
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }
    }

    private fun onButtonClick(id: Int) {
        when (id) {
            R.id.btnOne -> appendNumber("1")
            R.id.btnTwo -> appendNumber("2")
            R.id.btnThree -> appendNumber("3")
            R.id.btnFour -> appendNumber("4")
            R.id.btnFive -> appendNumber("5")
            R.id.btnSix -> appendNumber("6")
            R.id.btnSeven -> appendNumber("7")
            R.id.btnEight -> appendNumber("8")
            R.id.btnNine -> appendNumber("9")
            R.id.btnZero -> appendNumber("0")
            R.id.btnAdd -> setOperator("+")
            R.id.btnSubtract -> setOperator("-")
            R.id.btnMultiply -> setOperator("*")
            R.id.btnDivide -> setOperator("/")
            R.id.btnEquals -> calculateResult()
            R.id.btnClear -> clear()
            R.id.btnPlusMinus -> toggleSign()
            R.id.btnPercentage -> calculatePercentage()
            R.id.btnDot -> appendDot()
        }
    }

    private fun appendNumber(number: String) {
        currentNumber += number
        tvDisplay.text = tvDisplay.text.toString() + number
    }

    private fun setOperator(op: String) {
        if (currentNumber.isNotEmpty()) {
            firstOperand = currentNumber
            currentNumber = ""
            operator = op
            tvDisplay.text = tvDisplay.text.toString() + " $op "
        }
    }


    private fun calculateResult() {
        if (firstOperand.isNotEmpty() && currentNumber.isNotEmpty()) {
            secondOperand = currentNumber
            val result = when (operator) {
                "+" -> firstOperand.toDouble() + secondOperand.toDouble()
                "-" -> firstOperand.toDouble() - secondOperand.toDouble()
                "*" -> firstOperand.toDouble() * secondOperand.toDouble()
                "/" -> firstOperand.toDouble() / secondOperand.toDouble()
                else -> 0.0
            }

            // Create a SpannableStringBuilder to format the text
            val inputText = "$firstOperand $operator $secondOperand"
            val resultText = "\n$result"

            val spannable = SpannableStringBuilder()
            val greyColor = ContextCompat.getColor(this, R.color.grey) // Assuming you have this color in your resources
            val whiteColor = ContextCompat.getColor(this, R.color.white)

            // Apply grey color to the input
            spannable.append(inputText)
            spannable.setSpan(
                ForegroundColorSpan(greyColor),
                0, inputText.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            // Apply white color to the result
            spannable.append(resultText)
            spannable.setSpan(
                ForegroundColorSpan(whiteColor),
                inputText.length, spannable.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            // Set the formatted text to the TextView
            tvDisplay.text = spannable

            currentNumber = result.toString()
            firstOperand = ""
            secondOperand = ""
            operator = ""
        }
    }

    private fun clear() {
        currentNumber = ""
        firstOperand = ""
        secondOperand = ""
        operator = ""
        tvDisplay.text = "0"
    }

    private fun toggleSign() {
        if (currentNumber.isNotEmpty()) {
            currentNumber = if (currentNumber.startsWith("-")) {
                currentNumber.substring(1)
            } else {
                "-$currentNumber"
            }
            tvDisplay.text = currentNumber
        }
    }

    private fun calculatePercentage() {
        if (currentNumber.isNotEmpty()) {
            val percentage = currentNumber.toDouble() / 100
            currentNumber = percentage.toString()
            tvDisplay.text = currentNumber
        }
    }

    private fun appendDot() {
        if (!currentNumber.contains(".")) {
            if (currentNumber.isEmpty()) {
                currentNumber = "0."
            } else {
                currentNumber += "."
            }
            tvDisplay.text = currentNumber
        }
    }
    private fun isDarkModeActive(): Boolean {
        return when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES -> true
            else -> false
        }
    }
}
