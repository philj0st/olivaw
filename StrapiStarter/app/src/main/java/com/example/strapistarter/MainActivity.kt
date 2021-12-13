package com.example.strapistarter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.strapistarter.databinding.ActivityMainBinding
import com.example.strapistarter.databinding.FragmentItemListBinding
import retrofit2.Callback
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    object RetrofitBuilder {
        private const val BASE_URL = "https://phils-strapi.herokuapp.com/api/"
        var service: StrapiService

        init {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            service = retrofit.create(StrapiService::class.java)
        }
    }
}