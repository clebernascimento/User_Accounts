package com.soft.useraccounts.ui.account

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soft.useraccounts.R
import com.soft.useraccounts.repository.AccountsRepository
import kotlinx.coroutines.launch

class AccountsViewModel(private val repository: AccountsRepository) : ViewModel() {

    private val _accountsStateEventData = MutableLiveData<AccountState>()
    val accountsStateEventData: LiveData<AccountState>
        get() = _accountsStateEventData

    private val _messageEventData = MutableLiveData<Int>()
    val messageEventData: LiveData<Int>
        get() = _messageEventData

    private fun insertAccounts(name: String, user: String, password: String, decription: String) =
        viewModelScope.launch {
            try {
                val id = repository.insertAccounts(name, user, password, decription)
                if (id > 0) {
                    _accountsStateEventData.value = AccountState.Inserted
                    _messageEventData.value = R.string.account_inserted_successfully
                }
            } catch (exception: Exception) {
                _messageEventData.value = R.string.account_error_to_insert
                Log.e(TAG, exception.toString())
            }
        }

    fun addUpdateAccounts(
        name: String,
        user: String,
        password: String,
        decription: String,
        id: Long = 0
    ) {
        if (id > 0) {
            updateAccounts(id, name, user, password, decription)
        } else {
            insertAccounts(name, user, password, decription)
        }
    }

    private fun updateAccounts(
        id: Long,
        name: String,
        user: String,
        password: String,
        decription: String
    ) = viewModelScope.launch {
        try {
            repository.updateAccounts(id, name, user, password, decription)
            _accountsStateEventData.value = AccountState.Update
            _messageEventData.value = R.string.account_update_successfully
        } catch (exception: Exception) {
            _messageEventData.value = R.string.account_error_to_update
            Log.e(TAG, exception.toString())
        }
    }

    fun removeAccounts(id: Long) = viewModelScope.launch {
        try {
            if (id > 0) {
                repository.deleteAccounts(id)
                _accountsStateEventData.value = AccountState.Deleted
                _messageEventData.value = R.string.account_deleted_successfully
            }
        } catch (ex: Exception) {
            _messageEventData.value = R.string.account_error_to_delete
            Log.e(TAG, ex.toString())
        }

    }

    sealed class AccountState {
        object Inserted : AccountState()
        object Update : AccountState()
        object Deleted : AccountState()
    }

    companion object {
        private val TAG = AccountsViewModel::class.java.simpleName
    }
}