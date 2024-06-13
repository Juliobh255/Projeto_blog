package com.example.blogapp.converter

import com.example.blogapp.converter.presentation.DetailsPresentation
import com.example.blogapp.model.ModelPost

class DetailsShowPresentationConverter {
    fun createBuilderDetails(modelPost: ModelPost): DetailsPresentation {
        return DetailsPresentation(
            modelPost.title,
            modelPost.date,
            modelPost.description,
            modelPost.id
        )
    }
}