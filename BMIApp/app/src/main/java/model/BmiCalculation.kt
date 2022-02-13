package model

import kotlin.math.pow

class BmiCalculation {
    fun calculate(height: Double, weight: Double): BmiInfo {
        val bmi = weight / (height / 100).toDouble().pow(2.0);

        val bodyType = if (bmi < 18.5) {
            BmiInfo.BodyType.LEPTOSOMACTIC
        } else if (18.5 <= bmi && bmi < 25.0) {
            BmiInfo.BodyType.STANDARD
        } else {
            BmiInfo.BodyType.OBESITY
        }

        return BmiInfo(bmi, bodyType)
    }
}