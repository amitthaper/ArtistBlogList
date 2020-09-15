package com.sample.jet2ttinterview.view

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction

open class BaseActivity: AppCompatActivity(), IBaseView {

    override fun replaceFragment(sourceLayoutId: Int, isAddToBackStack: Boolean, tagName: String?, openFragment: androidx.fragment.app.Fragment) {
        val fragmentManager: androidx.fragment.app.FragmentManager = supportFragmentManager
        var fragmentTransaction: FragmentTransaction? = null
        if (isAddToBackStack) {
            tagName?.let {
                fragmentTransaction = fragmentManager.beginTransaction().replace(sourceLayoutId, openFragment, tagName)
            } ?: kotlin.run {
                fragmentTransaction = fragmentManager.beginTransaction().replace(sourceLayoutId, openFragment)
            }
        } else {
            fragmentTransaction = fragmentManager.beginTransaction().replace(sourceLayoutId, openFragment)
        }
        fragmentTransaction?.commit()
    }
}