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

    inner class AccountViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(account: AccountsEntity) {
            itemView.apply {
                text_account_name.text = account.name
                text_account_user.text = account.user
                text_account_pass.text = account.password
                text_account_description.text = account.description
            }
        }
        fun bindClick(waterfallClick: AccountsEntity?, position: Int, listener: AccountListFragment) {
            itemView.setOnClickListener { view: View? ->
                if (waterfallClick != null) {
                    listener.onItemClick(waterfallClick, position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_accounts, parent, false)
        return AccountViewHolder(view)
    }

    override fun onBindViewHolder(holder: AccountAdapter.AccountViewHolder, position: Int) {
        val water = accounts[position]
        holder.bindView(accounts[position])
        holder.bindClick(water, position, listener)
    }

    override fun getItemCount(): Int = accounts.size

    fun addAccounts(account: List<AccountsEntity>){
        this.accounts.apply {
            clear()
            addAll(account)
            notifyDataSetChanged()
        }
    }
}