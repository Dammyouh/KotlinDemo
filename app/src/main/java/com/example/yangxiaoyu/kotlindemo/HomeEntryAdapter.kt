package com.example.yangxiaoyu.kotlindemo

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yangxiaoyu.kotlindemo.entity.HomeEntryEntity

/**
 * Created by yangxy on 2018/6/7.
 */
class HomeEntryAdapter(context: Context,entryList : List<HomeEntryEntity>) : RecyclerView.Adapter<HomeEntryAdapter.MyViewHolder>() {

    private var mContext : Context ? = null
    private var mEntryList:List<HomeEntryEntity> ? = null

    init {
        mContext = context
        mEntryList = entryList

    }
    override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {

    }

    override fun getItemCount(): Int {
        return mEntryList!!.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(mContext).inflate(R.layout.item_home_entry,null)
        return MyViewHolder(view,mContext)
    }


    class MyViewHolder(itemView: View?,context : Context?) : RecyclerView.ViewHolder(itemView) {
        private var mContext: Context ? = null
        init {
            mContext = context
        }
    }
}