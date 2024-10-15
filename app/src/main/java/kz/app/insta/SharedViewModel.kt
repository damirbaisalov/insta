package kz.app.insta

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kz.app.insta.models.MY_USER_ID
import kz.app.insta.models.Notification
import kz.app.insta.models.Post
import kz.app.insta.models.ProfilePost
import kz.app.insta.models.User
import kotlin.random.Random

class SharedViewModel : ViewModel() {

    private val _posts = MutableLiveData<MutableList<Post>>()
    val posts: MutableLiveData<MutableList<Post>> = _posts

    private val _users = MutableLiveData<MutableList<User>>()
    val users: MutableLiveData<MutableList<User>> = _users

    private val _myPosts = MutableLiveData<MutableList<ProfilePost>>()
    val myPosts: MutableLiveData<MutableList<ProfilePost>> = _myPosts

    private val _notifications = MutableLiveData<MutableList<Notification>>()
    val notifications: MutableLiveData<MutableList<Notification>> = _notifications

    init {
        _myPosts.value = getMockProfilePosts()
        _users.value = initUsers()
        _posts.value = getMockPosts()
        _notifications.value = getMockNotifications()
    }

    fun addPost(post: Post) {
        _posts.value?.add(post)
        _posts.value = _posts.value

        _myPosts.value?.add(ProfilePost(post.postImage))
        _myPosts.value = _myPosts.value

        _notifications.value?.add(
            Notification(
                id = generateNewId(),
                message = "You added new post",
                timestamp = generateTimestamp()
            )
        )
        _notifications.value = _notifications.value
    }

    fun addLikedNotification(message: String) {
        _notifications.value?.add(Notification(id = generateNewId(), message, generateTimestamp()))
        _notifications.value = _notifications.value
    }

    private fun initMe(): User {
        return User(
            id = MY_USER_ID,
            nickname = "Maksat Madeniyetov",
            avatar = R.drawable.user_1,
            posts = _myPosts.value?.toList() ?: emptyList(),
            followers = 400,
            following = 1000,
            bio = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
            isMe = true
        )
    }

    private fun initUsers(): MutableList<User> {
        val users = mutableListOf<User>()
        for (index in 0..5) {
            if (index == 1) {
                users.add(initMe())
            } else {
                users.add(
                    User(
                        id = "ID_${index}_another",
                        nickname = "AnotherUser_$index",
                        avatar = R.drawable.user_1
                    )
                )
            }
        }
        return users
    }

    private fun getMockPosts(): MutableList<Post> {
        return mutableListOf(
            Post(
                id = 0,
                userId = MY_USER_ID,
                nickname = "Maksat Madeniyetov",
                caption = "Lorem ipsum dolor sit amet. Et nisi debitis aut dolores totam aut repellendu",
                likes = Random.nextInt(0, 100),
            ),
            Post(
                id = 1,
                userId = "ID_1",
                nickname = "AnotherProfileNickname_ID_1",
                caption = "Lorem ipsum dolor sit amet. Et nisi debitis aut dolores totam aut repellendu",
                likes = Random.nextInt(0, 1000)
            ),
            Post(
                id = 2,
                userId = MY_USER_ID,
                nickname = "Maksat Madeniyetov",
                caption = "Lorem ipsum dolor sit amet. Et nisi debitis aut dolores totam aut repellendu",
                likes = Random.nextInt(0, 100),
            ),
            Post(
                id = 3,
                userId = "ID_2",
                nickname = "AnotherProfileNickname_ID_2",
                caption = "Lorem ipsum dolor sit amet. Et nisi debitis aut dolores totam aut repellendu",
                likes = Random.nextInt(0, 10000)
            ),
            Post(
                id = 4,
                userId = MY_USER_ID,
                nickname = "Maksat Madeniyetov",
                caption = "Lorem ipsum dolor sit amet. Et nisi debitis aut dolores totam aut repellendu",
                likes = Random.nextInt(0, 100),
            ),
            Post(
                id = 5,
                userId = MY_USER_ID,
                nickname = "Maksat Madeniyetov",
                caption = "Lorem ipsum dolor sit amet. Et nisi debitis aut dolores totam aut repellendu",
                likes = Random.nextInt(0, 100),
            ),
            Post(
                id = 6,
                userId = MY_USER_ID,
                nickname = "Maksat Madeniyetov",
                caption = "Lorem ipsum dolor sit amet. Et nisi debitis aut dolores totam aut repellendu",
                likes = Random.nextInt(0, 100),
            ),
            Post(
                id = 7,
                userId = MY_USER_ID,
                nickname = "Maksat Madeniyetov",
                caption = "Lorem ipsum dolor sit amet. Et nisi debitis aut dolores totam aut repellendu",
                likes = Random.nextInt(0, 100),
            )
        )
    }

    private fun getMockProfilePosts(): MutableList<ProfilePost> {
        return mutableListOf(
            ProfilePost(),
            ProfilePost(),
            ProfilePost(),
            ProfilePost(),
            ProfilePost(),
            ProfilePost()
        )
    }

    private fun getMockNotifications(): MutableList<Notification> {
        return mutableListOf(
            Notification(1, "User1 liked your post", "10 minutes ago"),
            Notification(2, "User2 commented on your photo", "1 hour ago"),
            Notification(3, "User3 followed you", "2 hours ago")
        )
    }

    private fun generateNewId(): Int {
        return (0..100).random()
    }

    private fun generateTimestamp(): String {
        return "${(0..10).random()} times ago"
    }
}