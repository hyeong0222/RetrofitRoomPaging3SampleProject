package com.example.retrofitroompaging3sampleproject.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,

    @ColumnInfo(name = "login")
    val login: String? = "",

    @SerializedName("avatar_url")
    @ColumnInfo(name = "avatarUrl")
    val avatarUrl: String? = "",
)
