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

    lateinit var listView: RecyclerView //lateinit permite inicializar una propiedad no anulable por fuera del constructor
    lateinit var adapter: RecyclerView.Adapter<ItemAdapter.ItemViewHolder> //lateinit te ayuda cuando deseas asignar el valor de una propiedad después y no deseas usar comprobaciones de nulos (expresiones if, operador de acceso seguro o aserciones)
    val listItems = arrayListOf<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val layoutManager = LinearLayoutManager(this) //El layoutManager está asociado al RecyclerView. Es el que organiza las vistas dentro del RecyclerView.
        listView = list_feed
        listView.layoutManager = layoutManager

        adapter = ItemAdapter(listItems, this) //Decirle al listView lo que debe contener. En este caso listItems que es un array con Item.
        listView.adapter = adapter

        PkRSS.with(this).load("https://www.teo.gal/rss/novas").callback(this).async()
    }

    override fun onLoadFailed() {
    }

    override fun onPreload() {
    }

    override fun onLoaded(newArticles: MutableList<Article>?) {

        listItems.clear()

        newArticles?.mapTo(listItems) {

            Item(it.title, it.source, it.date)

        }

        adapter.notifyDataSetChanged()
    }
}
