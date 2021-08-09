package com.soft.useraccounts.repository

import com.soft.useraccounts.data.dao.ApiDao
import com.soft.useraccounts.data.model.AccountsEntity

class DataBaseDataSource(private val apiDao: ApiDao) : AccountsRepository {
    override suspend fun insertSubscriber(
        name: String,
        user: String,
        password: String,
        description: String
    ): Long {
        val account = AccountsEntity(
            name = name,
            user = user,
            password = password,
            description = description
        )
        return apiDao.insert(account)
    }

    override suspend fun updateSubscriber(
        id: Long,
        name: String,
        user: String,
        password: String,
        description: String
    ) {
        val account = AccountsEntity(
            id = id,
            name = name,
            user = user,
            password = password,
            description = description
        )
        apiDao.update(account)
    }

    override suspend fun deleteSubscriber(id: Long) {
        apiDao.delete(id)
    }

    override suspend fun deleteAllSubscribers() {
        apiDao.deleteAll()
    }

    override suspend fun getAllSubscribers(): List<AccountsEntity> {
        return apiDao.getAll()
    }
}