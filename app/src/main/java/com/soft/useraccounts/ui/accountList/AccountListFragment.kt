package com.soft.useraccounts.ui.accountList

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.soft.useraccounts.R
import com.soft.useraccounts.data.AppDataBase
import com.soft.useraccounts.data.model.AccountsEntity
import com.soft.useraccounts.repository.AccountsRepository
import com.soft.useraccounts.repository.DataBaseDataSource
import com.soft.useraccounts.util.Status
import com.soft.useraccounts.util.UtilList
import com.soft.useraccounts.util.navigateWithAnimations
import kotlinx.android.synthetic.main.account_list_fragment.*
import kotlinx.android.synthetic.main.accounts_fragment.*
import kotlinx.android.synthetic.main.accounts_fragment.view.*
import kotlinx.android.synthetic.main.item_accounts.*
import android.text.method.PasswordTransformationMethod




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

    private val listName: MutableList<AccountsEntity> = arrayListOf()
    private lateinit var adapter: AccountAdapter
    private lateinit var utilList: UtilList

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchAccountSetup()
        observeViewModelEvents()
        configureViewListeners()
        setupUI()
    }

    override fun onResume() {
        super.onResume()
        activity?.let {
            val list = it
            utilList = UtilList(list)
        }
        viewModel.getAccounts()
    }

    private fun setupUI() {
        adapter = AccountAdapter(listener = this)
        recycler_accounts.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(
                DividerItemDecoration(
                    this.context,
                    (this.layoutManager as LinearLayoutManager).orientation
                )
            )
            adapter = this@AccountListFragment.adapter
        }
    }

    private fun observeViewModelEvents() {
        viewModel.getAccounts().observe(viewLifecycleOwner, { allAccounts ->
            allAccounts.let { resources ->
                when (resources.status) {
                    Status.SUCCESS -> {
                        utilList.setSuccess()
                        resources.data?.let(::retrieveList)
                    }
                    Status.ERROR -> {
                        utilList.setError()
                    }
                    Status.LOADING -> {
                        utilList.setLoading()
                    }
                }
            }
        })
    }

    private fun retrieveList(accountsEntity: List<AccountsEntity>) {
        adapter.addAccounts(accountsEntity)
    }

    fun onItemClick(waterfall: AccountsEntity?, position: Int) {
        if (waterfall != null) {
            val directions = AccountListFragmentDirections
              .actionAccountListFragmentToAccountsFragment(waterfall)
            findNavController().navigateWithAnimations(directions)
            adapter.notifyItemChanged(position)
        }
    }

    private fun searchAccountSetup() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                search(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                search(newText)
                return false
            }
        })
    }

    private fun search(text: String?) {
        adapter.apply {
            listName.clear()
            listName.addAll(accounts)
            accounts.clear()
            text?.let {
                if (it.isEmpty()) {
                    observeViewModelEvents()
                } else {
                    listName.forEach { account ->
                        if (account.name.contains(text, true)) {
                            adapter.accounts.add(account)
                        }
                    }
                    if (listName.isNullOrEmpty()) {
                       utilList.setError()
                    }
                }
                updateRecyclerView()
            }
        }
    }

    private fun updateRecyclerView() {
        recycler_accounts.apply {
            adapter!!.notifyDataSetChanged()
        }
    }

    private fun configureViewListeners() {
        fabAddAccounts.setOnClickListener {
            findNavController().navigateWithAnimations(
                R.id.action_accountListFragment_to_accountsFragment
            )
        }
    }
}