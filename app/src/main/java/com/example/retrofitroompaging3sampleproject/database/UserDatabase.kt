package com.example.retrofitroompaging3sampleproject.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.retrofitroompaging3sampleproject.database.dao.UserDao
import com.example.retrofitroompaging3sampleproject.model.User

@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
}