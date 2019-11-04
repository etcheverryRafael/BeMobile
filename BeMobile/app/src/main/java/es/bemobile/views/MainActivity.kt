package es.bemobile.views

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.bemobile.R
import es.bemobile.adapters.TransactionAdapter
import es.bemobile.models.Transaction
import es.bemobile.viewModels.MainActivityViewModel

class MainActivity : AppCompatActivity(){

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var totalTransactions: TextView
    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.findViewsById()

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        mainActivityViewModel.getTransactions().observe(this, Observer<List<Transaction>>{ transactionsList ->
            showTransactions(transactionsList)
        })

        mainActivityViewModel.getError().observe(this, Observer<String>{ transactionsList ->
            showError()
        })
    }

    private fun findViewsById(){
        recyclerView = findViewById<RecyclerView>(R.id.transactions_recycler_view)
        progressBar = findViewById<ProgressBar>(R.id.progressBar)
        totalTransactions = findViewById<TextView>(R.id.total_transactions)
    }

    private fun showTransactions(transactionsList: List<Transaction>){
        // Creates a vertical Layout Manager
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = TransactionAdapter(this, transactionsList)
        recyclerView.adapter = adapter
        progressBar.visibility = View.GONE
        totalTransactions.text = transactionsList.size.toString() + " transactions"
    }

    private fun showError(){
        progressBar.visibility = View.GONE
        val builder = AlertDialog.Builder(this)
        builder.setTitle("GNB Alert")
        builder.setMessage("There was an error getting the transaction. Please check your connection or try again later.")
        builder.show()
    }
}
