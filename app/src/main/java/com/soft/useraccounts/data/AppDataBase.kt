package com.soft.useraccounts.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.soft.useraccounts.data.dao.ApiDao
import com.soft.useraccounts.data.model.AccountsEntity

@Database(entities = [AccountsEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract val apiDao: ApiDao

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            synchronized(this) {
                var instance: AppDataBase? = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        AppDataBase::class.java,
                        "app_database"
                    ).build()
                }
                return instance
            }
        }
    }
}