package kz.app.insta.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kz.app.insta.R
import kz.app.insta.models.Notification

class NotificationAdapter(private var notifications: List<Notification>) :
    RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

    class NotificationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val messageTextView: TextView = itemView.findViewById(R.id.notification_message)
        val timestampTextView: TextView = itemView.findViewById(R.id.notification_timestamp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_notification, parent, false)
        return NotificationViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val notification = notifications[position]
        holder.messageTextView.text = notification.message
        holder.timestampTextView.text = notification.timestamp
    }

    override fun getItemCount(): Int {
        return notifications.size
    }

    fun updateNotifications(newNotifications: List<Notification>) {
        notifications = newNotifications
        notifyDataSetChanged()
    }
}