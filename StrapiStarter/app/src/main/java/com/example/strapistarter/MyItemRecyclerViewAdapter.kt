package com.example.strapistarter

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

import com.example.strapistarter.placeholder.PlaceholderContent.PlaceholderItem
import com.example.strapistarter.databinding.FragmentItemBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyItemRecyclerViewAdapter(
    private val values: List<Data>,
    private val service: StrapiService
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.nameView.text = item.attributes.title
        holder.priceView.text = item.attributes.price.toString()
        holder.descView.text = item.attributes.description
        // fetch the image url
        service.getFile(item.id).enqueue(object : Callback<File> {
            override fun onResponse(call: Call<File>, response: Response<File>) {
                response.body()?.let {
                    Log.d("ITM", it.formats.thumbnail.url)
                    Glide.with(holder.itemView.context)
                        .load("https://phils-strapi.herokuapp.com"+it.formats.thumbnail.url)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.imageView)
                }

            }

            override fun onFailure(call: Call<File>, t: Throwable) {
                Log.d("ITM", t.message.toString())
            }
        })
//        Glide.with(holder.itemView.context).load()
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val nameView: TextView = binding.productName
        val priceView: TextView = binding.productPrice
        val imageView: ImageView = binding.productImage
        val descView: TextView = binding.productDesc

        override fun toString(): String {
            return super.toString() + " '" + nameView.text + "'"
        }
    }

}