package es.bemobile

import es.bemobile.helpers.HelperFunctions
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class RoundToEvenUnitTest {
    @Test
    fun roundEvenValueDown_isCorrect() {
        val value = 1.12411
        val roundedValue = HelperFunctions.round(value)
        assertEquals(1.12f, roundedValue, 0.0f)
    }

    @Test
    fun roundEvenValueUp_isCorrect() {
        val value = 2.12922
        val roundedValue = HelperFunctions.round(value)
        assertEquals(2.13f, roundedValue, 0.0f)
    }

    @Test
    fun roundOddValueDown_isCorrect() {
        val value = 3.13433
        val roundedValue = HelperFunctions.round(value)
        assertEquals(3.13f, roundedValue, 0.0f)
    }

    @Test
    fun roundOddValueUp_isCorrect() {
        val value = 4.12944
        val roundedValue = HelperFunctions.round(value)
        assertEquals(4.13f, roundedValue, 0.0f)
    }

    @Test
    fun roundEvenValueDownInLimit_isCorrect() {
        val value = 5.12555
        val roundedValue = HelperFunctions.round(value)
        assertEquals(5.12f, roundedValue, 0.0f)
    }

    @Test
    fun roundOddValueUpInLimit_isCorrect() {
        val value = 6.13566
        val roundedValue = HelperFunctions.round(value)
        assertEquals(6.14f, roundedValue, 0.0f)
    }

}
