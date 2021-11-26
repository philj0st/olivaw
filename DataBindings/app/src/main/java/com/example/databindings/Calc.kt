package com.example.databindings

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Calc : ViewModel(){

    private val _amount =  MutableLiveData(0)
    val amount: LiveData<Int> = _amount


    // add digits 0-9
    fun appendDigit(digit:Int){
        _amount.value = _amount.value?.times(10)?.plus(digit)
    }

//    dollar bills (20,10,5,1)
}
