package com.example.studyandroid.network

import com.example.studyandroid.model.post.Comment
import com.example.studyandroid.model.post.Post
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PostApi {
    //GET /posts
    @GET("posts")
    fun getPosts(): Call<List<Post>>

    //GET post/1
    @GET("posts/{id}")
    fun getPost(@Path("id") id: Int): Call<Post>


    // POST /posts
    @POST("posts")
    fun createPost(@Body post: Post): Call<Post>

    // PUT /posts/1  (thay cả object)
    @PUT("posts/{id}")
    fun updatePost(@Path("id") id: Int, @Body post: Post): Call<Post>

    // PATCH /posts/1  (sửa một phần — có thể dùng thay PUT)
    @PATCH("posts/{id}")
    fun patchPost(@Path("id") id: Int, @Body post: Post): Call<Post>

    // DELETE /posts/1
    @DELETE("posts/{id}")
    fun deletePost(@Path("id") id: Int): Call<Unit>

    @GET("posts/{id}/comments")
    fun getComments(@Path("id") postId: Int): Call<List<Comment>>
}