package com.example.mvvmlivedata.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvmlivedata.model.Fruit

@Dao
interface FruitDao {
    @Query("SELECT * FROM fruit")
    suspend fun getAll(): List<Fruit>

    @Query("SELECT * FROM fruit WHERE uid IN (:fruitIds)")
    fun loadAllByIds(fruitIds: IntArray): List<Fruit>

    @Query("SELECT * FROM fruit WHERE name LIKE :fruitName ")
    fun findByName(fruitName: String): Fruit

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(fruit: Fruit)

    //@Insert(onConflict = OnConflictStrategy.REPLACE)
    //fun insertAll(vararg fruits: ArrayList<Fruit>)
}