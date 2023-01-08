package ru.netology.nmedia.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.netology.nmedia.dto.Post

@Entity
data class PostEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val author: String,
    val published: String,
    val content: String,
    var likedByMe: Boolean,
    val likes: Long = 0,
    val share: Long,
    val video: String? = null,
) {

    fun toDto() = Post(id, author, published, content, likedByMe, likes, share, video)

    companion object {
        fun fromDto(dto: Post) =
            PostEntity(
                dto.id,
                dto.author,
                dto.published,
                dto.content,
                dto.likedByMe,
                dto.likes,
                dto.share,
                dto.video,
            )
    }
}
