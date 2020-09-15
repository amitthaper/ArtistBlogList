package com.sample.jet2ttinterview.modules.getfeeddata.ui

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sample.jet2ttinterview.R
import com.sample.jet2ttinterview.api.apiresponse.BlogListAPIResponse
import com.sample.jet2ttinterview.modules.getfeeddata.viewmodel.ArtistBlogViewModel
import kotlinx.android.synthetic.main.feeds_list_screen.view.*


class FeedListFragment : Fragment() {

    var feedList = ArrayList<BlogListAPIResponse?>()
    var feedListAdapter: FeedListAdapter? = null
    var feedListObserver: ArtistBlogViewModel? = null
    var isLoading = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.feeds_list_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSpecializationObserver()
        view.recycler_view_feeds_list_screen_feed.apply {
            setHasFixedSize(true)
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity?.applicationContext)
            initRecyclerAdapter()
            feedListObserver?.fetchArtistBlogList("1", "10")
        }

        view.recycler_view_feeds_list_screen_feed.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(@NonNull recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(@NonNull recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager =
                    recyclerView.layoutManager as LinearLayoutManager?
                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == feedList.size - 1) {
                        //bottom of list!
                        loadMore()
                        isLoading = true
                    }
                }
            }
        })
    }

    private fun initRecyclerAdapter() {
        feedList = ArrayList()
        feedListAdapter = FeedListAdapter(feedList)
        view?.recycler_view_feeds_list_screen_feed?.adapter = feedListAdapter
    }

    fun handleAPIResponse() {
        feedListObserver?.artistBlogsMutableList?.observe(viewLifecycleOwner, Observer {
            feedList.addAll(it)
            feedListAdapter?.notifyDataSetChanged()
        })
        feedListObserver?.errorMutableThrowable?.observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity, "Unable to fetch artist blog", Toast.LENGTH_SHORT).show()
        })
    }

    private fun initSpecializationObserver() {
        feedListObserver = ViewModelProvider(activity!!).get(ArtistBlogViewModel::class.java)
        handleAPIResponse()
    }

    private fun loadMore() {
        feedList.add(null)
        feedListAdapter?.notifyItemInserted(feedList.size - 1)
        val handler = Handler()
        handler.postDelayed(Runnable {
            feedList.removeAt(feedList.size - 1)
            val scrollPosition: Int = feedList.size
            feedListAdapter?.notifyItemRemoved(scrollPosition)
            var currentSize = (scrollPosition / 10) + 1
            val nextLimit = 10
            feedListObserver?.fetchArtistBlogList(currentSize.toString(), nextLimit.toString())
            isLoading = false
        }, 2000)
    }
}