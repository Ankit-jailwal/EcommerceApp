package com.example.ecommerceapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.screens.cart.database.CartItem

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
        holder.image.setImageURI(currentItem.image.toUri())
        holder.title.text = currentItem.title
        holder.price.text = "Price: ${currentItem.price}₹"
        holder.quantity.text = "Quantity: ${currentItem.quantity}"
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