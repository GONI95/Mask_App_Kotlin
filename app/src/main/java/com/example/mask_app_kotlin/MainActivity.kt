package com.example.mask_app_kotlin

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mask_app_kotlin.model.Store


class MainActivity : AppCompatActivity() {
    // 코틀린에선 되도록 val을 쓰려고 하는 편, 그렇기에 아래와 같은 확장함수가 생겨남
    private val viewModel : MainViewModel by viewModels()
    //  private lateinit var viewModel : MainViewModel
    //  val이 아니라 변경될 수 있어 불안전
    //  private val viewModel : MainViewModel? = null
    //  코틀린 언어를 사용했지만 자바 방식의 문법처럼 초기화한 것임
    // fragment-ktx에서 제공하는 확장함수 중 하나

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

        viewModel.apply {
            itemLiveData.observe(this@MainActivity, Observer { stores ->
                storeAdapter.updateItems(stores)
            })
            loadingLiveData.observe(this@MainActivity, Observer { isloading ->
                findViewById<ProgressBar>(R.id.progressBar).visibility =
                    if (isloading) View.VISIBLE else View.GONE
            })
        }


    }
}