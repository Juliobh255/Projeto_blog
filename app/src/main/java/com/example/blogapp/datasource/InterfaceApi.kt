package com.example.blogapp.datasource

import com.example.blogapp.converter.presentation.ListPresentation
import com.example.blogapp.model.ModelPost
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface InterfaceApi {
    @GET("modelposts")
    fun getList(): Call<List<ListPresentation>>

    @POST("modelposts")
    fun posList(@Body modelPost: ModelPost): Call<ResponsePost>

    @DELETE("modelposts/{id}")
    fun detelete(@Path("id") id: Int): Call<ResponsePost?>?
}