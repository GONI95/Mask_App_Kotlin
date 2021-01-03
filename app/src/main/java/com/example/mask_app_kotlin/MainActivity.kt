package com.example.mask_app_kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mask_app_kotlin.model.Store


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val storeAdapter = StoreAdapter(this)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(
                this@MainActivity, RecyclerView.VERTICAL, false
            )
            adapter = storeAdapter
        }

        val items = listOf(
            Store("asd","asd","asd",2.2,2.3,"asd","asd","asd","asd")
        )
        storeAdapter.updateItems(items)
    }
}