package es.bemobile.adapters

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import es.bemobile.R
import es.bemobile.views.TransactionActivity
import es.bemobile.config.Constants
import es.bemobile.models.Transaction
import kotlinx.android.synthetic.main.list_item_transaction.view.*

class TransactionAdapter(val activity: Activity, val items : List<Transaction>) : RecyclerView.Adapter<TransactionViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val holder = TransactionViewHolder(LayoutInflater.from(activity).inflate(R.layout.list_item_transaction, parent, false))
        return holder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val transaction = items.get(position)
        holder.index.text = (position+1).toString()+") "
        holder.skuTextView.text = transaction.sku
        holder.amountTextView.text = transaction.amount.toString()
        holder.currencyTextView.text = transaction.currency

        holder.itemView.setOnClickListener{
            val selectedTransaction = items[position]
            val relatedTransactionsList = items.filter{ tr: Transaction -> tr.sku == selectedTransaction.sku}

            val intent = Intent(activity, TransactionActivity::class.java)
            intent.putExtra(Constants.SKU, selectedTransaction.sku)
            val gson = GsonBuilder().create()
            intent.putExtra(Constants.RELATED_TRANSACTIONS_JSON, gson.toJson(relatedTransactionsList))
            activity.startActivity(intent)
        }
    }
}

class TransactionViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val index = view.index
    val skuTextView = view.transaction_sku
    val amountTextView = view.transaction_amount
    val currencyTextView = view.transaction_currency
}
