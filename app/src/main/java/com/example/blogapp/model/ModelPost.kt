package com.example.blogapp.model

import com.google.gson.annotations.SerializedName

data class ModelPost(
    @SerializedName(value = "title")
    val title: String,
    @SerializedName(value = "date")
    val date: String,
    @SerializedName(value = "description")
    val description: String,
    @SerializedName(value = "id")
    val id: String,
)