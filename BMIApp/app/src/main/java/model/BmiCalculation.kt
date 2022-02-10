package model

import kotlin.math.pow


class BmiCalculation {
    fun calculate(height: Double, weight: Double): BmiInfo {
        var bodyType = ""
        val bmi = weight / (height / 100).toDouble().pow(2.0);

        bodyType = if (bmi < 18.5) {
            "痩せ型"
        } else if (18.5 <= bmi && bmi < 25.0) {
            "標準"
        } else {
            "肥満"
        }

        return BmiInfo(bmi, bodyType)
    }
}