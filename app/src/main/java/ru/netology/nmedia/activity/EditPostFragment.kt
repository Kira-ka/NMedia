package ru.netology.nmedia.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.FragmentEditPostBinding
import ru.netology.nmedia.databinding.FragmentNewPostBinding


import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.hidekeyboard.AndroidUtils
import ru.netology.nmedia.viewmodel.PostViewModel

class EditPostFragment : Fragment() {

    companion object {
        private const val TEXT_KEY = "TEXT_KEY"
        var Bundle.texArg: String? by StringArg
    }

    val viewModel: PostViewModel by viewModels(ownerProducer = ::requireParentFragment)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentEditPostBinding.inflate(
            inflater,
            container,
            false
        )


        viewModel.edited.observe(viewLifecycleOwner) { post ->
            binding.editPost.setText(post.content)
        }

        binding.okEditPost.setOnClickListener {
            viewModel.changeContent(binding.editPost.text.toString())
            viewModel.save()
            AndroidUtils.hideKeyboard(requireView())
            findNavController().navigate(R.id.action_editPostFragment_to_feedActivity)
        }

        return binding.root
    }


}
