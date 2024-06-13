package com.example.blogapp.viewmodel

import com.example.blogapp.converter.MyListPostPresentationConverter
import com.example.blogapp.converter.presentation.ListPresentation
import retrofit2.Call

class ViewModelMyList {
    fun presentation(call : Call<List<ListPresentation>>): Call<List<ListPresentation>>{
        return MyListPostPresentationConverter().createBuilderMyList(call)
    }
}