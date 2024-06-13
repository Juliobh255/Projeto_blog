package com.example.blogapp.converter.presentation

import java.io.Serializable

data class RemovePresentation(
    val message: String,
    val success: Boolean
):Serializable