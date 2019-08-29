package com.iramarjunior.feedreader_android.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iramarjunior.feedreader_android.R
import com.iramarjunior.feedreader_android.model.Item
import com.iramarjunior.feedreader_android.view.adpter.ItemAdapter
import com.pkmmte.pkrss.Article
import com.pkmmte.pkrss.Callback
import com.pkmmte.pkrss.PkRSS
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), Callback {

    lateinit var listView: RecyclerView
    lateinit var adapter: RecyclerView.Adapter<ItemAdapter.ItemViewHolder>
    val listItens = arrayListOf<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layoutManager = LinearLayoutManager(this)
        listView = list_feed
        listView.layoutManager = layoutManager

        adapter = ItemAdapter(listItens, this)
        listView.adapter = adapter

        PkRSS.with(this).load("https://rss.tecmundo.com.br/feed").callback(this).async()
    }

    override fun onLoadFailed() {
    }

    override fun onPreload() {
    }

    override fun onLoaded(newArticles: MutableList<Article>?) {

        listItens.clear()

        newArticles?.mapTo(listItens) {

            Item(it.title, it.author, it.date, it.source, it.enclosure.url)
        }

        adapter.notifyDataSetChanged()
    }
}
