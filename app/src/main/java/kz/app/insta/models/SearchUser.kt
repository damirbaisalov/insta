package kz.app.insta.models

import kz.app.insta.R

data class SearchUser(
    val id: String,
    val avatar: Int,
    val nickname: String
) {
    companion object {
        fun getMockData(): List<SearchUser> {
            return listOf(
                SearchUser("ID_0", R.drawable.user_1, "Maksat Madeniyetov"),
                SearchUser("ID_1", R.drawable.user_1, "nick2"),
                SearchUser("ID_3", R.drawable.user_1,"nick3"),
                SearchUser("ID_4", R.drawable.user_1, "nick4"),
                SearchUser("ID_5", R.drawable.user_1, "nick5"),
                SearchUser("ID_6", R.drawable.user_1, "nick6"),
                SearchUser("ID_7", R.drawable.user_1, "nick7"),
                SearchUser("ID_8", R.drawable.user_1, "nick8"),
                SearchUser("ID_9", R.drawable.user_1, "nick9"),
                SearchUser("ID_10", R.drawable.user_1, "nick10"),
            )
        }
    }
}
