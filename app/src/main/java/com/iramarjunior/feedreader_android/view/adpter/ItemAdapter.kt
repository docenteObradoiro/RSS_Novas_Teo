package com.iramarjunior.feedreader_android.view.adpter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.iramarjunior.feedreader_android.R
import com.iramarjunior.feedreader_android.model.Item
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ItemAdapter(val list: ArrayList<Item>, val context: Context) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val title = view.findViewById(R.id.text_title_card) as TextView
        val date = view.findViewById(R.id.text_date_publication_card) as TextView
        val seeMore = view.findViewById(R.id.button_see_more) as Button
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val viewHolder =
            LayoutInflater.from(parent?.context).inflate(R.layout.item_list_feed, parent, false)
        val itemViewHolder = ItemViewHolder(viewHolder)

        return itemViewHolder
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        holder?.title?.text = list[position].title
        //holder?.author?.text = list[position].author
        holder?.date?.text =
            SimpleDateFormat("dd/MM/yyyy", Locale("gl", "SP")).format(Date(list[position].pubDate))
        holder?.seeMore?.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, list[position].link)
            context.startActivity(intent)
        }

        //DownloadImageTask(holder?.image!!).execute(list[position].image)
    }

    override fun getItemCount(): Int = list.size
}