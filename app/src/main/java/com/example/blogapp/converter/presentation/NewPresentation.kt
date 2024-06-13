package com.example.blogapp.converter.presentation

import com.example.blogapp.model.ModelPost
import com.google.gson.annotations.SerializedName

data class NewPresentation(
    @SerializedName(value = "modelPost")
    val modelPost: ModelPost?
)