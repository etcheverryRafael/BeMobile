package es.bemobile.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import es.bemobile.models.Transaction
import es.bemobile.repositories.RatesRepository
import kotlinx.coroutines.Dispatchers

class TransactionActivityViewModel:ViewModel(){

    private var total: LiveData<Float> = MutableLiveData()
    private val repository: RatesRepository = RatesRepository()
    private var mError: MutableLiveData<String> = MutableLiveData()

    fun initialize(relatedTransactionsList:List<Transaction>){
        total = liveData(Dispatchers.IO) {
            try
            {
                val retrievedRates = repository.getRates()
                val roundedTotal = Transaction.calculateTotal(relatedTransactionsList,retrievedRates)
                emit(roundedTotal)
            }
            catch(ex:Exception){
                mError.postValue(ex.toString())
            }
        }
    }

    fun getTotal():LiveData<Float>{
        return total
    }

    fun getError():LiveData<String>{
        return mError
    }

}