package kz.app.insta.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kz.app.insta.R
import kz.app.insta.models.User

class SearchProfileAdapter(
    private var users: List<User>,
    private val onUserClick: (String) -> Unit
) : RecyclerView.Adapter<SearchProfileAdapter.PostViewHolder>() {

    private var originalUsers: List<User> = users

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.search_profile_avatar)
        val nickname: TextView = itemView.findViewById(R.id.search_profile_nickname)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search_profile, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val user = users[position]
        holder.image.setImageResource(user.avatar)
        holder.nickname.text = user.nickname

        holder.itemView.setOnClickListener { onUserClick.invoke(user.id) }
    }

    override fun getItemCount(): Int = users.size

    fun filter(query: String) {
        users = if (query.isEmpty()) {
            originalUsers
        } else {
            users.filter { user ->
                user.nickname.contains(query, ignoreCase = true)
            }
        }
        notifyDataSetChanged()
    }
}