package com.sample.jet2ttinterview.modules.getfeeddata.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sample.jet2ttinterview.R
import com.sample.jet2ttinterview.api.apiresponse.BlogListAPIResponse
import com.sample.jet2ttinterview.utility.ArticleBlogUtility

class FeedListAdapter(blogListApiResponse: List<BlogListAPIResponse?>) : androidx.recyclerview.widget.RecyclerView.Adapter<androidx.recyclerview.widget.RecyclerView.ViewHolder>() {
    private val artistFeedList = blogListApiResponse
    var appContext: Context? = null

    override fun onBindViewHolder(subscriptionPlanViewHolder: androidx.recyclerview.widget.RecyclerView.ViewHolder, position: Int) {

        when(subscriptionPlanViewHolder) {
            is FeedViewHolder -> {
                val subscriptionPlan = artistFeedList[position]
                subscriptionPlanViewHolder.setFeedLastPositionTime(ArticleBlogUtility.getTimeDifference(subscriptionPlan?.createdAt!!, appContext!!))
                subscriptionPlanViewHolder.setMediaDescription(subscriptionPlan.content)
                subscriptionPlanViewHolder.setMediaLikes(ArticleBlogUtility.getFormattedLikes(subscriptionPlan.likes, appContext!!))
                subscriptionPlanViewHolder.setMediaPic(subscriptionPlan.media[0].image)
                subscriptionPlanViewHolder.setUserDesignation(subscriptionPlan.user[0].designation)
                subscriptionPlanViewHolder.setUserName(subscriptionPlan.user[0].name, subscriptionPlan.user[0].lastname)
                subscriptionPlanViewHolder.setUserProfilePic(subscriptionPlan.user[0].avatar)
                subscriptionPlanViewHolder.setComments(ArticleBlogUtility.getFormattedComments(subscriptionPlan.comments, appContext!!))
                subscriptionPlanViewHolder.setArticleTitle(subscriptionPlan.media[0].title)
                subscriptionPlanViewHolder.setArticleUrl(subscriptionPlan.media[0].url)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): androidx.recyclerview.widget.RecyclerView.ViewHolder {
        appContext = viewGroup.context
        if(viewType == 0) {
            val inflatedView =
                LayoutInflater.from(appContext).inflate(R.layout.blog_feed, viewGroup, false)
            return FeedViewHolder(inflatedView)
        } else {
            val inflatedView =
                LayoutInflater.from(appContext).inflate(R.layout.feed_loading, viewGroup, false)
            return LoadingViewHolder(inflatedView)
        }
        // TODO Can add more views if required
    }

    override fun getItemCount(): Int {
        return artistFeedList.size
    }

    override fun getItemViewType(position: Int): Int {
        artistFeedList[position]?.let {
            return 0
        }?: kotlin.run {
            return 1
        }
    }
}