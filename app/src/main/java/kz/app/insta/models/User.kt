package kz.app.insta.models

import kz.app.insta.R

const val MY_USER_ID = "ID_0"

data class User(
    val id: String,
    val nickname: String,
    val avatar: Int = R.drawable.user_1,
    val followers: Int = 0,
    val following: Int = 0,
    val bio: String = "",
    val posts: List<ProfilePost> = emptyList(),
    val isMe: Boolean = false
)
