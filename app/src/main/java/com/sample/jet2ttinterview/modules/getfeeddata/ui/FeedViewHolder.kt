package com.sample.jet2ttinterview.modules.getfeeddata.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.blog_feed.view.*

class FeedViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val inflatedView = view

    fun setUserProfilePic(url: String?) {
        Picasso.get().load(url).into(inflatedView.image_view_blog_feed_user_pic)
    }

    fun setUserName(firstName: String?, lastName: String?) {
        inflatedView.text_view_blog_feed_user_name.text = "$firstName $lastName"
    }

    fun setUserDesignation(designation: String?) {
        inflatedView.text_view_blog_feed_user_designation.text = designation
    }

    fun setFeedLastPositionTime(time: String?) {
        inflatedView.text_view_blog_feed_updated_time.text = time
    }

    fun setMediaPic(url: String?) {
        Picasso.get().load(url).into(inflatedView.image_view_blog_feed_media_pic)
    }

    fun setMediaDescription(description: String?) {
        inflatedView.text_view_blog_feed_description.text = description
    }

    fun setComments(comments: String?) {
        inflatedView.text_view_blog_feed_comments.text = comments
    }

    fun setMediaLikes(likes: String?) {
        inflatedView.text_view_blog_feed_likes.text = likes
    }

    fun setArticleTitle(title: String?) {
        inflatedView.text_view_blog_feed_title.text = title
    }

    fun setArticleUrl(url: String?) {
        inflatedView.text_view_blog_feed_url.text = url
    }
}