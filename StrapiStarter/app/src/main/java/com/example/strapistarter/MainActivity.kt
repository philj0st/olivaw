package com.example.strapistarter

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.strapistarter.databinding.ActivityMainBinding
import com.example.strapistarter.databinding.FragmentItemListBinding
import okhttp3.Cache
import retrofit2.Callback
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var service: StrapiService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        service = RetrofitBuilder().build(this)
    }


    class RetrofitBuilder {
        private val BASE_URL = "https://phils-strapi.herokuapp.com/api/"
        fun build(ctx: Context):StrapiService {
            val cacheSize = (5 * 1024 * 1024).toLong()
            val myCache = Cache(ctx.cacheDir, cacheSize)

            fun hasNetwork(context: Context): Boolean? {
                var isConnected: Boolean? = false // Initial Value
                val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
                if (activeNetwork != null && activeNetwork.isConnected)
                    isConnected = true
                return isConnected
            }
            val okHttpClient = OkHttpClient.Builder()
                .cache(myCache)
                .addInterceptor { chain ->
                    var request = chain.request()
                    request = if (hasNetwork(ctx)!!)
                        request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                    else
                        request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
                    chain.proceed(request)
                }
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()

            return retrofit.create(StrapiService::class.java)
        }
    }
}