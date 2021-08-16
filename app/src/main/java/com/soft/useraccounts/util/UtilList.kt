package com.soft.useraccounts.util

import android.view.View
import androidx.fragment.app.FragmentActivity
import kotlinx.android.synthetic.main.account_list_fragment.*

class UtilList(val accountListFragment: FragmentActivity) {

    fun setLoading() {
        accountListFragment.progressBar.visibility = View.VISIBLE
        accountListFragment.recycler_accounts.visibility = View.GONE
        accountListFragment.layout_error.visibility = View.GONE
    }

    fun setSuccess() {
        accountListFragment.recycler_accounts.visibility = View.VISIBLE
        accountListFragment.progressBar.visibility = View.GONE
        accountListFragment.layout_error.visibility = View.GONE
    }

    fun setError() {
        accountListFragment.layout_error.visibility = View.VISIBLE
        accountListFragment.recycler_accounts.visibility = View.GONE
        accountListFragment.progressBar.visibility = View.GONE
    }
}