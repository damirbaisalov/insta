package kz.app.insta.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kz.app.insta.R
import kz.app.insta.SharedViewModel
import kz.app.insta.adapters.NotificationAdapter

class NotificationsFragment : Fragment() {

    private lateinit var notificationsRecyclerView: RecyclerView
    private lateinit var notificationAdapter: NotificationAdapter
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_notifications, container, false)

        notificationsRecyclerView = view.findViewById(R.id.notifications_recycler_view)
        notificationsRecyclerView.layoutManager = LinearLayoutManager(context)
        notificationAdapter = NotificationAdapter(sharedViewModel.notifications.value?.toList() ?: emptyList())
        notificationsRecyclerView.adapter = notificationAdapter

        sharedViewModel.notifications.observe(viewLifecycleOwner) { notifications ->
            notificationAdapter.updateNotifications(notifications.reversed())
        }

        return view
    }
}