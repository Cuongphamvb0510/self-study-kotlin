package com.example.studyandroid.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studyandroid.databinding.ItemPostBinding
import com.example.studyandroid.model.post.Post

class PostAdapter(
    private var posts: List<Post> = emptyList(),
    private val onClick: (Post) -> Unit = {}
) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    class PostViewHolder(val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemPostBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]
        holder.binding.txtPostId.text = "#${post.id}"
        holder.binding.txtTitle.text = post.title
        holder.binding.txtBody.text = post.body

        holder.itemView.setOnClickListener { onClick(post) }
    }

    override fun getItemCount(): Int = posts.size

    fun submitList(newPosts: List<Post>) {
        posts = newPosts
        notifyDataSetChanged()
    }
}