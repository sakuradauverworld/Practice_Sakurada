package model

data class BmiInfo (var bmi: Double, var bodyType: BodyType){
    enum class BodyType {
        LEPTOSOMACTIC, STANDARD, OBESITY
    }
}