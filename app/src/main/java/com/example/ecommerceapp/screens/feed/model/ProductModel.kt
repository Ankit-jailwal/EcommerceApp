package com.example.ecommerceapp.screens.feed.model

class ProductItem {
    private var image:String
    private var title:String
    private var price: Int
    private var id : Int
    constructor(image: String, title: String, price: Int, id: Int) {
        this.image = image
        this.title = title
        this.price = price
        this.id = id
    }

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