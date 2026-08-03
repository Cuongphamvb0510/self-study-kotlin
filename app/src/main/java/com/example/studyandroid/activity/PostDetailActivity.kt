package com.example.studyandroid.activity

import android.os.Bundle
import android.view.View
import com.example.studyandroid.R
import com.example.studyandroid.databinding.ActivityPostDetailBinding
import com.example.studyandroid.fragment.CommentsFragment
import com.example.studyandroid.model.post.Post
import com.example.studyandroid.network.RetrofitClient
import com.example.studyandroid.view.MessageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostDetailActivity : BaseActivity() {

    companion object {
        const val EXTRA_POST_ID = "postId"
    }

    private lateinit var binding: ActivityPostDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        applySystemBarInsets(binding.root)

        val postId = intent.getIntExtra(EXTRA_POST_ID, -1)
        if (postId == -1) {
            MessageView.showError(this, "Không tìm thấy post")
            finish()
            return
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.commentContainer,
                    CommentsFragment.newInstance(postId)
                )
                .commit()
        }

        loadPost(postId)
    }

    private fun loadPost(postId: Int) {
        binding.progressBar.visibility = View.VISIBLE

        RetrofitClient.postApi.getPost(postId).enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                binding.progressBar.visibility = View.GONE

                if (!response.isSuccessful) {
                    MessageView.showError(
                        this@PostDetailActivity,
                        "Lỗi ${response.code()}"
                    )
                    return
                }

                val post = response.body()
                if (post == null) {
                    MessageView.showError(this@PostDetailActivity, "Dữ liệu trống")
                    return
                }

                binding.txtPostId.text = "#${post.id}"
                binding.txtTitle.text = post.title
                binding.txtUserId.text = "User ID: ${post.userId}"
                binding.txtBody.text = post.body
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                binding.progressBar.visibility = View.GONE
                MessageView.showError(this@PostDetailActivity, "Lỗi mạng")
            }
        })
    }
}
