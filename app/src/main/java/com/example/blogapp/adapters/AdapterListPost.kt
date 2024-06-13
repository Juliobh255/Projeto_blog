package com.example.blogapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.blogapp.R
import com.example.blogapp.model.ModelPost

class AdapterListPost(
    context: Context,
    private val listPost: List<ModelPost?>?,
    private var onClickEvent: AdapterListPost.OnclickListener
) :
    RecyclerView.Adapter<AdapterListPost.ViewHolder>() {

    class ViewHolder(viewHolder: View) : RecyclerView.ViewHolder(viewHolder) {
        val textTitle: TextView
        val textDescription: TextView
        val layoutPost: LinearLayout

        init {
            textTitle = viewHolder.findViewById(R.id.textTitlePost)
            textDescription = viewHolder.findViewById(R.id.textPostDescription)
            layoutPost = viewHolder.findViewById(R.id.layoutPost)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterListPost.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_adapter_list, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterListPost.ViewHolder, position: Int) {
        holder.textTitle.text = listPost?.get(position)?.title
        holder.textDescription.text = listPost?.get(position)?.description

        holder.layoutPost.setOnClickListener {
            returnItemDetails(listPost?.get(position))

        }

    }

    private fun returnItemDetails(modelPost: ModelPost?) {
        if (modelPost != null) {
            onClickEvent.onClickItem(modelPost)
        }

    }

    override fun getItemCount() = listPost!!.size

    interface OnclickListener : View.OnClickListener {
        fun onClickItem(position: ModelPost)
    }
}