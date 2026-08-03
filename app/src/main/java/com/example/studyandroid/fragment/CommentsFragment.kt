package com.example.studyandroid.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studyandroid.adapter.CommentAdapter
import com.example.studyandroid.databinding.FragmentCommentsBinding
import com.example.studyandroid.model.post.Comment
import com.example.studyandroid.network.RetrofitClient
import com.example.studyandroid.view.MessageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentsFragment : Fragment() {

    companion object {
        private const val ARG_POST_ID = "postId"

        fun newInstance(postId: Int): CommentsFragment {
            return CommentsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_POST_ID, postId)
                }
            }
        }
    }

    private var _binding: FragmentCommentsBinding? = null
    private val binding get() = _binding!!

    private lateinit var commentAdapter: CommentAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCommentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val postId = arguments?.getInt(ARG_POST_ID, -1) ?: -1
        if (postId == -1) return

        commentAdapter = CommentAdapter()
        binding.rvComments.layoutManager = LinearLayoutManager(requireContext())
        binding.rvComments.adapter = commentAdapter

        loadComments(postId)
    }

    private fun loadComments(postId: Int) {
        binding.progressBar.visibility = View.VISIBLE

        RetrofitClient.postApi.getComments(postId)
            .enqueue(object : Callback<List<Comment>> {
                override fun onResponse(
                    call: Call<List<Comment>>,
                    response: Response<List<Comment>>
                ) {
                    if (!isAdded) return
                    binding.progressBar.visibility = View.GONE

                    if (response.isSuccessful) {
                        commentAdapter.submitList(response.body().orEmpty())
                    } else {
                        MessageView.showError(
                            requireContext(),
                            "Lỗi comments ${response.code()}"
                        )
                    }
                }

                override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                    if (!isAdded) return
                    binding.progressBar.visibility = View.GONE
                    MessageView.showError(requireContext(), "Lỗi mạng comments")
                }
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}