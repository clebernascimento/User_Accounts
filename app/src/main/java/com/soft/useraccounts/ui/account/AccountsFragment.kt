package com.soft.useraccounts.ui.account

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.soft.useraccounts.R
import com.soft.useraccounts.data.AppDataBase
import com.soft.useraccounts.data.dao.ApiDao
import com.soft.useraccounts.repository.AccountsRepository
import com.soft.useraccounts.repository.DataBaseDataSource
import com.soft.useraccounts.util.hideKeyboard
import kotlinx.android.synthetic.main.accounts_fragment.*

class AccountsFragment : Fragment(R.layout.accounts_fragment) {

    private val viewModel: AccountsViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val apiDAO: ApiDao =
                    AppDataBase.getInstance(requireContext()).apiDao

                val repository: AccountsRepository = DataBaseDataSource(apiDAO)
                return AccountsViewModel(repository) as T
            }
        }
    }

    private val args:AccountsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.accounts?.let { accounts ->
            bt_account.text = getString(R.string.account_button_update)
            input_name.setText(accounts.name)
            input_user.setText(accounts.user)
            input_pass.setText(accounts.password)
            input_description.setText(accounts.description)

            bt_delete.visibility = View.VISIBLE
        }

        observeEvents()
        setListeners()
    }

    private fun observeEvents() {
        viewModel.accountsStateEventData.observe(viewLifecycleOwner) { accountState ->
            when (accountState) {
                is AccountsViewModel.AccountState.Inserted,
                is AccountsViewModel.AccountState.Update,
                is AccountsViewModel.AccountState.Deleted -> {
                    clearFields()
                    hideKeyboard()
                    requireView().requestFocus()

                    findNavController().popBackStack()
                }
            }
        }
        viewModel.messageEventData.observe(viewLifecycleOwner) { stringResId ->
            Snackbar.make(requireView(), stringResId, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun clearFields() {
        input_name.text?.clear()
        input_user.text?.clear()
        input_pass.text?.clear()
        input_description.text?.clear()
    }

    private fun hideKeyboard() {
        val parentActivity = requireActivity()
        if (parentActivity is AppCompatActivity) {
            parentActivity.hideKeyboard()
        }
    }

    private fun setListeners() {
        bt_account.setOnClickListener {
            val name = input_name.text.toString()
            val user = "Usuario: " + input_user.text.toString()
            val pass = "Senha: " + input_pass.text.toString()
            val desc = "Descrição: " + input_description.text.toString()

            viewModel.addUpdateAccounts(name, user, pass, desc, args.accounts?.id ?: 0)
        }

        bt_delete.setOnClickListener {
            viewModel.removeAccounts(args.accounts?.id ?: 0)
        }
    }
}