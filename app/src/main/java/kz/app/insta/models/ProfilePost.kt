package kz.app.insta.models

import kz.app.insta.R

data class ProfilePost(val image: Int) {
    companion object {
        fun getMockProfilePosts(): List<ProfilePost> {
            return listOf(
                ProfilePost(R.drawable.image1),
                ProfilePost(R.drawable.image1),
                ProfilePost(R.drawable.image1),
                ProfilePost(R.drawable.image1),
                ProfilePost(R.drawable.image1),
                ProfilePost(R.drawable.image1),
            )
        }
    }
}
