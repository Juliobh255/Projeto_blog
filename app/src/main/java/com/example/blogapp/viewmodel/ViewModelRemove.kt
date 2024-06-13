package com.example.blogapp.viewmodel

import com.example.blogapp.converter.RemovePresentationConverter
import com.example.blogapp.datasource.ResponsePost
import retrofit2.Call

class ViewModelRemove {
    fun presentation(response: Call<ResponsePost?>?): retrofit2.Call<ResponsePost?>? {
        return RemovePresentationConverter().createBuilderReturnRemove(call = response)
    }
}