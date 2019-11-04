package es.bemobile.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import es.bemobile.models.Transaction
import es.bemobile.repositories.TransactionsRepository
import kotlinx.coroutines.Dispatchers

class MainActivityViewModel: ViewModel() {

    private val repository: TransactionsRepository = TransactionsRepository()
    private var transactions:LiveData<List<Transaction>> = MutableLiveData()

    private var mError: MutableLiveData<String> = MutableLiveData()

    init {
        transactions = liveData(Dispatchers.IO) {
            try
            {
                val retrievedTransactions = repository.getTransactions()
                emit(retrievedTransactions)
            }
            catch(ex:Exception){
                mError.postValue(ex.toString())
            }
        }
    }

    fun getTransactions():LiveData<List<Transaction>>{
        return transactions
    }

    fun getError():LiveData<String>{
        return mError
    }

}
