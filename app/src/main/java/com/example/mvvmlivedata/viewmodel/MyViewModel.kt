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

class MyViewModel(application: Application) : AndroidViewModel(application), FruitRepository.FruitRepositoryListener {
    private val fruitRepository = FruitRepository(application, this)
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
        /*val myHandler = Handler(Looper.getMainLooper())
        myHandler.postDelayed({
            val fruitsStringList: MutableList<String> = ArrayList()
            fruitsStringList.add("Mango")
            fruitsStringList.add("Apple")
            fruitsStringList.add("Orange")
            fruitsStringList.add("Banana")
            fruitsStringList.add("Grapes")
            val seed = System.nanoTime()
            fruitsStringList.shuffle(Random(seed))
            fruitList.setValue(fruitsStringList)
        }, 5000)*/
        viewModelScope.launch {
            fruitRepository.getAllFruit()
        }
    }

    override fun onAllFruitRetrievedSuccessfully(fruits: List<Fruit>) {
        val fruitsStringList: MutableList<String> = ArrayList()
        for (fruit in fruits) {
            fruit.name?.let { fruitsStringList.add(it) }
        }
        val seed = System.nanoTime()
        fruitsStringList.shuffle(Random(seed))
        fruitList.value = fruitsStringList
    }
}
