package kz.app.insta.models

import kz.app.insta.R

data class User(
    val id: String,
    val nickname: String,
    val avatar: Int,
    val followers: Int,
    val following: Int,
    val bio: String,
    val posts: List<Post>,
    val isMe: Boolean = false
) {
    companion object {
        fun initUser(): User {
            return User(
                id = "0",
                nickname = "Maksat Madeniyetov",
                avatar = R.drawable.user_1,
                posts = Post.getMockPosts(),
                followers = 400,
                following = 1000,
                bio = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                isMe = true
            )
        }
    }
}
