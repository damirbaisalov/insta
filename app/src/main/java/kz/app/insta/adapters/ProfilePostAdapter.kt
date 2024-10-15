package kz.app.insta.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kz.app.insta.R
import kz.app.insta.models.ProfilePost

class ProfilePostAdapter(
    private var posts: List<ProfilePost>
) : RecyclerView.Adapter<ProfilePostAdapter.PostViewHolder>() {

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.profile_post_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_profile_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]
        Glide.with(holder.itemView.context)
            .load(post.imageUri)
            .placeholder(R.drawable.image1)
            .into(holder.image)
        holder.image.setOnClickListener {
            Toast.makeText(holder.itemView.context, "$position post is clicked", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun getItemCount(): Int = posts.size

    fun updateProfilePosts(newPosts: List<ProfilePost>) {
        posts = newPosts
        notifyDataSetChanged()
    }
}