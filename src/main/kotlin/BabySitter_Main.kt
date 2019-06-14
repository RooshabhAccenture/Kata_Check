//Class to hold "type" Time
data class Time(
    var Hours: Int,
    var minutes: Int,
    var period: String
)

//Class to hold validated/corrected Time, along with the filtered timeMap
data class CorrectedTime(
    var validatedStartTime: String,
    var validatedStopTime: String,
    var validatedMap: Map<String, Int>
)


class FamilyInfo constructor(strFamilyValue: String) {
    //Class properties act as a database, in lieu of one for this Kata
    //Dynamic creation of maps could be done by utilizing user input of time ranges, and creating

    val familyATimeMap = mapOf(
        "5pm" to 15, "6pm" to 15, "7pm" to 15, "8pm" to 15, "9pm" to 15,
        "10pm" to 15, "11pm" to 20, "12am" to 20, "1am" to 20, "2am" to 20, "3am" to 20, "4am" to 20
    )

    val familyBTimeMap = mapOf(
        "5pm" to 12, "6pm" to 12, "7pm" to 12, "8pm" to 12, "9pm" to 12,
        "10pm" to 8, "11pm" to 8, "12am" to 16, "1am" to 16, "2am" to 16, "3am" to 16, "4am" to 16
    )

    val familyCTimeMap = mapOf(
        "5pm" to 21, "6pm" to 21, "7pm" to 21, "8pm" to 21, "9pm" to 15,
        "10pm" to 15, "11pm" to 15, "12am" to 15, "1am" to 15, "2am" to 15, "3am" to 20, "4am" to 20
    )

    //Returns family map
    fun validateFamily(strFamilyValue: String) =
        when (strFamilyValue) {
            "A" -> familyATimeMap
            "B" -> familyBTimeMap
            "C" -> familyCTimeMap
            //to fix the "else" statement
            else -> throw IllegalArgumentException("Valid family value required")
        }
}

