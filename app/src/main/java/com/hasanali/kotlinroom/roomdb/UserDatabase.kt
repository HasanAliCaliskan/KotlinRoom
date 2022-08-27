package com.hasanali.kotlinroom.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hasanali.kotlinroom.model.User

@Database(entities = [User::class], version = 1)
abstract class UserDatabase: RoomDatabase(){
    abstract fun userDao(): UserDao
}



