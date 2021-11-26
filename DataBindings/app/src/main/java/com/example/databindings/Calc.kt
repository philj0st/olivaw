package com.example.databindings

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

const val QUARTER = 25
const val DIME = 10
val NICKEL = 5
val PENNY = 1

class Calc : ViewModel(){

    private val _amount =  MutableLiveData(0)
    val amount: LiveData<Int> = _amount

    // dimes is exposed as LiveData using a Transformation instead of a @Bindable property.
    val dimes: LiveData<Int> = Transformations.map(_amount) {
        // whatever cannot be turned into quarters we will turn into dimes.
        (it % QUARTER) % DIME
    }

    // add digits 0-9
    fun appendDigit(digit:Int){
        _amount.value = _amount.value?.times(10)?.plus(digit)
    }

//    dollar bills (20,10,5,1)
}