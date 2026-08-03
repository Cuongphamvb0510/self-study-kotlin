package com.example.studyandroid.model.post

data class Post(
    val userId: Int,
    val id: Int? = null,
    val title: String,
    val body: String
)
