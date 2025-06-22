package com.mobile.bce

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobile.bce.adapter.ProductAdapter
import com.mobile.bce.model.Product
import com.mobile.bce.model.Rating
import com.mobile.bce.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var makePostbtn: Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        recyclerView = findViewById(R.id.productRecyclerView)
        makePostbtn = findViewById(R.id.makePost)
        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchProducts()

        makePostbtn.setOnClickListener{
            postStaticProduct()
        }
    }

    private fun fetchProducts() {
        RetrofitClient.api.getProducts()
            .enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                if (response.isSuccessful) {
                    val products = response.body() ?: emptyList()
                    recyclerView.adapter = ProductAdapter(products)
                } else {
                    Toast.makeText(this@ProductActivity, "Failed to load", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                Toast.makeText(this@ProductActivity, "Error: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun postStaticProduct() {

        val sampleProduct = Product(
            id = 21, // ID might be ignored on server side
            title = "Static Product",
            price = 99.99,
            description = "This is a sample static product.",
            category = "electronics",
            image = "https://i.pravatar.cc",
            rating = Rating(rate = 4.5, count = 200)
        )

        val call = RetrofitClient.api.postProduct(sampleProduct)
        call.enqueue(object : Callback<Product> {
            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@ProductActivity, "Product posted successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@ProductActivity, "Failed: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Product>, t: Throwable) {
                Toast.makeText(this@ProductActivity, "Error: ${t.localizedMessage}", Toast.LENGTH_LONG).show()
            }
        })
    }


}
