package com.example.databindings

import androidx.databinding.ObservableInt

class Calc {

    val amount = ObservableInt()

    // add digits 0-9
    fun appendDigit(digit:Int){
        amount.set((amount.get() * 10) + digit)
    }
}
