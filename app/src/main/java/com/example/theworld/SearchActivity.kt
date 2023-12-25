package com.example.theworld

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.theworld.adapters.DataAdapter
import com.example.theworld.data.model.NewsResponse

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        val recyclerview = findViewById<RecyclerView>(R.id.searchRecycler)

        val requestResult : NewsResponse = intent.extras?.getSerializable("result") as NewsResponse

        if (requestResult.results.isNotEmpty()) {
            recyclerview.layoutManager = LinearLayoutManager(applicationContext)
            recyclerview.adapter = DataAdapter(requestResult.results)
        }

    }
}