package kz.app.insta.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kz.app.insta.R
import kz.app.insta.adapters.PostAdapter
import kz.app.insta.models.Post

class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PostAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = PostAdapter(Post.getMockPosts(), view.context, ::onUserClick)
        recyclerView.adapter = adapter
        return view
    }

    private fun onUserClick(isMe: Boolean) {
        if (isMe) {
            findNavController().navigate(R.id.profileFragment)
        } else {
            Toast.makeText(requireActivity(), "AnotherProfileClicked", Toast.LENGTH_SHORT).show()
        }
    }
}