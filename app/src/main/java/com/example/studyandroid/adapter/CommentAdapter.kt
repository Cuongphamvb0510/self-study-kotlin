package com.example.studyandroid.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studyandroid.databinding.ItemCommentBinding
import com.example.studyandroid.model.post.Comment

class CommentAdapter(
    private var comments: List<Comment> = emptyList()
) : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    class CommentViewHolder(val binding: ItemCommentBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val binding = ItemCommentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val comment = comments[position]
        holder.binding.txtName.text = comment.name
        holder.binding.txtEmail.text = comment.email
        holder.binding.txtBody.text = comment.body
    }

    override fun getItemCount(): Int = comments.size

    fun submitList(newComments: List<Comment>) {
        comments = newComments
        notifyDataSetChanged()
    }
}