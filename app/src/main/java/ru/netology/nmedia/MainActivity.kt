package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable.Factory
import androidx.activity.viewModels
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Counter
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.dto.PostViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.lifecycle.MutableLiveData

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        viewModel.data.observe(this)
        { post ->
            with(binding) {
                autor.text = post.author
                time.text = post.published
                content.text = post.content
                likeCount.text = Counter.count(post.likes)
                shareCount.text = Counter.count(post.share)
                like.setImageResource(
                    if (post.likedByMe) R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_border_24
                )
            }
        }
        binding.like.setOnClickListener {
            viewModel.like()
        }
        binding.shares.setOnClickListener {
            viewModel.share()
        }


    }
}
