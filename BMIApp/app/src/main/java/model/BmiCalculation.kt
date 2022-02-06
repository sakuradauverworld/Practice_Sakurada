package model

class BmiCalculation {
    fun caluculate(height: Int,weight: Int): BmiInfo{
        val height = height / 100
        var bodyType = ""
        val bmi = weight / (height * 2)

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