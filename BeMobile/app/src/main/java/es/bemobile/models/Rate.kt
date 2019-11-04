package es.bemobile.models

import es.bemobile.config.Constants

class Rate {
    var from: String? = null
    var to: String? = null
    var rate: Float? = null

    constructor(from:String, to:String, rate:Float){
        this.from = from
        this.to = to
        this.rate = rate
    }

    override fun toString(): String = "From: ${this.from}, To: ${this.to}, Rate:${this.rate.toString()}"

    companion object{
        fun getRateValue(ratesArray:List<Rate>, currency:String, accum:Double):Double {
            var rate = ratesArray.find { rate:Rate -> rate.from == currency && rate.to == Constants.EUR}
            if (rate == null) {
                rate = ratesArray.find { rate:Rate -> rate.from == currency }
                if(rate==null){
                    return accum
                }
                else {
                    val newAccum = accum * rate?.rate!!
                    return getRateValue(ratesArray, rate?.to!!, newAccum)
                }
            }
            else {
                return accum * rate.rate!!
            }
        }
    }
}