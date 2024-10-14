package kz.app.insta.adapters

import android.content.Context
import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kz.app.insta.R
import kz.app.insta.models.Post

class PostAdapter(
    private val posts: List<Post>,
    private val context: Context,
    private val onUserClick: (Boolean) -> Unit
) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.post_image)
        val username: TextView = itemView.findViewById(R.id.username)
        val usernameAndCaption: TextView = itemView.findViewById(R.id.usernameAndCaption)
        val likes: TextView = itemView.findViewById(R.id.likes)
        val likeButton: ImageView = itemView.findViewById(R.id.heart_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]
        val postIndex = position
        holder.image.setImageResource(post.postImage)
        holder.username.text = post.user.nickname
        holder.username.setOnClickListener { onUserClick.invoke(post.user.isMe) }
        val usernameAndCaption = "${post.user.nickname} ${post.caption}"
        val spannableString = SpannableString(usernameAndCaption)
        spannableString.setSpan(
            StyleSpan(Typeface.BOLD),
            0,
            post.user.nickname.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        holder.usernameAndCaption.text = spannableString
        holder.likes.text = "${post.likes} likes"

        val likeImageStatus = if (post.isLiked) R.drawable.ic_liked else R.drawable.ic_heart
        holder.likeButton.setImageResource(likeImageStatus)
        holder.likeButton.setOnClickListener {
            post.isLiked = !post.isLiked
            post.likes += if (post.isLiked) 1 else -1
            notifyItemChanged(position)
        }
        val gestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
            override fun onDoubleTap(e: MotionEvent): Boolean {
                post.isLiked = !post.isLiked
                post.likes += if (post.isLiked) 1 else -1
                notifyItemChanged(postIndex)
                return true
            }
        })

        holder.image.setOnTouchListener { _, event ->
            gestureDetector.onTouchEvent(event)
            true
        }
    }

    override fun getItemCount(): Int = posts.size
}