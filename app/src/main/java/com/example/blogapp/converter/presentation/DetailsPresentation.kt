package com.example.blogapp.converter.presentation

import com.google.gson.annotations.SerializedName

data class DetailsPresentation(
    @SerializedName(value = "title")
    val title: String,
    @SerializedName(value = "date")
    val date: String,
    @SerializedName(value = "description")
    val description: String,
    @SerializedName(value = "id")
    val id: String,
)