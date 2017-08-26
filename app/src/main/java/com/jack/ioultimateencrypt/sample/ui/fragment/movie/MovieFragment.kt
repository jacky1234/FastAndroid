package com.jack.ioultimateencrypt.sample.ui.fragment.movie

import android.support.v4.app.Fragment
import com.jack.ioultimateencrypt.sample.R
import com.jack.ioultimateencrypt.sample.base.BaseFragment

/**
 * 2017/8/21.
 * github:[https://github.com/jacky1234]
 * @author  jackyang
 *
 */
class MovieFragment :BaseFragment(){
    var mTabs = listOf<String>("正在热映", "即将上映").toMutableList()
    lateinit var mFragments: ArrayList<Fragment>


    override fun initView() {

    }

    override fun getLayoutResources(): Int {
        return R.layout.fragment_movie
    }

}