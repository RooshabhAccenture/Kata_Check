import org.junit.jupiter.api.Assertions.assertEquals

import java.lang.IllegalArgumentException
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.Test


class ValTimeTest {

    @Test
    fun `Simple round check`() {
        val startTime1 = Time(8, 10, "pm")
        val stopTime1 = Time(3, 33, "am")

        val expectedMap1 = mapOf(
            "8pm" to 3, "9pm" to 4,
            "10pm" to 5, "11pm" to 6, "12am" to 7, "1am" to 8, "2am" to 9, "3am" to 10, "4am" to 11
        )

        val startTimeStr1 = "8pm"
        val stopTimeStr1 = "4am"

        val expectedResult1 = CorrectedTime(
            startTimeStr1,
            stopTimeStr1,
            expectedMap1
        )

        val actualResult1 = validateTime(startTime1, stopTime1)
        assertEquals(expectedResult1, actualResult1)

        return
    }

    @Test
    fun `midnight round check`() {
        val startTime2 = Time(8, 45, "pm")
        val stopTime2 = Time(12, 33, "am")

        val expectedMap2 = mapOf(
            "9pm" to 4,
            "10pm" to 5, "11pm" to 6, "12am" to 7, "1am" to 8
        )

        val startTimeStr2 = "9pm"
        val stopTimeStr2 = "1am"

        val expectedResult2 = CorrectedTime(
            startTimeStr2,
            stopTimeStr2,
            expectedMap2
        )

        val actualResult2 = validateTime(startTime2, stopTime2)
        assertEquals(expectedResult2, actualResult2)


        return
    }
}