package com.sample.jet2ttinterview.api.apiresponse

import com.google.gson.annotations.SerializedName
import com.sample.jet2ttinterview.api.models.Media
import com.sample.jet2ttinterview.api.models.User

class BlogListAPIResponse(
    @SerializedName("id") val _id: String,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("content") val content: String,
    @SerializedName("comments") val comments: String,
    @SerializedName("likes") val likes: String,
    @SerializedName("media") val media: ArrayList<Media>,
    @SerializedName("user") val user: ArrayList<User>)