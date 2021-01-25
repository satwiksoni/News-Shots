package com.example.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*

class NewsAdaptor( private val listener:NewsItemClicked):RecyclerView.Adapter<NewsAdaptor.NewsViewHolder>() {
    var list:ArrayList<News> =ArrayList()
    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        val viewHolder=NewsViewHolder(itemView)
        itemView.setOnClickListener {
            listener.onItemClicked(list[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.itemView.tv1.text=list[position].url
    }

    override fun getItemCount(): Int {
        return list.size
    }
     fun updateNews(updateNews:ArrayList<News>)
     {
         list.clear()
         list.addAll(updateNews)
         notifyDataSetChanged()
     }
}
interface NewsItemClicked
{
    fun onItemClicked(item: News)
}