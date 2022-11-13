package ru.netology.nmedia.dto

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.databinding.PostCardBinding

typealias OnLikeListener = (post: Post) -> Unit
typealias OnShareListener = (post: Post) -> Unit


class PostAdapter (private val onLikeListener: OnLikeListener, private val onShareListener: OnShareListener) : RecyclerView.Adapter<PostViewHolder>(){
    var list = emptyList<Post>()
    set(value){
        field = value
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
       val binding = PostCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  PostViewHolder(binding,onLikeListener, onShareListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = list[position]
        holder.bind(post)
    }

    override fun getItemCount(): Int = list.size

}
