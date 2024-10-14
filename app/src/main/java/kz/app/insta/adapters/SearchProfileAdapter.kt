package kz.app.insta.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kz.app.insta.R
import kz.app.insta.models.SearchUser

class SearchProfileAdapter(
    private var searchUsers: List<SearchUser>,
    private val onUserClick: (String) -> Unit
) : RecyclerView.Adapter<SearchProfileAdapter.PostViewHolder>() {

    private var originalSearchUsers: List<SearchUser> = searchUsers

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.search_profile_avatar)
        val nickname: TextView = itemView.findViewById(R.id.search_profile_nickname)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search_profile, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val searchUser = searchUsers[position]
        holder.image.setImageResource(searchUser.avatar)
        holder.nickname.text = searchUser.nickname

        holder.itemView.setOnClickListener { onUserClick.invoke(searchUser.id) }
    }

    override fun getItemCount(): Int = searchUsers.size

    fun filter(query: String) {
        searchUsers = if (query.isEmpty()) {
            originalSearchUsers
        } else {
            searchUsers.filter { user ->
                user.nickname.contains(query, ignoreCase = true)
            }
        }
        notifyDataSetChanged()
    }
}