package ru.netology.nmedia.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ru.netology.nmedia.databinding.ActivityEditPostBinding
import ru.netology.nmedia.databinding.ActivityNewPostBinding
import ru.netology.nmedia.dto.Post

class EditPostActivity : AppCompatActivity() {

    lateinit var post: Post

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityEditPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val str = intent?.getStringExtra("data")
        post = str?.let { Json.decodeFromString<Post>(it) }!!
        binding.editPost.setText(post.content)


        binding.editPost.requestFocus();
        binding.okEditPost.setOnClickListener {
            val intent = Intent()
            if (binding.editPost.text.isBlank()) {
                setResult(RESULT_CANCELED, intent)
            } else {
                val content = binding.editPost.text.toString()
               val p = post.copy(content = content)
                intent.putExtra("data", Json.encodeToString(p))
                setResult(RESULT_OK, intent)
            }
            finish()
        }
    }
}
