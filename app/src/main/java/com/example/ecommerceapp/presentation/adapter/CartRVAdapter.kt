package com.example.ecommerceapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceapp.model.CartItem

class CartRVAdapter(private val context: Context, private val listener: ICartRVAdapter):RecyclerView.Adapter<CartRVAdapter.CartViewHolder>() {

    var allItems = ArrayList<CartItem>()

    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.image)
        val title: TextView = itemView.findViewById(R.id.title)
        val price: TextView = itemView.findViewById(R.id.price)
        val quantity: TextView = itemView.findViewById(R.id.quantity)
        val deleteButton: ImageView = itemView.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
       val viewHolder = CartViewHolder(LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false))
        viewHolder.deleteButton.setOnClickListener{
            listener.onItemClicked(allItems[viewHolder.adapterPosition])
        }
       return viewHolder
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val currentItem = allItems[position]
        Glide.with(holder.itemView.context).load(currentItem.image).into(holder.image)
        holder.title.text = currentItem.title
        "Price: ${currentItem.price}₹".also { holder.price.text = it }
        "Quantity: ${currentItem.quantity}".also { holder.quantity.text = it }
    }

    override fun getItemCount(): Int {
        return allItems.size
    }

    fun updateList(newList: List<CartItem>) {
        allItems.clear()
        allItems.addAll(newList)

        notifyDataSetChanged()
    }
}

interface ICartRVAdapter {
    fun onItemClicked(cartItem: CartItem)
}