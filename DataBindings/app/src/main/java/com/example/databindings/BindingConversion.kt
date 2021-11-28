package com.example.databindings

object BindingConversion {
    @JvmStatic fun centToDollar(number:Int) :String{
        return "${number/100}.${number%100}$"
    }
}