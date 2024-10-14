package kz.app.insta.models

import kz.app.insta.R
import kotlin.random.Random

data class Post(
    val id: Int,
    val postImage: Int,
    val username: String,
    val caption: String,
    var likes: Int,
    var isLiked: Boolean = false
) {
    companion object {
        fun getMockPosts(): List<Post> {
            return listOf(
                Post(
                    id = 1,
                    R.drawable.image1,
                    "Maksat Madeniyetov",
                    "Lorem ipsum dolor sit amet. Et nisi debitis aut dolores totam aut repellendu",
                    Random.nextInt(0, 100),
                ),
                Post(
                    id = 2,
                    R.drawable.image1,
                    "Maksat Madeniyetov",
                    "Lorem ipsum dolor sit amet. Et nisi debitis aut dolores totam aut repellendu",
                    Random.nextInt(0, 1000)
                ),
                Post(
                    id = 3,
                    R.drawable.image1,
                    "Maksat Madeniyetov",
                    "Lorem ipsum dolor sit amet. Et nisi debitis aut dolores totam aut repellendu",
                    Random.nextInt(0, 10000)
                ),
                Post(
                    id = 4,
                    R.drawable.image1,
                    "Maksat Madeniyetov",
                    "Lorem ipsum dolor sit amet. Et nisi debitis aut dolores totam aut repellendu",
                    Random.nextInt(0, 100),
                ),
                Post(
                    id = 5,
                    R.drawable.image1,
                    "Maksat Madeniyetov",
                    "Lorem ipsum dolor sit amet. Et nisi debitis aut dolores totam aut repellendu",
                    Random.nextInt(0, 1000)
                ),
                Post(
                    id = 6,
                    R.drawable.image1,
                    "Maksat Madeniyetov",
                    "Lorem ipsum dolor sit amet. Et nisi debitis aut dolores totam aut repellendu",
                    Random.nextInt(0, 10000)
                ),
            )
        }
    }
}
