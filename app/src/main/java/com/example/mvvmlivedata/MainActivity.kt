package com.example.mvvmlivedata

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView: ListView = findViewById<View>(R.id.list) as ListView
        val progressBar = findViewById<ProgressBar>(R.id.progressbar)
        progressBar.visibility = View.VISIBLE
        val model: MyViewModel = ViewModelProvider(this).get(MyViewModel::class.java);
        model.getFruitList().observe(this, Observer<List<String>>() { fruits ->
            val adapter: ArrayAdapter<String> = ArrayAdapter(
                this,
                R.layout.list_item_row, R.id.tvUrl, fruits
            )
            // Assign adapter to ListView
            listView.adapter = adapter
            progressBar.visibility = View.GONE
        });
    }
}