package com.sample.jet2ttinterview.api

import com.sample.jet2ttinterview.api.apirequest.GetArticleBlogsRequest
import com.sample.jet2ttinterview.api.apiresponse.BlogListAPIResponse
import io.reactivex.Maybe

interface APIService {
    interface ArticleBlog {
        fun fetchArticleBlog(initialOffset: String, limit: String): Maybe<ArrayList<BlogListAPIResponse>>?
    }
}