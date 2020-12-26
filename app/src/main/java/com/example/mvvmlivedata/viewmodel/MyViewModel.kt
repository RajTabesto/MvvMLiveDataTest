package com.example.mvvmlivedata.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mvvmlivedata.model.Fruit
import com.example.mvvmlivedata.repository.FruitRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Random

class MyViewModel(application: Application) : AndroidViewModel(application) {
    private val fruitRepository = FruitRepository(application)
    private val fruitList: MutableLiveData<List<String>> by lazy {
        MutableLiveData<List<String>>().also {
            loadFruits()
        }
    }

    fun getFruitList(): LiveData<List<String>> {
        return fruitList
    }

    private fun loadFruits() {
        // Do an asynchronous operation to fetch fruit list
        viewModelScope.launch {
            val fruitsStringList: MutableList<String> = ArrayList()
            val fruits: List<Fruit> = fruitRepository.getAllFruit()
            for (fruit in fruits) {
                fruit.name?.let { fruitsStringList.add(it) }
            }
            val seed = System.nanoTime()
            fruitsStringList.shuffle(Random(seed))
            fruitList.value = fruitsStringList
        }
    }
}
