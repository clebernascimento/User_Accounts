package com.soft.useraccounts.ui.accountList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.soft.useraccounts.R
import com.soft.useraccounts.data.model.AccountsEntity
import kotlinx.android.synthetic.main.item_accounts.view.*

class AccountAdapter(
    val accounts: MutableList<AccountsEntity> = mutableListOf(),
    val listener: AccountListFragment
) :
    RecyclerView.Adapter<AccountAdapter.AccountViewHolder>() {

    var onItemClick: ((entity: AccountsEntity) -> Unit)? = null

    inner class AccountViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textAccountName: TextView = itemView.text_account_name
        private val textAccountUser: TextView = itemView.text_account_user
        private val textAccountPassword: TextView = itemView.text_account_pass
        private val textAccountDescription: TextView = itemView.text_account_description

        fun bindView(account: AccountsEntity) {
            textAccountName.text = account.name
            textAccountUser.text = account.user
            textAccountPassword.text = account.password
            textAccountDescription.text = account.description

            itemView.setOnClickListener {
                onItemClick?.invoke(account)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_accounts, parent, false)
        return AccountViewHolder(view)
    }

    override fun onBindViewHolder(holder: AccountAdapter.AccountViewHolder, position: Int) {
        holder.bindView(accounts[position])
    }

    override fun getItemCount(): Int = accounts.size

}