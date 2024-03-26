package com.example.calcsci

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.graphics.toColorInt
import com.example.calcsci.databinding.ActivityMainBinding
import java.lang.RuntimeException
import kotlin.math.pow
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btn0.setOnClickListener {
            binding.TVPrimary.text = binding.TVPrimary.text.toString() + "0"
        }

        binding.btn1.setOnClickListener {
            binding.TVPrimary.text = binding.TVPrimary.text.toString() + "1"
        }

        binding.btn2.setOnClickListener {
            binding.TVPrimary.text = binding.TVPrimary.text.toString() + "2"
        }

        binding.btn3.setOnClickListener {
            binding.TVPrimary.text = binding.TVPrimary.text.toString() + "3"
        }

        binding.btn4.setOnClickListener {
            binding.TVPrimary.text = binding.TVPrimary.text.toString() + "4"
        }

        binding.btn5.setOnClickListener {
            binding.TVPrimary.text = binding.TVPrimary.text.toString() + "5"
        }

        binding.btn6.setOnClickListener {
            binding.TVPrimary.text = binding.TVPrimary.text.toString() + "6"
        }

        binding.btn7.setOnClickListener {
            binding.TVPrimary.text = binding.TVPrimary.text.toString() + "7"
        }

        binding.btn8.setOnClickListener {
            binding.TVPrimary.text = binding.TVPrimary.text.toString() + "8"
        }

        binding.btn9.setOnClickListener {
            binding.TVPrimary.text = binding.TVPrimary.text.toString() + "9"
        }

        binding.btnDeci.setOnClickListener {
            binding.TVPrimary.text = binding.TVPrimary.text.toString() + "."
        }

        binding.btnOpenBrac.setOnClickListener {
            binding.TVPrimary.text = binding.TVPrimary.text.toString() + "("
        }

        binding.btnCloseBrac.setOnClickListener {
            binding.TVPrimary.text = binding.TVPrimary.text.toString() + ")"
        }

        binding.btnPie.setOnClickListener {
            binding.TVPrimary.text = binding.TVPrimary.text.toString() + "3.142"
        }

        binding.btnCloseBrac.setOnClickListener {
            binding.TVPrimary.text = binding.TVPrimary.text.toString() + ")"
        }

        binding.btnRecip.setOnClickListener {
            binding.TVPrimary.text = binding.TVPrimary.text.toString() + "^" + "-1"
        }

        binding.btnDivide.setOnClickListener {
            val str: String = binding.TVPrimary.text.toString()
            if (!(str.get(index = str.length - 1).equals("÷"))) {
                binding.TVPrimary.text = binding.TVPrimary.text.toString() + "÷"
            }
        }

        binding.btnMinus.setOnClickListener {
            val str: String = binding.TVPrimary.text.toString()
            if (!str.get(index = str.length - 1).equals("-")) {
                binding.TVPrimary.text = binding.TVPrimary.text.toString() + "-"
            }
        }

        binding.btnMulti.setOnClickListener {
            val str: String = binding.TVPrimary.text.toString()
            if (!str.get(index = str.length - 1).equals("×")) {
                binding.TVPrimary.text = binding.TVPrimary.text.toString() + "×"
            }
        }

        binding.btnPlus.setOnClickListener {
            val str: String = binding.TVPrimary.text.toString()
            if (!str.get(index = str.length - 1).equals("+")) {
                binding.TVPrimary.text = binding.TVPrimary.text.toString() + "+"
            }
        }

        binding.btnAC.setOnClickListener {
            binding.TVPrimary.text = ""
            binding.TVSecondary.text = ""
        }

        binding.btnC.setOnClickListener {
            var str: String = binding.TVPrimary.text.toString()
            if (!str.equals("")) {
                str = str.substring(0, str.length - 1)
                binding.TVPrimary.text = str
            }
        }

        binding.btnSqR.setOnClickListener {
            if (binding.TVPrimary.text.toString().isNotEmpty()) {
                val str: String = binding.TVPrimary.text.toString()
                val sqr = sqrt(str.toDouble())
                binding.TVPrimary.text = sqr.toString()
            } else {
                Toast.makeText(this, "Enter a valid number", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnSq.setOnClickListener {
            if (binding.TVPrimary.text.equals("")) {
                Toast.makeText(this, "Enter a valid number", Toast.LENGTH_SHORT).show()
            } else {
                val str: Double = binding.TVPrimary.text.toString().toDouble()
                val sq = str * str
                binding.TVPrimary.text = sq.toString()
            }
        }

        binding.btnFact.setOnClickListener {
            if (binding.TVPrimary.text.equals("")) {
                Toast.makeText(this, "Enter a valid number", Toast.LENGTH_SHORT).show()
            } else {
                val str: String = binding.TVPrimary.text.toString()
                val factorial: Int = factorial(str.toInt())
                binding.TVPrimary.text = factorial.toString()
                binding.TVSecondary.text = factorial.toString()
            }
        }

        binding.btnEqual.setOnClickListener {
            if (binding.TVPrimary.text.equals("")) {
                Toast.makeText(this, "Enter a valid number", Toast.LENGTH_SHORT).show()
            } else {
                val str: String = binding.TVPrimary.text.toString()
                val answer: Double = evaluate(str)
                binding.TVPrimary.text = answer.toString()
                binding.TVSecondary.text = str
            }
        }
    }

    private fun factorial(n: Int): Int {
        return if (n == 0 || n == 1) {
            1
        } else n * factorial(n - 1)
    }

    private fun evaluate(str: String): Double {
        if (str.isEmpty()) {
            // Handle the case where the input is empty
            return 0.0  // or any other appropriate value
        }

        return object : Any() {
            var pos = -1
            var ch = 0
            fun nextChar() {
                ch = if (++pos < str.length) str[pos].toInt() else -1
            }

            fun eat(charToEat: Int): Boolean {
                while (ch == ' '.toInt()) nextChar()
                if (ch == charToEat) {
                    nextChar()
                    return true
                }
                return false
            }

            fun parse(): Double {
                nextChar()
                val x = parseExpression()
                if (pos < str.length) {
                    throw RuntimeException("Unexpected character: " + ch.toChar())
                }
                return x
            }

            private fun parseExpression(): Double {
                var x = parseTerm()
                while (true) {
                    if (eat('+'.toInt())) x += parseTerm()
                    else

                        if (eat('-'.toInt())) x -= parseTerm()
                        else

                            return x
                }
            }

            fun parseTerm(): Double {
                var x = parseFactor()
                while (true) {
                    if (eat('*'.toInt())) {
                        x *= parseFactor()
                    } else if (eat('/'.toInt())) {
                        val divisor = parseFactor()
                        if (divisor == 0.0) {
                            throw ArithmeticException("Division by zero")
                        }
                        x /= divisor
                    } else {
                        return x
                    }
                }
            }

            private fun parseFactor(): Double {
                if (eat('+'.toInt())) return parseFactor()
                if (eat('-'.toInt())) return -parseFactor()

                var x: Double


                val startPos = pos

                if (eat('('.toInt())) {
                    if (ch == ')'.toInt()) {
                        throw RuntimeException("Empty parentheses are not allowed.")
                    }
                    x = parseExpression()
                    eat(')'.toInt())
                } else

                    if (ch >= '0'.toInt() && ch <= '9'.toInt() || ch == '.'.toInt()) {
                        while (ch >= '0'.toInt() && ch <= '9'.toInt() || ch == '.'.toInt()) nextChar()
                        x = str.substring(startPos, pos).toDouble()
                    } else

                        if (ch >= 'a'.toInt() && ch <= 'z'.toInt()) {
                            while (ch >= 'a'.toInt() && ch <= 'z'.toInt()) nextChar()
                            val func = str.substring(startPos, pos)
                            x = parseFactor()
                            if (func == "sqrt") {
                                x = sqrt(x)
                            }
                        } else {
                            throw RuntimeException("Unexpected character: " + ch.toChar())
                        }
                if (eat('^'.toInt())) x = x.pow(parseFactor())
                return x
            }
        }.parse()
    }
}
