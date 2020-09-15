package com.sample.jet2ttinterview.api.apiservice

import com.sample.jet2ttinterview.api.apiresponse.BlogListAPIResponse
import io.reactivex.Observable
import retrofit2.http.*

interface BlogAPIService {
    @Headers("content-type: application/json")
    @GET("blogs")
    fun getAllArticleBlogs(@Query("page") classId: String, @Query("limit") limit: String): Observable<ArrayList<BlogListAPIResponse>>
}