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
import ru.netology.nmedia.databinding.PostCardBinding
import ru.netology.nmedia.dto.PostAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        val adapter = PostAdapter({
            viewModel.likeById(it.id)
        }, {
            viewModel.shareById(it.id)

        })
        binding.list.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }

    }


}
