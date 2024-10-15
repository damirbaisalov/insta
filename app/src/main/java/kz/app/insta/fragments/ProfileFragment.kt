package kz.app.insta.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kz.app.insta.R
import kz.app.insta.SharedViewModel
import kz.app.insta.adapters.ProfilePostAdapter
import kz.app.insta.models.ProfilePost
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

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        val user = sharedViewModel.users.value?.firstOrNull { it.isMe }
        initViews(view)
        user?.let { bindViewsWithData(it)}

        recyclerView = view.findViewById(R.id.profile_recycler_view_posts)
        val gridLayoutManager = GridLayoutManager(context, 3)
        recyclerView.layoutManager = gridLayoutManager
        postsAdapter = ProfilePostAdapter(emptyList())
        recyclerView.adapter = postsAdapter

        loadProfilePosts(user?.posts ?: emptyList())

        sharedViewModel.myPosts.observe(viewLifecycleOwner) { posts ->
            postsAdapter.updateProfilePosts(posts)
        }

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

    private fun bindViewsWithData(user: User) {
        userAvatar.setImageResource(user.avatar)
        userNickname.text = user.nickname
        userBio.text = user.bio
        userPostsCount.text = "${user.posts.size}\nPosts"
        userFollowers.text = "${user.followers}\nFollowers"
        userFollowing.text = "${user.following}\nFollowing"
    }

    private fun loadProfilePosts(posts: List<ProfilePost>) {
        postsAdapter = ProfilePostAdapter(posts)
        recyclerView.adapter = postsAdapter
    }
}