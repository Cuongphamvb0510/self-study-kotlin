package com.example.studyandroid.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studyandroid.adapter.PostAdapter
import com.example.studyandroid.databinding.ActivityPostsBinding
import com.example.studyandroid.model.post.Post
import com.example.studyandroid.network.RetrofitClient
import com.example.studyandroid.view.MessageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostsActivity : BaseActivity() {
    private lateinit var binding: ActivityPostsBinding
    private lateinit var postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        applySystemBarInsets(binding.root)

        setupRecyclerView()
        loadPosts()
    }

    private fun setupRecyclerView() {
        postAdapter = PostAdapter { post ->
            val postId = post.id ?: return@PostAdapter
            val intent = Intent(this, PostDetailActivity::class.java)
            intent.putExtra(PostDetailActivity.EXTRA_POST_ID, postId)
            startActivity(intent)
        }
        binding.rvPosts.layoutManager = LinearLayoutManager(this)
        binding.rvPosts.adapter = postAdapter
    }

    private fun loadPosts() {
        binding.progressBar.visibility = View.VISIBLE

        RetrofitClient.postApi.getPosts().enqueue(object : Callback<List<Post>> {
            override fun onResponse(
                call: Call<List<Post>>,
                response: Response<List<Post>>
            ) {
                binding.progressBar.visibility = View.GONE
                if (response.isSuccessful) {
                    postAdapter.submitList(response.body().orEmpty())
                } else {
                    MessageView.showError(this@PostsActivity, "Lỗi ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                binding.progressBar.visibility = View.GONE
                MessageView.showError(this@PostsActivity, "Lỗi mạng")
            }
        })
    }
}