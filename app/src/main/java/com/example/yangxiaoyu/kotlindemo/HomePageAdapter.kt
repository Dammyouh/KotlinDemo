package com.example.yangxiaoyu.kotlindemo

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by yangxy on 2018/5/21.
 */
class HomePageAdapter(fm: FragmentManager?,fragmentList : List<Fragment>) : FragmentPagerAdapter(fm) {

    private var fragmentList : List<Fragment> = fragmentList

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }
}