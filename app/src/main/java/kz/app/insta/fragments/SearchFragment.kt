package kz.app.insta.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kz.app.insta.R
import kz.app.insta.SharedViewModel
import kz.app.insta.adapters.SearchProfileAdapter

class SearchFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SearchProfileAdapter
    private lateinit var searchView: SearchView
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        searchView = view.findViewById(R.id.search_view)
        recyclerView = view.findViewById(R.id.search_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)

        val users = sharedViewModel.users.value ?: emptyList()
        Log.d("TAG", "onCreateView: $users")
        adapter = SearchProfileAdapter(users, ::onUserClick)
        recyclerView.adapter = adapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter(newText ?: "")
                return true
            }
        })

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
}