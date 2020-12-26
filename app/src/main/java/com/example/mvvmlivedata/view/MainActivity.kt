package com.example.mvvmlivedata.view

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmlivedata.viewmodel.MyViewModel
import com.example.mvvmlivedata.R
import com.example.mvvmlivedata.databinding.ActivityMainBinding
import com.example.mvvmlivedata.databinding.ListItemRowBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bindingActivityMain = ActivityMainBinding.inflate(layoutInflater)
        val bindingItemRow = ListItemRowBinding.inflate(layoutInflater)
        setContentView(bindingActivityMain.root)

        bindingActivityMain.progressbar.visibility = View.VISIBLE
        val model: MyViewModel = ViewModelProvider(this).get(MyViewModel::class.java);
        model.getFruitList().observe(this, Observer<List<String>>() { fruits ->
            val adapter: ArrayAdapter<String> = ArrayAdapter(
                this,
                R.layout.list_item_row,
                R.id.tvUrl, fruits
            )
            // Assign adapter to ListView
            bindingActivityMain.list.adapter = adapter
            bindingActivityMain.progressbar.visibility = View.GONE
        });
    }
}