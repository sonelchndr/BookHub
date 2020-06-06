package com.sonelchndr.bookhub.database

import androidx.room.Database
import com.sonelchndr.bookhub.database.BookDao
import androidx.room.RoomDatabase

@Database(entities = [BookEntity::class],version = 1)
abstract class BookDatabase:RoomDatabase() {


    abstract fun bookDao(): BookDao
}