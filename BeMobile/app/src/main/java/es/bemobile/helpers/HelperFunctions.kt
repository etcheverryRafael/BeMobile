package es.bemobile.helpers

class HelperFunctions {
    companion object {
        fun round(total:Double):Float{
            var aux = total*100
            val aux2 = total*1000
            if((aux.toInt()%2==1 && aux2.toInt()%10>=5) || (aux.toInt()%2==0 && aux2.toInt()%10>5))
                aux += 1
            return aux.toInt()/100.0f
        }
    }
}