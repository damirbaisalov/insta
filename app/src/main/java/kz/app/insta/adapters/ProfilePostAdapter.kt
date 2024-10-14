package kz.app.insta.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kz.app.insta.R
import kz.app.insta.models.ProfilePost

class ProfilePostAdapter(
    private val posts: List<ProfilePost>,
    private val context: Context
) : RecyclerView.Adapter<ProfilePostAdapter.PostViewHolder>() {

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.profile_post_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_profile_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]
        holder.image.setImageResource(post.image)
        holder.image.setOnClickListener {
            Toast.makeText(context, "$position post is clicked", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int = posts.size
}