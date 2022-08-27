package com.hasanali.kotlinroom.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class User(
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "last_name") val lastName: String?
) {
    @PrimaryKey(autoGenerate = true)
    var userId: Int = 0
}