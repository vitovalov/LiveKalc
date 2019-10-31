package com.vitovalov.livekalc

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class CalculatorViewModel : ViewModel() {
    private val _output = MutableLiveData("")
    private val _convert = MutableLiveData("DEC")
    private val _outputResult = MutableLiveData("0")

    val output: LiveData<String> = _output
    val convert: LiveData<String> = _convert
    val outputResult: LiveData<String> = _outputResult


    private fun calcResult() {
        val multiply = _output.value?.split("x")?.mapNotNull { it.toIntOrNull() } ?: emptyList()
        val division = _output.value?.split("/")?.mapNotNull { it.toIntOrNull() } ?: emptyList()
        val addition = _output.value?.split("+")?.mapNotNull { it.toIntOrNull() } ?: emptyList()
        val substraction = _output.value?.split("-")?.mapNotNull { it.toIntOrNull() } ?: emptyList()

        val operands: List<Int?>
        var result = 0

        when {
            multiply.size > 1 -> {
                operands = multiply
                result = operands[0] * operands[1]
            }
            division.size > 1 -> {
                operands = division
                result = operands[0] / operands[1]
            }
            addition.size > 1 -> {
                operands = addition
                result = operands[0] + operands[1]
            }
            substraction.size > 1 -> {
                operands = substraction
                result = operands[0] - operands[1]
            }
        }

        _outputResult.value = result.toString()
    }

    private fun operatorPresent(): Boolean {
        val value = _output.value ?: ""
        if (value.contains("x")) return true
        if (value.contains("/")) return true
        if (value.contains("-")) return true
        if (value.contains("+")) return true
        return false
    }

    fun addPressed() {
        if (operatorPresent()) return
        _output.value += "+"
        calcResult()
    }

    fun onePressed() {
        _output.value += "1"
        calcResult()
    }

    fun twoPressed() {
        _output.value += "2"
        calcResult()

    }

    fun threePressed() {
        _output.value += "3"
        calcResult()
    }

    fun acPressed() {
        _outputResult.value = "0"
        _output.value = ""
    }

    fun convertPressed() {
        val value = _convert.value
        when {
            value.equals("DEC") -> {
                _convert.value = "HEX"
                val result = _outputResult.value?.toIntOrNull() ?: 0
                _outputResult.value = Integer.toHexString(result)
            }
            value.equals("HEX") -> {
                _convert.value = "BIN"
                val valString = outputResult.value.toString()
                val i = Integer.parseInt(valString, 16)
                _outputResult.value = Integer.toBinaryString(i)
            }
            value.equals("BIN") -> {
                _convert.value = "DEC"
                val result = toDecimal(_outputResult.value ?: "")
                _outputResult.value = result.toString()
            }
        }
    }

    private fun toDecimal(binaryNumber: String): Int {
        var sum = 0
        binaryNumber.reversed().forEachIndexed { k, v ->
            sum += v.toString().toInt() * pow(2, k)
        }
        return sum
    }

    private fun pow(base: Int, exponent: Int) = Math.pow(base.toDouble(), exponent.toDouble()).toInt()

    fun divisionPressed() {
        if (operatorPresent()) return
        _output.value += "/"
        calcResult()
    }

    fun multiplyPressed() {
        if (operatorPresent()) return
        _output.value += "x"
        calcResult()

    }

    fun substractPressed() {
        if (operatorPresent()) return
        _output.value += "-"
        calcResult()

    }

    fun zeroPressed() {
        _output.value += "0"
        calcResult()

    }

    fun equalPressed() {

    }

    fun commaPressed() {

    }

    fun fourPressed() {
        _output.value += "4"
        calcResult()
    }

    fun fivePressed() {
        _output.value += "5"
        calcResult()
    }

    fun sixPressed() {
        _output.value += "6"
        calcResult()
    }

    fun sevenPressed() {
        _output.value += "7"
        calcResult()
    }

    fun eightPressed() {
        _output.value += "8"
        calcResult()
    }

    fun ninePressed() {
        _output.value += "9"
        calcResult()
    }
}
