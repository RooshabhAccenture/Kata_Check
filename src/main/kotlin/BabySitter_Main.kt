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

//Babysitter does not work in fractional hours;
// Time is rounded up/down based on the 30 minute mark.
fun roundTime(timeMinute: Int, timeHour: Int): Int {
    var roundedHour: Int = timeHour
    if (timeMinute >= 30) {
        roundedHour = timeHour + 1
    }
    return roundedHour
}

//Function to validate user input times
fun validateTime(): CorrectedTime {

    //**********REMOVE & move to input for function
    val startTime = Time(8, 10, "pm")
    val stopTime = Time(2, 33, "am")
    //***********REMOVE % move to input for function


    //Created a linkedMap with "indexing" to be used in the event Kotlin LinkedHashMap does not
    //expose the list interface when attempting to iterate through it
    val timeTranslationMap = mapOf(
        "5pm" to 0, "6pm" to 1, "7pm" to 2, "8pm" to 3, "9pm" to 4,
        "10pm" to 5, "11pm" to 6, "12am" to 7, "1am" to 8, "2am" to 9, "3am" to 10, "4am" to 11
    )


    //call function to round time
    startTime.Hours = roundTime(startTime.minutes, startTime.Hours)
    stopTime.Hours = roundTime(stopTime.minutes, stopTime.Hours)

    //Validate start following rounding to
    if (startTime.period == "am" || (startTime.Hours < 5 && startTime.period == "pm")) {
        println("no bueno") //update to return valid message
    }

    //validate time is within resaoable time frame
    if (stopTime.period == "am" && stopTime.Hours > 4) {
        println("no bueno")
    }


    //return value blocks to be fixed
    val startTimeStr = "the rounded start time string"
    val stopTimeStr = "the rounded stop time string"
    val filteredTimeMap = mapOf("?am/pm" to 0)

    return CorrectedTime(startTimeStr, stopTimeStr, filteredTimeMap)
}