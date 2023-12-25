package com.example.theworld

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.theworld.adapters.DataAdapter
import com.example.theworld.api.NewsApi
import com.example.theworld.data.model.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchButton = findViewById<ImageButton>(R.id.searchButton)
        val searchText = findViewById<EditText>(R.id.searchText)
        val retrofit = Retrofit.Builder().baseUrl(Constants.NewsAPIConstants.baseURL).addConverterFactory(GsonConverterFactory.create()).build()
        val call = retrofit.create(NewsApi::class.java)
        val recyclerview = findViewById<RecyclerView>(R.id.recentNewsRecycler)

        // since keyword is an optional parameter, if we pass null then there will be no query
        // api will show us recent news by base url
        call.getNews(null).enqueue(object : Callback<NewsResponse> {
            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Toast.makeText(applicationContext, getString(R.string.response_exception), Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if(response.isSuccessful && response.body()!!.totalResults > 0){
                    recyclerview.layoutManager = LinearLayoutManager(applicationContext)
                    recyclerview.adapter = DataAdapter(response.body()!!.results)
                }
            }
        })

        // search handle

        searchButton.setOnClickListener(View.OnClickListener {
            if (searchText.text.toString().isNotEmpty()){
                var keyword = searchText.text.toString()
                call.getNews(keyword.replace(" ", "-")).enqueue(object : Callback<NewsResponse> {
                    override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                        Toast.makeText(applicationContext, getString(R.string.response_exception), Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                        if(response.isSuccessful && response.body()!!.totalResults > 0){
                            val intent = Intent(applicationContext, SearchActivity::class.java)
                            val bundle = Bundle()
                            bundle.putSerializable("result", response.body())
                            intent.putExtras(bundle)
                            startActivity(intent)
                        }
                        else {
                            Toast.makeText(applicationContext, getString(R.string.search_exception), Toast.LENGTH_LONG).show()
                        }
                    }

                })

            }
        })



    }
}