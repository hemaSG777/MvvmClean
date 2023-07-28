package com.example.mvvmclean.utils

import com.example.mvvmclean.model.ImageModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface ImageApi {
    @GET("rest")
    fun getImageList(
        @Query("method") method: String = "flickr.photos.getRecent",
        @Query("per_page") per_page: Int = 20,
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = "6f102c62f41998d151e5a1b48713cf13",
        @Query("format") format: String = "json",
        @Query("nojsoncallback") nojsoncallback: Int = 1,
        @Query("extras") extras: String = "url_s"
    ): Call<ImageModel>
    //or
    // when response is only this much then add Call<List<Model>
//    val id: Double,
//    val logo: String,
//    val name: String,
//    val slogan: String,


    companion object {
        private val customInterceptor = Interceptor {
            val requestBuilder = it.request().newBuilder()
                .addHeader("api_key", "")
                .build()
            it.proceed(requestBuilder)
        }

        //if in input api key is given then use interceptor

        private val client = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(customInterceptor)
            .build()
        var BASE_URL = "https://api.flickr.com/services/"

        fun imageList(): ImageApi {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(client)
                .build()
            return retrofit.create(ImageApi::class.java)
        }
    }
}
