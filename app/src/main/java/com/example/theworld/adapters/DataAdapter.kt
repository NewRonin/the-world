package com.example.theworld.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.theworld.R
import com.example.theworld.data.model.NewsItem

class DataAdapter (val list : List<NewsItem>): RecyclerView.Adapter<DataAdapter.MyViewHolder>() {

    class MyViewHolder(item : View) : RecyclerView.ViewHolder(item){
        var newsTitle = item.findViewById<TextView>(R.id.newsText)
        var newsImage = item.findViewById<ImageView>(R.id.newsImage)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        val news = list.get(position)
        holder.newsTitle.text = news.title
        Glide.with(holder.itemView.context).load(news.image_url).into(holder.newsImage)

    }

    override fun getItemCount() = list.size

}