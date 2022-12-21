package ru.netology.nmedia.activity

import android.content.Intent

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.launch
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController

import ru.netology.nmedia.R
import ru.netology.nmedia.adapter.OnInteractionListener
import ru.netology.nmedia.adapter.PostAdapter

import ru.netology.nmedia.databinding.FragmentFeedBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.viewmodel.PostViewModel


class FeedActivity : Fragment() {

    val viewModel: PostViewModel by viewModels(ownerProducer = ::requireParentFragment)
    val binding by lazy { FragmentFeedBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFeedBinding.inflate(
            inflater,
            container,
            false
        )

        val adapter = PostAdapter(object : OnInteractionListener{

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
                findNavController().navigate(R.id.action_feedActivity_to_editPostFragment)
            }

            override fun onPlay(post: Post) {
                val video = Uri.parse(post.video)
                val playIntent = Intent(Intent.ACTION_VIEW, video)
                startActivity(playIntent)
            }
            override fun onSpecificPost (post: Post){
                viewModel.edit(post)
                findNavController().navigate(R.id.action_feedActivity_to_postFragment)
            }

        })

        binding.list.adapter = adapter
        viewModel.data.observe(viewLifecycleOwner) { posts ->
            adapter.submitList(posts)

        }



        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_feedActivity_to_newPostFragment)
        }
        return binding.root
    }

}

