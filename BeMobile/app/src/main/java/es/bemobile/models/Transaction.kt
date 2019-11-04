package es.bemobile.models

import es.bemobile.config.Constants
import es.bemobile.helpers.HelperFunctions

class Transaction {
    var sku: String? = null
    var amount: Float ? = null
    var currency: String ? = null

    constructor(sku:String, amount:Float, currency:String){
        this.sku = sku
        this.amount = amount
        this.currency = currency
    }

    override fun toString(): String = "SKU: ${this.sku}, Amount: ${this.amount.toString()}, Currency: ${this.currency}"

    companion object {
        fun calculateTotal(relatedTransactionsArray:List<Transaction>, ratesArray:List<Rate>?):Float{
            var total = 0.0
            for(tr in relatedTransactionsArray) {
                if (tr.currency != Constants.EUR) {
                    val rateValue = Rate.getRateValue(ratesArray!!, tr.currency!!, 1.0)
                    total += tr?.amount!! * rateValue
                } else {
                    total += tr?.amount!!
                }
            }
            return HelperFunctions.round(total)
        }
    }
}