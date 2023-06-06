package com.example.bugrahan_topal_odev6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class DetailPage : AppCompatActivity() {
    lateinit var ImageView2:ImageView
    lateinit var titleProduct:TextView
    lateinit var desProduct:TextView
    lateinit var stockProduct:TextView
    lateinit var ratingProduct:TextView
    lateinit var categoryProduct:TextView
    lateinit var priceProduct:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_page)
        Log.d("DetailPage",ProductPage.detailProduct.toString())
        titleProduct=findViewById(R.id.titleProduct)
        desProduct=findViewById(R.id.desProduct)
        stockProduct=findViewById(R.id.stockProduct)
        ratingProduct=findViewById(R.id.ratingProduct)
        categoryProduct=findViewById(R.id.catagoryProduct)
        priceProduct=findViewById(R.id.priceProduct)
        ImageView2=findViewById(R.id.imageView2)

        titleProduct.text=ProductPage.detailProduct.title
        desProduct.text=ProductPage.detailProduct.description
        stockProduct.text="Stock:${ProductPage.detailProduct.stock.toString()}"
        ratingProduct.text="Rating:${ProductPage.detailProduct.rating.toString()}"
        categoryProduct.text="Category:${ProductPage.detailProduct.category}"
        priceProduct.text="Price:${ProductPage.detailProduct.price.toString()}"

        Glide.with(this)
            .load(ProductPage.detailProduct.thumbnail)
            .override(400, 200)
            .centerCrop()
            .into(ImageView2)





    }
}