package com.jack.ioultimateencrypt.sample.ui.fragment.movie

import com.jack.ioultimateencrypt.sample.R
import com.jack.ioultimateencrypt.sample.base.BaseFragment
import com.jack.ioultimateencrypt.sample.mvp.contract.UpComingContract
import com.jack.ioultimateencrypt.sample.mvp.model.bean.UpcomingMovieBean
import com.jack.ioultimateencrypt.sample.mvp.present.UpComingPresent

/**
 * 2017/8/25.
 * github:[https://github.com/jacky1234]
 * @author  jackyang
 *
 */
class UpcomingFragment : BaseFragment(), UpComingContract.View {
    override fun onResponseMovies(bean: UpcomingMovieBean?) {

    }

    override fun onLazyLoad() {
        mPresent.queryUpComingMovies(mCityId)
    }

    override fun getLayoutResources(): Int {
        return R.layout.fragment_upcoming
    }

    lateinit var mPresent: UpComingContract.Present
    lateinit var mCityId: String
    override fun initView() {
        mPresent = UpComingPresent(context, this)
//        mCityId =
    }

}