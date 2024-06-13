package com.example.blogapp.datasource

import java.io.Serializable

class ResponsePost(
    val error: String,
    val success: Boolean,
) : Serializable