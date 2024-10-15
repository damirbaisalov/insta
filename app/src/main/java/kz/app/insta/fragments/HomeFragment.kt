package kz.app.insta.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kz.app.insta.R
import kz.app.insta.SharedViewModel
import kz.app.insta.adapters.PostAdapter

class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PostAdapter
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = PostAdapter(
            sharedViewModel.posts.value?.reversed()?.toList() ?: emptyList(),
            ::onUserClick,
            ::onLikeClick
        )
        recyclerView.adapter = adapter

        sharedViewModel.posts.observe(viewLifecycleOwner) { posts ->
            adapter.updatePosts(posts.reversed())
        }
        return view
    }

    private fun onUserClick(userId: String) {
        val isMe = sharedViewModel.users.value?.firstOrNull { userId == it.id }?.isMe ?: false
        if (isMe) {
            findNavController().navigate(R.id.profileFragment)
        } else {
            Toast.makeText(requireActivity(), "AnotherProfileClicked $userId", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun onLikeClick() {
        sharedViewModel.addLikedNotification("You liked post")
    }
}