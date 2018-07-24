package com.example.yangxiaoyu.kotlindemo


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.example.yangxiaoyu.kotlindemo.net.HttpUtil
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import io.reactivex.subjects.PublishSubject
import com.example.yangxiaoyu.kotlindemo.net.ActivityLifeCycleEvent



class HomeActivity : AppCompatActivity(),BottomNavigationBar.OnTabSelectedListener, ViewPager.OnPageChangeListener{

    private var fragmentList : List<Fragment> ? = null
    val lifecycleSubject = PublishSubject.create<ActivityLifeCycleEvent>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        getData()
    }

    private fun getData() {
        lifecycleSubject.onNext(ActivityLifeCycleEvent.CREATE)
        HttpUtil.getInstance().doGet(lifecycleSubject,this)
    }


    private fun initView() {
        with(bottom_toolbar){
            //设置显示模式,必须在初始化完成之前设置
            bottom_toolbar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
            bottom_toolbar.setMode(BottomNavigationBar.MODE_FIXED)
            bottom_toolbar.addItem(BottomNavigationItem(R.drawable.homepage_controlbar_btn_home,""))
            bottom_toolbar.addItem(BottomNavigationItem(R.drawable.homepage_controlbar_btn_learn,""))
            bottom_toolbar.addItem(BottomNavigationItem(R.drawable.homepage_controlbar_btn_message,""))
            bottom_toolbar.addItem(BottomNavigationItem(R.drawable.homepage_controlbar_btn_community,""))
            bottom_toolbar.addItem(BottomNavigationItem(R.drawable.homepage_controlbar_btn_mine,""))
            bottom_toolbar.setFirstSelectedPosition(0)// 默认选中 的item
            bottom_toolbar.initialise()
        }
        fragmentList = listOf(
                HomeFragment(),
                StudyFragment(),
                MessageFragment(),
                PublicFragment(),
                MineFragment()

        )
        val fm : FragmentManager = supportFragmentManager
        viewpager.adapter = HomePageAdapter(fm, fragmentList!!)
        viewpager.addOnPageChangeListener(this)
        bottom_toolbar.setTabSelectedListener(this)
    }

    /**
     * BottomNavigationBar的选中监听
     */
    override fun onTabReselected(position: Int) {
    }

    override fun onTabUnselected(position: Int) {

    }

    override fun onTabSelected(position: Int) {
        viewpager.currentItem = position
    }

    /**
     * ViewPager的滑动监听
     */
    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        bottom_toolbar.selectTab(position)
    }
}
