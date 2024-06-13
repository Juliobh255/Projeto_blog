package com.example.blogapp.viewmodel

import com.example.blogapp.converter.presentation.ListPresentation
import com.example.blogapp.datasource.ResponsePost
import com.example.blogapp.model.ModelPost
import com.example.blogapp.repository.RepositoryBlog
import retrofit2.Call

class ViewModelRepository {

    fun postNewBlog(modelPost: ModelPost): retrofit2.Call<ResponsePost> {
       return RepositoryBlog().post(modelPost)
    }

    fun getListBlog(): Call<List<ListPresentation>> {
        return RepositoryBlog().getPos()
    }

    fun removePost(id:ModelPost): retrofit2.Call<ResponsePost?>?{
      return RepositoryBlog().removePost(id)
    }
}