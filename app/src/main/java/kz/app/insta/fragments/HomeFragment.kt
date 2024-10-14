package kz.app.insta.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kz.app.insta.R
import kz.app.insta.adapters.PostAdapter
import kz.app.insta.models.Post
import kotlin.random.Random

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
        adapter = PostAdapter(getMockPosts(), view.context)
        recyclerView.adapter = adapter
        return view
    }

    private fun getMockPosts(): List<Post> {
        return listOf(
            Post(
                id = 1,
                R.drawable.image1,
                "Maksat Madenietov",
                "Lorem ipsum dolor sit amet. Et nisi debitis aut dolores totam aut repellendu",
                Random.nextInt(0, 100),
            ),
            Post(
                id = 2,
                R.drawable.image1,
                "Maksat Madenietov",
                "Lorem ipsum dolor sit amet. Et nisi debitis aut dolores totam aut repellendu",
                Random.nextInt(0, 1000)
            ),
            Post(
                id = 3,
                R.drawable.image1,
                "Maksat Madenietov",
                "Lorem ipsum dolor sit amet. Et nisi debitis aut dolores totam aut repellendu",
                Random.nextInt(0, 10000)
            ),
        )
    }
}