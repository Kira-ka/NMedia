package ru.netology.nmedia.activity

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

import ru.netology.nmedia.R
import ru.netology.nmedia.adapter.OnInteractionListener
import ru.netology.nmedia.adapter.PostAdapter
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.hidekeyboard.AndroidUtils
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {

    val viewModel: PostViewModel by viewModels()
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val interactionListener = object : OnInteractionListener {

        override fun onLike(post: Post) {
            viewModel.likeById(post.id)
        }

        override fun onShare(post: Post) {
            viewModel.shareById(post.id)
        }

        override fun onRemove(post: Post) {
            viewModel.removeById(post.id)

        }

        override fun onEdit(post: Post) {
            viewModel.edit(post)
            binding.group.visibility = VISIBLE
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)


        val adapter = PostAdapter(interactionListener)
        binding.list.adapter = adapter

        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }

        viewModel.edited.observe(this) { post ->
            if (post.id == 0L) {
                return@observe
            }
            with(binding.editContent) {
                requestFocus()
                setText(post.content)
            }
            with(binding.content) {
                setText(post.content)
            }
        }
        binding.saveButton.setOnClickListener {
            with(binding.editContent) {
                if (text.isNullOrBlank()) {
                    Toast.makeText(
                        this@MainActivity,
                        getString(R.string.not_empty),
                        Toast.LENGTH_SHORT
                    )
                        .show()
                    return@setOnClickListener
                }

                viewModel.changeContent(text.toString())
                viewModel.save()

                setText("")
                clearFocus()
                AndroidUtils.hideKeyboard(this)
            }

        }
        binding.cancelButton.setOnClickListener {
            viewModel.cancel()
            binding.group.visibility = GONE
        }

        binding.list.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
            with(binding.editContent) {
                setText("")
                clearFocus()
                AndroidUtils.hideKeyboard(this)
            }
        }

    }


}
