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


class FamilyInfo {
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
//To account for midnight transition, manual change of time to 1
fun roundTime(timeMinute: Int, timeHour: Int): Int {
    var roundedHour: Int = timeHour
    if (timeMinute >= 30) {
        when (roundedHour) {
            in 1..11 -> roundedHour = timeHour + 1
            12 -> roundedHour = 1
        }
    }
    return roundedHour
}

//Function to validate user input times
@Throws(IllegalArgumentException::class)
fun validateTime(startTime: Time, stopTime: Time): CorrectedTime {


    //Created a linkedMap with "indexing" to be used in the event Kotlin LinkedHashMap does not
    //expose the list interface when attempting to iterate through it
    //The map is used to circumvent the use of an import of java datetime library
    val timeTranslationMap = mapOf(
        "5pm" to 0, "6pm" to 1, "7pm" to 2, "8pm" to 3, "9pm" to 4,
        "10pm" to 5, "11pm" to 6, "12am" to 7, "1am" to 8, "2am" to 9, "3am" to 10, "4am" to 11
    )

    //call function to round time
    startTime.Hours = roundTime(startTime.minutes, startTime.Hours)
    stopTime.Hours = roundTime(stopTime.minutes, stopTime.Hours)
    // println("stop time after round: ${stopTime.Hours}")

    //Validate start following rounding to
    if (startTime.period == "am" || (startTime.Hours < 5 && startTime.period == "pm")) {
        throw IllegalArgumentException("Invalid time entered.")
    }

    //validate time is within resaoable time frame
    if (stopTime.period == "am" && stopTime.Hours > 4) {
        throw IllegalArgumentException("Invalid end time entered.")
    }


    //Get Key value to be searched in translation map
    val startHours = startTime.Hours.toString()
    val stopHours = stopTime.Hours.toString()
    val startTimeStr = startHours + startTime.period
    val stopTimeStr = stopHours + stopTime.period


    //Block to handle times entered are both available
    if (!timeTranslationMap.containsKey(startTimeStr) || !timeTranslationMap.containsKey(stopTimeStr)) {
        throw IllegalArgumentException("Invalid end time entered.")
    }
    val translatedStart = timeTranslationMap.get(startTimeStr)!!
    val translatedStop = timeTranslationMap.get(stopTimeStr)!!
    //Confirm start time occurs before stop time. Prevents babysitter from working less than 30 minutes
    val translationDiff = translatedStop - translatedStart

    //translation map used to confirm that the babysitter works for at least 30 minutes
    //Allows babysitter to be on call (e.g. working from 3am - 4am)
    if (translationDiff <= 0) {
        throw IllegalArgumentException("Invalid end time entered. The start time should be before the end time. The time interval should be greater than 30 minutes")
    }

    //filtered translation map to be used in conjunction with the family pay rates per hour
    val filteredTimeMap = timeTranslationMap.filterValues { it in translatedStart..translatedStop }


    return CorrectedTime(startTimeStr, stopTimeStr, filteredTimeMap)
}

//total pay is calculated from the map values. A list is kep to allow additional changes in the future
//The calculate pay function could be adjusted to pull in the indexes on the time map and iterate or
//sum the the the values between the two indexes
fun calculatePay(
    familyMap: Map<String, Int>,
    validatedMap: Map<String, Int>
): Int {
    val payList = mutableListOf<Int>()

    for ((key, value) in validatedMap) {
        payList.add(familyMap.getValue(key))
    }

    val totalPay = payList.reduce { sum, element -> sum + element }
    println("Your total pay is: $totalPay")

    return totalPay

}

