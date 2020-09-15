package com.sample.jet2ttinterview.api.requestmanager

import android.util.Log
import com.sample.jet2ttinterview.api.APIService
import com.sample.jet2ttinterview.api.MaybeNetworkObserver
import com.sample.jet2ttinterview.api.RetrofitClient
import com.sample.jet2ttinterview.api.apiresponse.BlogListAPIResponse
import com.sample.jet2ttinterview.api.apiservice.BlogAPIService
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class BlogRequestManager: MaybeNetworkObserver<Any>(), APIService.ArticleBlog {

    fun handleFetchBlogResponse(response: ArrayList<BlogListAPIResponse>) {
        Log.d("Login Response", "Success")
    }

    fun handleFetchBlogError(throwable: Throwable) : Observable<ArrayList<BlogListAPIResponse>> {
        Log.d("API Blog Error Response", "Error")
        return Observable.error(throwable)
    }

    override fun fetchArticleBlog(initialOffset: String, limit: String): Maybe<ArrayList<BlogListAPIResponse>>? {
        val apiService = RetrofitClient.getRetrofitInstance()?.create(BlogAPIService::class.java)
        val loginObservable = apiService?.getAllArticleBlogs(initialOffset, limit)
        return loginObservable?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.onErrorResumeNext(this::handleFetchBlogError)
            ?.doOnNext(this::handleFetchBlogResponse)
            ?.singleElement()
    }
}