package model

import java.io.Serializable

data class BmiInfo (var bmi: Double, var bodyType: BodyType) : Serializable{
    enum class BodyType : Serializable{
        LEPTOSOMACTIC, STANDARD, OBESITY
    }
}