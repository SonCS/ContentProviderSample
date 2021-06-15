package com.csson.example.contentprovider.database.user

import androidx.room.*

@Dao
interface UserDao {
    @Query("select * from user")
    fun getAll(): List<User>

    @Query("select * from user where uid in (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query("select * from user where uid == :userId")
    fun getUserById(userId: Int): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg user: User)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(vararg user: User)

    @Delete
    fun delete(user: User)
}