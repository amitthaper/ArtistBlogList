package com.sample.jet2ttinterview.view

import android.os.Bundle
import com.sample.jet2ttinterview.R
import com.sample.jet2ttinterview.modules.getfeeddata.ui.FeedListFragment

class MainActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        val feedListFragment = FeedListFragment()
        replaceFragment(R.id.feed_list_frame_layout, true, FeedListFragment::class.java.simpleName, feedListFragment)
    }
}