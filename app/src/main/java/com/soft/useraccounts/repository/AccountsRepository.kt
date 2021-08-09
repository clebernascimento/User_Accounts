package com.soft.useraccounts.repository

import com.soft.useraccounts.data.model.AccountsEntity

interface AccountsRepository {
    suspend fun insertSubscriber(name: String, user: String, password: String, description: String): Long

    suspend fun updateSubscriber(id: Long, name: String, user: String, password: String, description: String)

    suspend fun deleteSubscriber(id: Long)

    suspend fun deleteAllSubscribers()

    suspend fun getAllSubscribers(): List<AccountsEntity>
}