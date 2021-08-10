package com.soft.useraccounts.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.soft.useraccounts.data.model.AccountsEntity

@Dao
interface ApiDao {
    @Insert
    suspend fun insert(accounts: AccountsEntity): Long

    @Update
    suspend fun update(accounts: AccountsEntity)

    @Query("DELETE FROM accounts WHERE id = :id")
    suspend fun delete(id: Long)

    @Query("DELETE FROM accounts")
    suspend fun deleteAll()

    @Query("SELECT * FROM accounts")
    suspend fun getAll(): List<AccountsEntity>

    @Query("SELECT * FROM accounts WHERE name LIKE :name")
    suspend fun getSearch(name: String): List<AccountsEntity>
}