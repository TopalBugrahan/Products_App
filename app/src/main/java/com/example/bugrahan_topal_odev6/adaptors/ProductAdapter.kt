package com.example.bugrahan_topal_odev6.adaptors

import android.app.Activity
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.bugrahan_topal_odev6.R
import com.example.bugrahan_topal_odev6.models.Product

class ProductAdapter(private val context: Activity, private val productList: List<Product>):ArrayAdapter<Product>(context, R.layout.costum_product_list_view, productList ){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rootView = context.layoutInflater.inflate(R.layout.costum_product_list_view, null, true)
        val productTitle = rootView.findViewById<TextView>(R.id.productTitle)
        val productDes = rootView.findViewById<TextView>(R.id.productDes)
        val productPrice = rootView.findViewById<TextView>(R.id.productPrice)
        val productRating = rootView.findViewById<TextView>(R.id.productRating)
        val imageView = rootView.findViewById<ImageView>(R.id.imageView)

        val product = productList.get(position)
        productTitle.text=product.title
        productDes.text=product.description
        productPrice.text="${product.price.toString()} TL"
        productRating.text=product.rating.toString()

        Glide.with(context)
            .load(product.thumbnail)
            .override(200, 100)
            .centerCrop()
            .into(imageView)

        return rootView
    }
}