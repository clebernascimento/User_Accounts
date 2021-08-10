package com.soft.useraccounts.repository

import com.soft.useraccounts.data.model.AccountsEntity

interface AccountsRepository {
    suspend fun insertAccounts(name: String, user: String, password: String, description: String): Long

    suspend fun updateAccounts(id: Long, name: String, user: String, password: String, description: String)

    suspend fun deleteAccounts(id: Long)

    suspend fun deleteAllAccounts()

    suspend fun getAllAccounts(): List<AccountsEntity>
}