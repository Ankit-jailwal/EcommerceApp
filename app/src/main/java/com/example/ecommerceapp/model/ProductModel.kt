package com.example.ecommerceapp.model

class ProductModel(
    private var image: String,
    private var title: String,
    private var price: Int,
    private var id: Int
) {

    fun getImage(): String {
        return image
    }
    fun getTitle(): String {
        return title
    }
    fun getPrice(): Int {
        return price
    }
    fun getId(): Int {
        return id
    }
}