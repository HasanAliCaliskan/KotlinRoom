package com.hasanali.kotlinroom.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.hasanali.kotlinroom.model.User
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
interface UserDao {
    // parametre alarak query
    @Query("SELECT * FROM user WHERE userId = :id")
    fun getId(id: String): Flowable<List<User>>

    @Query("SELECT * FROM user")
    fun getAll(): Flowable<List<User>>

    @Insert
    fun insert(user: User): Completable

    @Delete
    fun delete(user: User): Completable
}


