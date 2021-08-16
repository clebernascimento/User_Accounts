package com.soft.useraccounts.ui.accountList

import androidx.lifecycle.*
import com.soft.useraccounts.data.model.AccountsEntity
import com.soft.useraccounts.repository.AccountsRepository
import com.soft.useraccounts.util.Resources
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class AccountListViewModel(
    private val repository: AccountsRepository
) : ViewModel() {

    private val _allAccountsEvent = MutableLiveData<List<AccountsEntity>>()
    val allAccountsEvent: LiveData<List<AccountsEntity>>
        get() = _allAccountsEvent

    fun getAccounts() = liveData(Dispatchers.IO) {
        emit(Resources.loading(data = null))
        try {
            emit(Resources.success(data = repository.getAllAccounts()))
        } catch (exception: Exception) {
            emit(
                Resources.error(data = null, message = exception.message ?: "Erro na lista")
            )
        }
    }

    fun getSearch(name: String?) = liveData(Dispatchers.IO) {
        emit(Resources.loading(data = null))
        try {
            emit(Resources.success(data = name?.let { repository.getSearch(it) }))
        } catch (exception: Exception) {
            emit(
                Resources.error(data = null, message = exception.message ?: "Erro na pesquisa")
            )
        }
    }
}