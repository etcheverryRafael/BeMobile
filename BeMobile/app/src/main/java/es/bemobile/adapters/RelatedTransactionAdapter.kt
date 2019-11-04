package es.bemobile.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.bemobile.R
import es.bemobile.models.Transaction
import kotlinx.android.synthetic.main.list_item_related_transaction.view.*

class RelatedTransactionAdapter(val context: Context, val items : List<Transaction>) : RecyclerView.Adapter<RelatedTransactionViewHolder>() {

    override fun onBindViewHolder(holder: RelatedTransactionViewHolder, position: Int) {
        val transaction = items.get(position)
        holder.index.text = (position+1).toString()+") "
        holder.amountTextView.text = transaction.amount.toString()
        holder.currencyTextView.text = transaction.currency
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RelatedTransactionViewHolder {
        return RelatedTransactionViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_related_transaction, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class RelatedTransactionViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val index = view.related_index
    val amountTextView = view.related_transaction_amount
    val currencyTextView = view.related_transaction_currency
}
