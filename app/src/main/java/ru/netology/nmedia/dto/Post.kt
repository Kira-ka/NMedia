package ru.netology.nmedia.dto

import kotlinx.serialization.Serializable


@Serializable
data class Post(
    val id: Long,
    val author: String,
    val published: String,
    val content: String,
    var likedByMe: Boolean,
    val likes: Long = 0,
    val share: Long,
    val video: String? = null
)


