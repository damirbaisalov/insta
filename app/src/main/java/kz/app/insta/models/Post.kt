package kz.app.insta.models

data class Post(
    val id: Int,
    val postImage: Int,
    val username: String,
    val caption: String,
    var likes: Int,
    var isLiked: Boolean = false
)
