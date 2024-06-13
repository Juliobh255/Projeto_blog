package com.example.blogapp.converter

import com.example.blogapp.datasource.ResponsePost
import retrofit2.Call

class RemovePresentationConverter {
    fun createBuilderReturnRemove(call: Call<ResponsePost?>?): retrofit2.Call<ResponsePost?>? {
        return call
    }
}