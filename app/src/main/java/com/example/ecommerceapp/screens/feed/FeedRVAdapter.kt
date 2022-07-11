package com.example.ecommerceapp.screens.feed

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceapp.R
import com.example.ecommerceapp.screens.feed.model.ProductModel

class FeedRVAdapter(private val context: Context, private val listener: IFeedRVAdapter, private var allItems : ArrayList<ProductModel>):RecyclerView.Adapter<FeedRVAdapter.CartViewHolder>() {
    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.image)
        val title: TextView = itemView.findViewById(R.id.title)
        val price: TextView = itemView.findViewById(R.id.price)
        val addToCart: Button = itemView.findViewById(R.id.cartButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val viewHolder = CartViewHolder(LayoutInflater.from(context).inflate(R.layout.item_product, parent, false))
        viewHolder.addToCart.setOnClickListener{
            listener.onItemClicked(allItems[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val currentItem = allItems[position]
        Glide.with(holder.itemView.context).load(currentItem.getImage()).into(holder.image)
        holder.title.text = currentItem.getTitle()
        holder.price.text = "Price: ${currentItem.getPrice()}₹"
    }

    override fun getItemCount(): Int {
        return allItems.size
    }

}

interface IFeedRVAdapter {
    fun onItemClicked(item: ProductModel)
}