package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Stack

class MainActivity : AppCompatActivity() {

    private lateinit var displayTextView: TextView
    private var expression = StringBuilder()
    private var isCalculated = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        displayTextView = findViewById(R.id.displayTextView)

        setNumberListeners()
        setOperatorListeners()
        setUtilityButtons()
    }

    private fun setNumberListeners() {
        val numberButtons = listOf<Button>(
            findViewById(R.id.btn0), findViewById(R.id.btn1),
            findViewById(R.id.btn2), findViewById(R.id.btn3),
            findViewById(R.id.btn4), findViewById(R.id.btn5),
            findViewById(R.id.btn6), findViewById(R.id.btn7),
            findViewById(R.id.btn8), findViewById(R.id.btn9),
            findViewById(R.id.btnDot)
        )

        for (button in numberButtons) {
            button.setOnClickListener {
                if (isCalculated) {
                    expression.clear()
                    isCalculated = false
                }
                expression.append(button.text)
                updateDisplay()
            }
        }
    }

    private fun setOperatorListeners() {
        val operatorButtons = listOf<Button>(
            findViewById(R.id.btnAdd), findViewById(R.id.btnSubtract),
            findViewById(R.id.btnMultiply), findViewById(R.id.btnDivide)
        )

        for (button in operatorButtons) {
            button.setOnClickListener {
                if (isCalculated) {
                    isCalculated = false
                }
                if (expression.isNotEmpty() && "+-/*".none { it == expression.last() }) {
                    expression.append(button.text)
                    updateDisplay()
                }
            }
        }

        val btnEquals: Button = findViewById(R.id.btnEquals)
        btnEquals.setOnClickListener { calculateResult() }
    }

    private fun setUtilityButtons() {
        val btnCE: Button = findViewById(R.id.btnCE)
        btnCE.setOnClickListener {
            expression.clear()
            updateDisplay()
        }

        val btnC: Button = findViewById(R.id.btnC)
        btnC.setOnClickListener {
            if (expression.isNotEmpty()) {
                expression.deleteCharAt(expression.length - 1)
                updateDisplay()
            }
        }

        val btnBS: Button = findViewById(R.id.btnBS)
        btnBS.setOnClickListener {
            if (expression.isNotEmpty()) {
                expression.deleteCharAt(expression.length - 1)
                updateDisplay()
            }
        }

        val btnPlusMinus: Button = findViewById(R.id.btnPlusMinus)
        btnPlusMinus.setOnClickListener {
            if (expression.isNotEmpty() && expression.last().isDigit()) {
                val currentExpression = expression.toString()
                val lastNumber = currentExpression.split(Regex("[+\\-*/]")).last()
                val start = currentExpression.length - lastNumber.length
                expression.replace(start, currentExpression.length, (-lastNumber.toDouble()).toString())
                updateDisplay()
            }
        }
    }

    private fun updateDisplay() {
        if (expression.isEmpty()) {
            displayTextView.text = "0"
        } else {
            displayTextView.text = expression.toString()
        }
    }

    private fun calculateResult() {
        try {
            val result = eval(expression.toString())

            // Kiểm tra nếu kết quả là số nguyên (vd: 42.0 -> 42)
            if (result == result.toLong().toDouble()) {
                displayTextView.text = result.toLong().toString()
            } else {
                displayTextView.text = result.toString()
            }

            isCalculated = true
        } catch (e: Exception) {
            displayTextView.text = "Error"
            expression.clear()
        }
    }

    private fun eval(expression: String): Double {
        val tokens = expression.toCharArray()
        val values = Stack<Double>()
        val operators = Stack<Char>()

        var i = 0
        while (i < tokens.size) {
            if (tokens[i] == ' ') {
                i++
                continue
            }

            if (tokens[i] in '0'..'9' || tokens[i] == '.') {
                val buffer = StringBuffer()
                while (i < tokens.size && (tokens[i] in '0'..'9' || tokens[i] == '.')) {
                    buffer.append(tokens[i++])
                }
                values.push(buffer.toString().toDouble())
                i--
            } else if (tokens[i] == '(') {
                operators.push(tokens[i])
            } else if (tokens[i] == ')') {
                while (operators.peek() != '(') {
                    values.push(applyOperator(operators.pop(), values.pop(), values.pop()))
                }
                operators.pop()
            } else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/') {
                while (!operators.isEmpty() && hasPrecedence(tokens[i], operators.peek())) {
                    values.push(applyOperator(operators.pop(), values.pop(), values.pop()))
                }
                operators.push(tokens[i])
            }
            i++
        }

        while (!operators.isEmpty()) {
            values.push(applyOperator(operators.pop(), values.pop(), values.pop()))
        }

        return values.pop()
    }

    private fun hasPrecedence(op1: Char, op2: Char): Boolean {
        if (op2 == '(' || op2 == ')') return false
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) return false
        return true
    }

    private fun applyOperator(op: Char, b: Double, a: Double): Double {
        return when (op) {
            '+' -> a + b
            '-' -> a - b
            '*' -> a * b
            '/' -> {
                if (b == 0.0) throw UnsupportedOperationException("Cannot divide by zero")
                a / b
            }
            else -> 0.0
        }
    }
}