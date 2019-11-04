package es.bemobile

import es.bemobile.models.Rate
import es.bemobile.models.Transaction
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class TransactionUnitTest {
    @Test
    fun totalCalculation_isCorrect() {
        val rate1 = Rate("EUR","USD",1.359f)
        val rate2 = Rate("CAD","EUR",0.732f)
        val rate3 = Rate("USD","EUR", 0.736f)
        val rate4 = Rate("EUR", "CAD", 1.366f)
        val ratesList:List<Rate> = listOf(rate1,rate2,rate3,rate4)

        val t1 = Transaction("T2006", 10.00f, "USD")
        val t2 = Transaction("M2007", 34.57f, "CAD")
        val t3 = Transaction("R2008", 17.95f, "USD")
        val t4 = Transaction("T2006", 7.63f, "EUR")
        val t5 = Transaction("B2009", 21.23f, "USD")

        val transactionsList:List<Transaction> = listOf(t1,t2,t3,t4,t5)
        val relatedTransactionsList = transactionsList.filter{tr: Transaction -> tr.sku == "T2006"}

        val roundedValue = Transaction.calculateTotal(relatedTransactionsList, ratesList)
        assertEquals(14.99f , roundedValue, 0.0f)
    }

}
