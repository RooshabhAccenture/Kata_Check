import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class FamilyInfoTest {

    @Test
    fun `Run multiple tests`() {
        val fam1 = "A"
        val fam2 = "B"
        val fam3 = "C"

        val family1TimeMap = mapOf(
            "5pm" to 15, "6pm" to 15, "7pm" to 15, "8pm" to 15, "9pm" to 15,
            "10pm" to 15, "11pm" to 20, "12am" to 20, "1am" to 20, "2am" to 20, "3am" to 20, "4am" to 20
        )

        val family2TimeMap = mapOf(
            "5pm" to 12, "6pm" to 12, "7pm" to 12, "8pm" to 12, "9pm" to 12,
            "10pm" to 8, "11pm" to 8, "12am" to 16, "1am" to 16, "2am" to 16, "3am" to 16, "4am" to 16
        )

        val family3TimeMap = mapOf(
            "5pm" to 21, "6pm" to 21, "7pm" to 21, "8pm" to 21, "9pm" to 15,
            "10pm" to 15, "11pm" to 15, "12am" to 15, "1am" to 15, "2am" to 15, "3am" to 20, "4am" to 20
        )

        val fam1Test = FamilyInfo()
        val fam2Test = FamilyInfo()
        val fam3Test = FamilyInfo()

        assertEquals(family1TimeMap, fam1Test.validateFamily(fam1))
        assertEquals(family2TimeMap, fam2Test.validateFamily(fam2))
        assertEquals(family3TimeMap, fam3Test.validateFamily(fam3))
        return
    }
//
//    @Test
//    fun `expected exception testing`() {
//        val fam4 = "D"
//        val fam4Test = FamilyInfo(fam4)
//
//        val exception = assertThrows<IllegalArgumentException>("Should throw an exception)") {
//            fam4Test.validateFamily(fam4)
//        }
//        assertEquals("Valid family value required", exception.message)
//    }

}
