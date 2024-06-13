package com.example.blogapp.converter.presentation

import com.example.blogapp.model.ModelPost
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ListPresentation(
    @SerializedName(value = "title")
    val title: String,
    @SerializedName(value = "date")
    val date: String,
    @SerializedName(value = "description")
    val description: String,
    @SerializedName(value = "id")
    val id: String,
): Serializable