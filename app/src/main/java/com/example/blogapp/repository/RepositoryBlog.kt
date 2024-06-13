package com.example.blogapp.repository

import com.example.blogapp.converter.presentation.ListPresentation
import com.example.blogapp.datasource.RequestApiMock
import com.example.blogapp.datasource.ResponsePost
import com.example.blogapp.model.ModelPost
import com.example.blogapp.viewmodel.ViewModelMyList
import com.example.blogapp.viewmodel.ViewModelNewPost
import com.example.blogapp.viewmodel.ViewModelRemove

class RepositoryBlog {
    private val requestApiMock = RequestApiMock()

    fun post(modelPost: ModelPost): retrofit2.Call<ResponsePost> {
        val call = requestApiMock.posList(modelPost)
        return ViewModelNewPost().presentation(call)
    }

    fun getPos(): retrofit2.Call<List<ListPresentation>> {
        val call = requestApiMock.getList()
        return ViewModelMyList().presentation(call)
    }

    fun removePost(modelPost: ModelPost): retrofit2.Call<ResponsePost?>? {
        val call = requestApiMock.detelete(modelPost.id.toInt())
        return ViewModelRemove().presentation(call)
    }

}