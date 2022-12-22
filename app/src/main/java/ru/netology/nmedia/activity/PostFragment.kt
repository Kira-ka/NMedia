package ru.netology.nmedia.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.FragmentEditPostBinding
import ru.netology.nmedia.databinding.FragmentPostBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.hidekeyboard.AndroidUtils
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
                    viewModel.data.observe(viewLifecycleOwner){ posts ->
                        viewModel.edited.value = posts.find {   it.id == post.id}
                    }
                }
                shares.setOnClickListener{
                    viewModel.shareById(post.id)
                }
                menu.setOnClickListener {
                    PopupMenu(it.context, it).apply {
                        inflate(R.menu.popup_menu)
                        setOnMenuItemClickListener { item ->
                            when (item.itemId) {
                                R.id.remove -> {
                                    viewModel.removeById(post.id)
                                    findNavController().navigateUp()
                                    true
                                }
                                R.id.edit -> {
                                    findNavController().navigate(R.id.action_postFragment_to_editPostFragment)
                                    true
                                }
                                else ->
                                    false
                            }
                        }
                    }.show()
                }
            }
        }





        return binding.root
    }
}
