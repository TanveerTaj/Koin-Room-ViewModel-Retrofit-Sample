package com.example.trainman.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.trainman.R
import com.example.trainman.databinding.LayoutListItemGiphyBinding
import com.example.trainman.modal.GiphyData

class GiphyAdapter(private val listener: OnItemClickListener) :
    RecyclerView.Adapter<GiphyAdapter.CardHolder>() {

    var list: MutableList<GiphyData.Data> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = list.size

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class CardHolder(val binder: LayoutListItemGiphyBinding) :
        RecyclerView.ViewHolder(binder.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder =
        CardHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.layout_list_item_giphy, parent, false
            )
        )

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        val item = list[position]
        holder.binder.data = item
        holder.itemView.setOnClickListener {
            listener.onItemSelect(item.images.downsizedLarge.url, item.title)
        }
        holder.binder.giphyImg.transitionName = "transition $position"
    }

    fun appendList(list: List<GiphyData.Data>) {
        val lastIndex = itemCount
        this.list.addAll(list.toMutableList())
        notifyItemChanged(lastIndex - 1)
        notifyItemRangeInserted(lastIndex, list.size)
    }

    interface OnItemClickListener {
        fun onItemSelect(
            url: String, name: String
        )
    }
}