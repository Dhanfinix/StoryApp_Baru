package com.dhandev.storyapp.api

import com.dhandev.storyapp.model.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("name") name : String,
        @Field("email") email : String,
        @Field("password") password : String,
    ) : Call<register>

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email : String,
        @Field("password") password : String
    ) : Call<login>

    @GET("stories")
    suspend fun getStories(
        @Header("Authorization") token : String,
        @Query("page") page : Int,
        @Query("size") size : Int
    ) : GetAllStory

    @Multipart
    @POST("stories")
    fun uploadImage(
        @Header("Authorization") Authorization : String,
        @Part file: MultipartBody.Part,
        @Part("description") description: RequestBody,
    ): Call<FileUploadResponse>
}