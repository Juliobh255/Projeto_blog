package com.example.blogapp.converter

import com.example.blogapp.converter.presentation.ListPresentation
import retrofit2.Call

class MyListPostPresentationConverter() {
    fun createBuilderMyList(call: Call<List<ListPresentation>> ) : Call<List<ListPresentation>> {
        return call
    }
}