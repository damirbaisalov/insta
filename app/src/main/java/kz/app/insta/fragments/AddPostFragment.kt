package kz.app.insta.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import kz.app.insta.R
import kz.app.insta.SharedViewModel
import kz.app.insta.models.Post

class AddPostFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var selectedImageView: ImageView
    private var selectedImageUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_post, container, false)

        val captionEditText: EditText = view.findViewById(R.id.caption_edit_text)
        val selectImageButton: Button = view.findViewById(R.id.select_image_button)
        val submitButton: Button = view.findViewById(R.id.submit_button)
        selectedImageView = view.findViewById(R.id.selected_image_view)

        selectImageButton.setOnClickListener {
            openImagePicker()
        }

        submitButton.setOnClickListener {
            if (selectedImageUri != null) {
                val user = sharedViewModel.users.value?.firstOrNull { it.isMe }
                    ?: return@setOnClickListener
                val newPost = Post(
                    id = generateNewId(),
                    postImage = selectedImageUri.toString(),
                    caption = captionEditText.text.toString(),
                    nickname = user.nickname
                )
                sharedViewModel.addPost(newPost)
                findNavController().navigate(R.id.homeFragment)
            } else {
                Toast.makeText(context, "Please upload photo", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    private fun openImagePicker() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, IMAGE_PICK_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_PICK_REQUEST_CODE && resultCode == AppCompatActivity.RESULT_OK) {
            selectedImageUri = data?.data
            selectedImageView.visibility = View.VISIBLE

            selectedImageUri?.let { uri ->
                loadImageFromUri(uri)
            }
        }
    }

    private fun loadImageFromUri(uri: Uri) {
        Glide.with(this)
            .load(uri)
            .into(selectedImageView)
    }

    private fun generateNewId(): Int {
        return (1..1000).random()
    }

    companion object {
        private const val IMAGE_PICK_REQUEST_CODE = 100
    }
}