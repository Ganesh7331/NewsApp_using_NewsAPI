package com.example.newsapplication

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapplication.databinding.NewsItemBinding
import com.squareup.picasso.Picasso
import kotlin.concurrent.timer

class NewsAdapter(val newss:List<Article>):RecyclerView.Adapter<NewsAdapter.newssViewHolder>() {

    inner class newssViewHolder(val binding:NewsItemBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): newssViewHolder {
        return newssViewHolder(NewsItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return newss.size
    }

    override fun onBindViewHolder(holder: newssViewHolder, position: Int) {

        Picasso.get().load(newss[position].urlToImage).into(holder.binding.thumbnail)
        holder.binding.Itemtitle.text=newss[position].title

        holder.binding.root.setOnClickListener{
            val intent= Intent(it.context,NewsScreen::class.java)
            intent.putExtra("name",newss[position].source.name)
            intent.putExtra("title",newss[position].title)
            intent.putExtra("author",newss[position].author)
            intent.putExtra("time",newss[position].publishedAt)
            intent.putExtra("image",newss[position].urlToImage)
            intent.putExtra("content",newss[position].content)
            intent.putExtra("site",newss[position].url)
            startActivity(it.context, intent, null)
        }

    }
}