package com.jack.ioultimateencrypt.sample.ui.fragment.movie

import android.support.v4.app.Fragment
import com.jack.ioultimateencrypt.sample.R
import com.jack.ioultimateencrypt.sample.base.BaseFragment
import com.jack.ioultimateencrypt.sample.ui.adapter.ViewPagerFragmentAdapter
import kotlinx.android.synthetic.main.fragment_movie.*

/**
 * 2017/8/21.
 * github:[https://github.com/jacky1234]
 * @author  jackyang
 *
 */
class MovieFragment : BaseFragment() {
    var mTabs = listOf("正在热播", "即将上映").toMutableList()
    lateinit var mFragments: ArrayList<Fragment>


    override fun initView() {
        mFragments = ArrayList()
        val upcomingFragment = UpcomingFragment()
        val hotMovieFragment = HotMovieFragment()
        mFragments.add(hotMovieFragment)
        mFragments.add(upcomingFragment)
        vp_content.adapter = ViewPagerFragmentAdapter(fragmentManager, mFragments, mTabs)
        tabs.setupWithViewPager(vp_content)
    }

    override fun getLayoutResources(): Int {
        return R.layout.fragment_movie
    }

}