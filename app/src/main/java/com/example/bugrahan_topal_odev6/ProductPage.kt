package com.example.bugrahan_topal_odev6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import com.example.bugrahan_topal_odev6.adaptors.ProductAdapter
import com.example.bugrahan_topal_odev6.configs.ApiClient
import com.example.bugrahan_topal_odev6.models.Product
import com.example.bugrahan_topal_odev6.models.TenProducts
import com.example.bugrahan_topal_odev6.services.DummyService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductPage : AppCompatActivity() {
    lateinit var productsListView:ListView
    lateinit var aramaEditText:EditText
    lateinit var btnAra:Button
    var productList= mutableListOf<Product>()
    var productListTemp= mutableListOf<Product>()
    lateinit var dummyService: DummyService
    lateinit var  customAdapter:ArrayAdapter<Product>
    companion object{
        lateinit var detailProduct:Product
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_page)
        productsListView=findViewById(R.id.productsListView)
        aramaEditText=findViewById(R.id.aramaEditText)
        btnAra=findViewById(R.id.btnAra)

        dummyService=ApiClient.getClient().create(DummyService::class.java)
        dummyService.getTenProduct().enqueue(object:Callback<TenProducts>{
            override fun onResponse(call: Call<TenProducts>, response: Response<TenProducts>) {
                Log.d("products",response.body().toString())

                for(product in response.body()!!.products){
                    productList.add(product)
                }
                customAdapter = ProductAdapter(this@ProductPage,productList)
                productsListView.adapter=customAdapter
            }

            override fun onFailure(call: Call<TenProducts>, t: Throwable) {
                Log.e("eroor","server error")
            }

        })

        btnAra.setOnClickListener(btnAraOnClickListener)
        productsListView.setOnItemClickListener { adapterView, view, i, l ->
            detailProduct=productList.get(i)
            val intent = Intent(this, DetailPage::class.java)
            startActivity(intent)
        }
    }
    val btnAraOnClickListener=View.OnClickListener {
        val aramaText=aramaEditText.text.toString()
        dummyService.searchProducts(aramaText).enqueue(object :Callback<TenProducts>{
            override fun onResponse(call: Call<TenProducts>, response: Response<TenProducts>) {
                Log.d("searchProduct",response.body().toString())
                productListTemp=productList
                productList.clear()
                for(product in response.body()!!.products){
                    productList.add(product)
                }
                customAdapter.notifyDataSetChanged()

            }

            override fun onFailure(call: Call<TenProducts>, t: Throwable) {
                Log.e("eroor","server error")
            }

        })

    }
}