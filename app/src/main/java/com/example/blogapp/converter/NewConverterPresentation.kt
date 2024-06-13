package com.example.blogapp.converter

import com.example.blogapp.datasource.ResponsePost
import retrofit2.Call

class NewConverterPresentation {
    fun createBuilderNewPost(callPost: Call<ResponsePost>):Call<ResponsePost> {
        return callPost
    }
}