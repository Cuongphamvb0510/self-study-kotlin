package com.example.studyandroid.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    // 1) Gốc của API — phải kết thúc bằng /
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    // 2) Log request/response ra Logcat (debug)
    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    // 3) OkHttp: tầng gửi HTTP thật
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(logging)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    // 4) Retrofit: gắn baseUrl + OkHttp + parse JSON
    private val jsonPlaceholderRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient) // dùng chung OkHttp + log
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val postApi: PostApi = jsonPlaceholderRetrofit.create(PostApi::class.java)
}