package com.android.mindtecktask.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.mindtecktask.R
import com.android.mindtecktask.model.Itemmodel
import java.lang.String
import java.util.*
import kotlin.CharSequence
import kotlin.Int

class CustomAdapter(private var mList: List<Itemmodel>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>(){

    var mFilterList: List<Itemmodel> = ArrayList<Itemmodel>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view_design, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]
        mFilterList = mList

        holder.imageView.setImageResource(ItemsViewModel.image)

        holder.textView.text = ItemsViewModel.text

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val textView: TextView = itemView.findViewById(R.id.textView)
    }

    fun filterList(filterlist: ArrayList<Itemmodel>) {
        mList = filterlist
        notifyDataSetChanged()
    }


}
