package es.bemobile

import es.bemobile.models.Rate
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class RateUnitTest {
    @Test
    fun totalOneStepCalculation_isCorrect() {
        val r1 = Rate("EUR","USD",1.359f)
        val r2 = Rate("AUD","USD",0.732f)
        val r3 = Rate("CAD","EUR",0.732f)
        val r4 = Rate("USD","EUR", 0.836f)
        val r5= Rate("EUR", "CAD", 1.366f)
        val ratesList:List<Rate> = listOf(r1,r2,r3,r4,r5)

        val rateValue = Rate.getRateValue(ratesList, "USD", 1.0)

        assertEquals(0.836, rateValue, 0.0)
    }

    @Test
    fun totalTwoStepCalculation_isCorrect() {
        val r1 = Rate("EUR","USD",1.359f)
        val r2 = Rate("AUD","USD",0.732f)
        val r3 = Rate("CAD","EUR",0.732f)
        val r4 = Rate("USD","EUR", 0.836f)
        val r5= Rate("EUR", "CAD", 1.366f)
        val ratesList:List<Rate> = listOf(r1,r2,r3,r4,r5)

        val rateValue = Rate.getRateValue(ratesList, "AUD", 1.0)

        assertEquals(0.611952 , rateValue, 0.0)
    }

}
