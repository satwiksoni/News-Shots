package com.example.news

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity(), NewsItemClicked {
   lateinit var  mnews_adaptor:NewsAdaptor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fetch()
         mnews_adaptor=NewsAdaptor(this)
        rv1.layoutManager=LinearLayoutManager(this)
        rv1.adapter=mnews_adaptor

    }
 private fun fetch()
 {
     val url="http://newsapi.org/v2/everything?q=bitcoin&from=2020-11-27&sortBy=publishedAt&apiKey=f7edf6cc651c417885136a095282206d"
     val jsonObj = JsonObjectRequest(
         Request.Method.GET,url ,null,
         {
          val jsonObjectArray=it.getJSONArray("articles")
             val data=ArrayList<News>()
             for(i in 0 until jsonObjectArray.length())
             {
                 val jsonObject=jsonObjectArray.getJSONObject(i)
                 data.add(News(
                     jsonObject.getString("title"),
                     jsonObject.getString("author"),
                     jsonObject.getString("url"),
                     jsonObject.getString("urlToImage"),
                     ))
             }
         mnews_adaptor.updateNews(data)
         },
         {

         })

     MySingleton.getInstance(this).addToRequestQueue(jsonObj)

 }
    override fun onItemClicked(item: News) {
        Toast.makeText(this,"lets talk ${item.title}",Toast.LENGTH_LONG).show()
    }
}