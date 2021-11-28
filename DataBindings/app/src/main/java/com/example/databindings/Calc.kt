package com.example.databindings

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

const val DOLLAR20 = 2000
const val DOLLAR10 = 1000
const val DOLLAR5 = 500
const val DOLLAR = 100
const val QUARTER = 25
const val DIME = 10
const val NICKEL = 5

class Calc : ViewModel(){

    private val _amount =  MutableLiveData(0)
    val amount: LiveData<Int> = _amount

    val dollars20: LiveData<Int> = Transformations.map(_amount) {
        it / DOLLAR20
    }

    val dollars10: LiveData<Int> = Transformations.map(_amount) {
        (it % DOLLAR20) / DOLLAR10
    }

    val dollars5: LiveData<Int> = Transformations.map(_amount) {
        (it % DOLLAR10) / DOLLAR5
    }

    val dollars: LiveData<Int> = Transformations.map(_amount) {
        (it % DOLLAR5) / DOLLAR
    }

    val quarters: LiveData<Int> = Transformations.map(_amount) {
        (it % DOLLAR) / QUARTER
    }

    // dimes is exposed as LiveData using a Transformation instead of a @Bindable property.
    val dimes: LiveData<Int> = Transformations.map(_amount) {
        // whatever cannot be turned into quarters we will turn into dimes.
        (it % QUARTER) / DIME
    }

    val nickels: LiveData<Int> = Transformations.map(_amount){
        (it % DIME) / NICKEL
    }

    val pennies: LiveData<Int> = Transformations.map(_amount){
        (it % NICKEL)
    }

    // add digits 0-9
    fun appendDigit(digit:Int){
        _amount.value = _amount.value?.times(10)?.plus(digit)
    }

    fun clear(){
        _amount.value = 0
    }

//    dollar bills (20,10,5,1)
}