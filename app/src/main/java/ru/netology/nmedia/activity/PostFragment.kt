package ru.netology.nmedia.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.FragmentEditPostBinding
import ru.netology.nmedia.databinding.FragmentPostBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.viewmodel.PostViewModel


class PostFragment : Fragment() {


    val viewModel: PostViewModel by viewModels(ownerProducer = ::requireParentFragment)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPostBinding.inflate(
            inflater,
            container,
            false
        )

        viewModel.edited.observe(viewLifecycleOwner) { post ->
            with(binding) {
                contentFragPost.text = post.content
                author.text = post.author
                time.text = post.published
                shares.text = post.share.toString()
                shares.text = post.share.toString()
                like.setText(post.likes.toString())

                like.setOnClickListener {
                    viewModel.likeById(post.id)
                }
                shares.setOnClickListener{
                    viewModel.shareById(post.id)
                }
            }
        }





        return binding.root
    }
}
