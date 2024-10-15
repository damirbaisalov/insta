package kz.app.insta.models

data class Post(
    val id: Int = 0,
    val postImage: String? = null,
    val userId: String = "",
    val nickname: String = "",
    val caption: String = "",
    var likes: Int = 0,
    var isLiked: Boolean = false
)
