package com.quokkalabs.news.modules.adapter

import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.quokkalabs.news.R
import com.quokkalabs.news.databinding.NewsListBinding
import com.quokkalabs.news.model.Articles
import com.squareup.picasso.Picasso

class NewsListAdapter(
    var context: Context,
    var searchDataList: MutableList<Articles>?
) :
    RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewsListAdapter.ViewHolder {
        return ViewHolder(
            parent.context.getSystemService(LayoutInflater::class.java).inflate(
                R.layout.news_list,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return searchDataList?.size ?: 0
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        NewsListBinding.bind(holder.itemView).apply {
            searchDataList?.get(position)?.apply {
                if(urlToImage==null){
                    Picasso.with(context).load(R.drawable.defaultpic).into(image)
                }else{
                    Picasso.with(context).load(urlToImage).error(R.drawable.defaultpic).into(image)
                }
                authors.text = author
                textTime.text = publishedAt
                textHeading.text = title
                textSubHeading.text = description
            }

        }

    }

}