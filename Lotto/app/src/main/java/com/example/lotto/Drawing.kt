package com.example.lotto

import kotlin.random.Random

data class Drawing(val range:Triple<Int,Int,Int>, val luckyRange:Triple<Int,Int,Int>){
    init {
        val numbers = mutableListOf<Int>()
        val (from, to, amount) = range
        var nextNum:Int

        // fill the list with unique numbers in the given range
        while (numbers.size<amount){
            do {
                nextNum = Random.nextInt(from,to)
            } while (numbers.contains(nextNum))
            numbers.add(nextNum)
        }
        val (fromLucky, toLucky, amountLucky) = luckyRange
        while (numbers.size<amount+amountLucky){
            do {
                nextNum = Random.nextInt(fromLucky,toLucky)
            } while (numbers.contains(nextNum))
            numbers.add(nextNum)
        }
    }
}
