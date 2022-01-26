package com.example.bingoapp

class BingoManager {
    var isNextFlag = true
    var drawingBox = (1..75).toMutableList()
    var numericHistory: MutableList<Int> = mutableListOf()

    data class LotteryResult (var isNextFlag: Boolean, var number: Int)

    fun callNumber(): LotteryResult {
        val number = drawingBox.random()

        numericHistory.add(number)
        drawingBox.remove(number)
        isNextFlag = !drawingBox.isEmpty()

        return LotteryResult(isNextFlag, number)
    }

    fun bingoHistory(): MutableList<Int> {
        return numericHistory
    }
}
