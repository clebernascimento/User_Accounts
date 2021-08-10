package com.soft.useraccounts.repository

import com.soft.useraccounts.data.dao.ApiDao
import com.soft.useraccounts.data.model.AccountsEntity

class DataBaseDataSource(private val apiDao: ApiDao) : AccountsRepository {

    override suspend fun insertAccounts(
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

    override suspend fun updateAccounts(
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

    override suspend fun deleteAccounts(id: Long) {
        apiDao.delete(id)
    }

    override suspend fun deleteAllAccounts() {
        apiDao.deleteAll()
    }

    override suspend fun getAllAccounts(): List<AccountsEntity> {
        return apiDao.getAll()
    }

    override suspend fun getSearch(name: String): List<AccountsEntity> {
       return apiDao.getSearch(name)
    }
}