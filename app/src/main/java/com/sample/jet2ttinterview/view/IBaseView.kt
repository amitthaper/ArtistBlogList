package com.sample.jet2ttinterview.view

interface IBaseView {
    fun replaceFragment(sourceLayoutId: Int, isAddToBackStack: Boolean, tagName: String?, openFragment: androidx.fragment.app.Fragment)
}