package ru.netology.nmedia.viewholder

import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.adapter.OnLikeListener
import ru.netology.nmedia.adapter.OnRemoveListener
import ru.netology.nmedia.adapter.OnShareListener
import ru.netology.nmedia.databinding.PostCardBinding
import ru.netology.nmedia.counter.Counter
import ru.netology.nmedia.dto.Post


class PostViewHolder(
    private val binding: PostCardBinding,
    private val onLikeListener: OnLikeListener,
    private val onShareListener: OnShareListener,
    private val onRemoveListener: OnRemoveListener

) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.apply {
            author.text = post.author
            time.text = post.published
            content.text = post.content
            likeCount.text = Counter.count(post.likes)
            shareCount.text = Counter.count(post.share)
            like.setImageResource(
                if (post.likedByMe) R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_border_24
            )
            like.setOnClickListener {
                onLikeListener(post)
            }
            shares.setOnClickListener {
                onShareListener(post)
            }
            spot.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.popup_menu)
                    setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.remove -> {
                                onRemoveListener(post)
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
}
