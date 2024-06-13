package com.example.blogapp.viewmodel

import com.example.blogapp.converter.NewConverterPresentation
import com.example.blogapp.datasource.ResponsePost
import retrofit2.Call

class ViewModelNewPost {
    fun presentation(response: Call<ResponsePost>): Call<ResponsePost>{
        return NewConverterPresentation().createBuilderNewPost(response)
    }
}