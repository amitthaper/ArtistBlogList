package com.sample.jet2ttinterview.modules.getfeeddata.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sample.jet2ttinterview.api.APIObserver
import com.sample.jet2ttinterview.api.apiresponse.BlogListAPIResponse
import com.sample.jet2ttinterview.api.requestmanager.BlogRequestManager

class ArtistBlogViewModel : ViewModel() {
    var artistBlogsMutableList = MutableLiveData<ArrayList<BlogListAPIResponse>>()
    var errorMutableThrowable = MutableLiveData<Throwable>()

    fun fetchArtistBlogList(startOffset: String, limit: String) {
        BlogRequestManager().fetchArticleBlog(startOffset, limit)
            ?.subscribe(APIObserver({ fetchedArtistBlogs ->
                setAPISuccessResponse(fetchedArtistBlogs)
            }, { throwableError -> setAPIErrorResponse(throwableError) }))
    }

    private fun setAPISuccessResponse(apiResponse: ArrayList<BlogListAPIResponse>?) {
        apiResponse?.let {
            artistBlogsMutableList.postValue(apiResponse)
        }
    }

    private fun setAPIErrorResponse(throwable: Throwable) {
        errorMutableThrowable?.postValue(throwable)
    }

    fun observerSubscriptionList(): MutableLiveData<ArrayList<BlogListAPIResponse>> {
        return artistBlogsMutableList
    }
}