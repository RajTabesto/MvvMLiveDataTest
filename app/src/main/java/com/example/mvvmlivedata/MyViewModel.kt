package com.example.mvvmlivedata

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Random

@Suppress("unused")
class MyViewModel : ViewModel() {
    private val fruitList: MutableLiveData<List<String>> by lazy {
        MutableLiveData<List<String>>().also {
            loadUsers()
        }
    }

    fun getFruitList(): LiveData<List<String>> {
        return fruitList
    }

    private fun loadUsers() {
        // Do an asynchronous operation to fetch fruit list
        val myHandler = Handler(Looper.getMainLooper())
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
        }, 5000)
    }
}
