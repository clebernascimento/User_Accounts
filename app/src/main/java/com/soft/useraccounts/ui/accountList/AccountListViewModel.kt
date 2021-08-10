package com.soft.useraccounts.ui.accountList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soft.useraccounts.data.model.AccountsEntity
import com.soft.useraccounts.repository.AccountsRepository
import kotlinx.coroutines.launch

class AccountListViewModel (
    private val repository: AccountsRepository
) : ViewModel() {

    private val _allAccountsEvent = MutableLiveData<List<AccountsEntity>>()
    val allAccountsEvent: LiveData<List<AccountsEntity>>
        get() = _allAccountsEvent

    fun getSubscribers() = viewModelScope.launch {
        _allAccountsEvent.postValue(repository.getAllAccounts())
    }
}