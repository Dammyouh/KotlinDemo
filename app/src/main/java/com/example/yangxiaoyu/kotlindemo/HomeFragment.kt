package com.example.yangxiaoyu.kotlindemo

import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Yxy on 2018/3/31.
 */

class HomeFragment : Fragment() {
    private var mContext:Context ?= null
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mContext = context
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       var view: View = View.inflate(mContext!!,R.layout.activity_main, null)
        return  view
    }


}
