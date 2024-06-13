package com.example.blogapp.viewmodel

import com.example.blogapp.converter.DetailsShowPresentationConverter
import com.example.blogapp.converter.presentation.DetailsPresentation
import com.example.blogapp.model.ModelPost

class ViewModelDetail {
    fun presentation(modelPost: ModelPost): DetailsPresentation {
        return DetailsShowPresentationConverter().createBuilderDetails(modelPost)
    }
}