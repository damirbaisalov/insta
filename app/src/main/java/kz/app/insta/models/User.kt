package kz.app.insta.models

import kz.app.insta.R

data class User(
    val id: String,
    val nickname: String,
    val avatar: Int = R.drawable.user_1,
    val followers: Int = 0,
    val following: Int = 0,
    val bio: String = "",
    val posts: List<ProfilePost> = emptyList(),
    val isMe: Boolean = false
) {
    companion object {
        fun initUser(): User {
            return User(
                id = "ID_0",
                nickname = "Maksat Madeniyetov",
                avatar = R.drawable.user_1,
                posts = ProfilePost.getMockProfilePosts(),
                followers = 400,
                following = 1000,
                bio = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                isMe = true
            )
        }
    }
}
