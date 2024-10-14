package kz.app.insta.models

import kz.app.insta.R
import kotlin.random.Random

data class Post(
    val id: Int,
    val postImage: Int,
    val user: User,
    val caption: String,
    var likes: Int,
    var isLiked: Boolean = false
) {
    companion object {
        fun getMockPosts(): List<Post> {
            return listOf(
                Post(
                    id = 0,
                    postImage = R.drawable.image1,
                    user = User.initUser(),
                    caption = "Lorem ipsum dolor sit amet. Et nisi debitis aut dolores totam aut repellendu",
                    likes = Random.nextInt(0, 100),
                ),
                Post(
                    id = 1,
                    postImage = R.drawable.image1,
                    user = User(id = "ID_1", nickname = "AnotherProfileNickname_ID_1"),
                    caption = "Lorem ipsum dolor sit amet. Et nisi debitis aut dolores totam aut repellendu",
                    likes = Random.nextInt(0, 1000)
                ),
                Post(
                    id = 2,
                    postImage = R.drawable.image1,
                    user = User.initUser(),
                    caption = "Lorem ipsum dolor sit amet. Et nisi debitis aut dolores totam aut repellendu",
                    likes = Random.nextInt(0, 100),
                ),
                Post(
                    id = 3,
                    postImage = R.drawable.image1,
                    user = User(id = "ID_2", nickname = "AnotherProfileNickname_ID_2"),
                    caption = "Lorem ipsum dolor sit amet. Et nisi debitis aut dolores totam aut repellendu",
                    likes = Random.nextInt(0, 10000)
                ),
                Post(
                    id = 4,
                    postImage = R.drawable.image1,
                    user = User.initUser(),
                    caption = "Lorem ipsum dolor sit amet. Et nisi debitis aut dolores totam aut repellendu",
                    likes = Random.nextInt(0, 100),
                ),
                Post(
                    id = 5,
                    postImage = R.drawable.image1,
                    user = User(id = "ID_3", nickname = "AnotherProfileNickname_ID_3"),
                    caption = "Lorem ipsum dolor sit amet. Et nisi debitis aut dolores totam aut repellendu",
                    likes = Random.nextInt(0, 100)
                )
            )
        }
    }
}
