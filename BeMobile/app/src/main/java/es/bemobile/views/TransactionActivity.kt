package es.bemobile.views

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import es.bemobile.R
import es.bemobile.adapters.RelatedTransactionAdapter
import es.bemobile.config.Constants
import es.bemobile.models.Transaction
import es.bemobile.viewModels.TransactionActivityViewModel

class TransactionActivity : AppCompatActivity() {

    private lateinit var transactionActivityTitle: TextView
    private lateinit var totalTransactions: TextView
    private lateinit var totalAmount: TextView
    private lateinit var recyclerView: RecyclerView
    private var relatedTransactionsList: List<Transaction> = emptyList()
    private lateinit var transactionActivityViewModel: TransactionActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)

        transactionActivityTitle = findViewById<TextView>(R.id.related_transactions_title)
        totalTransactions = findViewById<TextView>(R.id.total_related_transactions)
        totalAmount= findViewById<TextView>(R.id.total_amount_related_transactions)
        recyclerView = findViewById<RecyclerView>(R.id.related_transactions_recylcer_view)

        val sku:String = intent.getStringExtra(Constants.SKU)
        val relatedTransactionsJson = intent.getStringExtra(Constants.RELATED_TRANSACTIONS_JSON)

        val gson = GsonBuilder().create()
        relatedTransactionsList = gson.fromJson(relatedTransactionsJson,Array<Transaction>::class.java).toList()

        transactionActivityTitle.text = "Transaction $sku"
        totalTransactions.text = "${relatedTransactionsList.size.toString()} related transactions"

        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = RelatedTransactionAdapter(this.applicationContext, relatedTransactionsList)
        recyclerView.adapter = adapter

        transactionActivityViewModel = ViewModelProviders.of(this).get(TransactionActivityViewModel::class.java)
        transactionActivityViewModel.initialize(relatedTransactionsList)
        transactionActivityViewModel.getTotal().observe(this, Observer<Float>{ roundedTotal ->
            totalAmount.text = "Total: $roundedTotal "+Constants.EUR
        })
        transactionActivityViewModel.getError().observe(this, Observer<String>{ transactionsList ->
            showError()
        })
    }


    private fun showError(){
        totalAmount.text = "Total: Couldn't be calculated (Please connect to the internet)"
        val builder = AlertDialog.Builder(this)
        builder.setTitle("GNB Alert")
        builder.setMessage("There was an error getting the rates. Please check your connection or try again later.")
        builder.show()
    }
}
