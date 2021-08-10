package com.soft.useraccounts.ui.accountList

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.soft.useraccounts.MainActivity
import com.soft.useraccounts.R
import com.soft.useraccounts.data.AppDataBase
import com.soft.useraccounts.repository.AccountsRepository
import com.soft.useraccounts.repository.DataBaseDataSource
import com.soft.useraccounts.util.navigateWithAnimations
import kotlinx.android.synthetic.main.account_list_fragment.*

class AccountListFragment : Fragment(R.layout.account_list_fragment) {

    private val viewModel: AccountListViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val apiDao = AppDataBase.getInstance(requireContext()).apiDao
                val repository: AccountsRepository = DataBaseDataSource(apiDao)
                return AccountListViewModel(repository) as T
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModelEvents()
        configureViewListeners()

    }

    private fun observeViewModelEvents() {
        viewModel.allAccountsEvent.observe(viewLifecycleOwner) { allAccounts ->
            val accountListAdapter = AccountAdapter(allAccounts).apply {
                if (allAccounts.isNotEmpty()) {
                    tv_NoList.visibility = View.GONE
                    onItemClick = { accounts ->
                        val directions = AccountListFragmentDirections
                            .actionAccountListFragmentToAccountsFragment(accounts)

                        findNavController().navigateWithAnimations(directions)
                    }
                } else {
                    tv_NoList.visibility = View.VISIBLE
                }
            }
            with(recycler_accounts) {
                setHasFixedSize(true)
                adapter = accountListAdapter
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getSubscribers()
    }

    private fun configureViewListeners() {
        fabAddAccounts.setOnClickListener {
            findNavController().navigateWithAnimations(
                R.id.action_accountListFragment_to_accountsFragment
            )
        }
    }
}