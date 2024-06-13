package com.example.blogapp.datasource

import com.example.blogapp.converter.presentation.ListPresentation
import com.example.blogapp.model.ModelPost
import com.example.blogapp.util.Constants.Companion.URLREQUEST
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RequestApiMock : InterfaceApi {

    override fun getList(): Call<List<ListPresentation>> {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(URLREQUEST)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        val restGetPosts = retrofit.create(InterfaceApi::class.java)

        return restGetPosts.getList()
    }

    override fun posList(modelPost: ModelPost): Call<ResponsePost> {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(URLREQUEST)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        val restPosts = retrofit.create(InterfaceApi::class.java)
        return restPosts.posList(modelPost)
    }

    override fun detelete(id: Int): Call<ResponsePost?>? {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(URLREQUEST)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        val restPosts = retrofit.create(InterfaceApi::class.java)
        restPosts.detelete(id)

        return restPosts.detelete(id)
    }


}


