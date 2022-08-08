package com.example.ecommerceapp.presentation.main.feed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceapp.R
import com.example.ecommerceapp.model.ProductModel

class FeedRVAdapter(
    private val listener: IFeedRVAdapter,
    private var allItems: MutableList<ProductModel>? = null,
) : RecyclerView.Adapter<FeedRVAdapter.CartViewHolder>() {

    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.image)
        val title: TextView = itemView.findViewById(R.id.title)
        val price: TextView = itemView.findViewById(R.id.price)
        val addToCart: TextView = itemView.findViewById(R.id.quantity)
        val add: TextView = itemView.findViewById(R.id.add)
        val subtract: TextView = itemView.findViewById(R.id.remove)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {

        val viewHolder = CartViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false))

        viewHolder.add.setOnClickListener {
            allItems?.get(viewHolder.adapterPosition)?.let { item -> listener.onAddClicked(item) }
        }

        viewHolder.addToCart.setOnClickListener {
            allItems?.get(viewHolder.adapterPosition)?.let { item -> listener.onItemClicked(item) }
        }
        viewHolder.subtract.setOnClickListener {
            allItems?.get(viewHolder.adapterPosition)
                ?.let { item -> listener.onSubtractClicked(item) }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val currentItem = allItems?.get(position)
        Glide.with(holder.itemView.context).load(currentItem?.getImage()).into(holder.image)
        holder.title.text = currentItem?.getTitle()
        holder.price.text = "Price: ${currentItem?.getPrice()}₹"
    }

    override fun getItemCount(): Int {
        return allItems?.size ?: 0
    }

}

interface IFeedRVAdapter {
    fun onItemClicked(item: ProductModel)
    fun onAddClicked(item: ProductModel)
    fun onSubtractClicked(item: ProductModel)
}