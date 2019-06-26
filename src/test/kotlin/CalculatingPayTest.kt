import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class CalculatingPayTest {

    @Test
    fun `calculatingPay`() {
        //Family B's time map.
        val familyBTimeMap = mapOf(
            "5pm" to 12, "6pm" to 12, "7pm" to 12, "8pm" to 12, "9pm" to 12,
            "10pm" to 8, "11pm" to 8, "12am" to 16, "1am" to 16, "2am" to 16, "3am" to 16, "4am" to 16
        )

        //A filtered time map that would be passed
        val filteredTranslationMap = mapOf(
            "6pm" to 1, "7pm" to 2, "8pm" to 3, "9pm" to 4,
            "10pm" to 5, "11pm" to 6, "12am" to 7, "1am" to 8, "2am" to 9, "3am" to 10
        )


        val expectedPay = 128

        val actualPay = calculatePay(familyBTimeMap, filteredTranslationMap)
        assertEquals(expectedPay, actualPay)


    }
}