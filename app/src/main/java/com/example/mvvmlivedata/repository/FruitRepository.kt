package com.example.mvvmlivedata.repository

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.mvvmlivedata.model.Fruit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FruitRepository(context: Context) {
    private val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "database-fruit"
    ).build()
    private val fruitDao = db.fruitDao()
    private val TAG = "FruitRepository"

    suspend fun getAllFruit(): List<Fruit> {
        val fruitsList: ArrayList<Fruit> = ArrayList()
        fruitsList.add(Fruit(name = "Mango"))
        fruitsList.add(Fruit(name = "Apple"))
        fruitsList.add(Fruit(name = "Orange"))
        fruitsList.add(Fruit(name = "Banana"))
        fruitsList.add(Fruit(name = "Grapes"))

        var fruits: List<Fruit> = fruitDao.getAll()

        if (fruits.isEmpty()) {
            Log.d(TAG, " list is empty we are going to create fruit in local database")
            for (fruit in fruitsList) {
                fruitDao.insert(fruit)
            }
            Log.d(TAG, " then we get back all fruits inserted ")
            fruits = fruitDao.getAll()
        } else {
            Log.d(TAG, " there are already fruit in local db room")
        }
        return fruits
    }
}