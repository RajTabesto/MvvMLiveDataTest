package com.example.mvvmlivedata.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mvvmlivedata.model.Fruit

@Database(entities = [Fruit::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun fruitDao(): FruitDao
}
