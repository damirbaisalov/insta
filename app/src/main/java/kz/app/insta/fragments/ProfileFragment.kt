package kz.app.insta.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kz.app.insta.R
import kz.app.insta.adapters.ProfilePostAdapter
import kz.app.insta.models.User

class ProfileFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var postsAdapter: ProfilePostAdapter
    private lateinit var userAvatar: ImageView
    private lateinit var userNickname: TextView
    private lateinit var userBio: TextView
    private lateinit var userPostsCount: TextView
    private lateinit var userFollowers: TextView
    private lateinit var userFollowing: TextView

    private val user: User by lazy { User.initUser() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        initViews(view)
        bindViewsWithData()

        recyclerView = view.findViewById(R.id.profile_recycler_view_posts)
        val gridLayoutManager = GridLayoutManager(context, 3)
        recyclerView.layoutManager = gridLayoutManager

        postsAdapter = ProfilePostAdapter(emptyList(), requireContext())
        recyclerView.adapter = postsAdapter

        loadProfilePosts()

        return view
    }

    private fun initViews(view: View) {
        userAvatar = view.findViewById(R.id.profile_user_avatar)
        userNickname = view.findViewById(R.id.profile_username)
        userBio = view.findViewById(R.id.profile_bio)
        userPostsCount = view.findViewById(R.id.profile_posts_count)
        userFollowers = view.findViewById(R.id.profile_followers_count)
        userFollowing = view.findViewById(R.id.profile_following_count)
    }

    private fun bindViewsWithData() {
        userAvatar.setImageResource(user.avatar)
        userNickname.text = user.nickname
        userBio.text = user.bio
        userPostsCount.text = "${user.posts.size}\nPosts"
        userFollowers.text = "${user.followers}\nFollowers"
        userFollowing.text = "${user.following}\nFollowing"
    }

    private fun loadProfilePosts() {
        postsAdapter = ProfilePostAdapter(user.posts, requireContext())
        recyclerView.adapter = postsAdapter
    }
}