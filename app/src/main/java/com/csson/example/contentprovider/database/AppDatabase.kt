package com.csson.example.contentprovider.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.csson.example.contentprovider.database.user.User
import com.csson.example.contentprovider.database.user.UserDao

@Database(entities = [User::class], version = 2)
abstract class AppDatabase: RoomDatabase(){
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        @JvmStatic
        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(AppDatabase::class) {
                INSTANCE ?: Room.databaseBuilder(context, AppDatabase::class.java, "cgms.db")
                    .fallbackToDestructiveMigration().build().also {
                    INSTANCE = it
                }
            }

        fun destroyInstance() {
            INSTANCE?.close()
            INSTANCE = null
        }
    }
}