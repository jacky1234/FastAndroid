package com.jack.ioultimateencrypt.sample.mvp.present

import android.content.Context
import com.jack.ioultimateencrypt.sample.applySchedulers
import com.jack.ioultimateencrypt.sample.mvp.contract.UpComingContract
import com.jack.ioultimateencrypt.sample.mvp.model.UpcomingMovieModule

/**
 * 2017/8/25.
 * github:[https://github.com/jacky1234]
 * @author  jackyang
 *
 */
class UpComingPresent(context: Context, view: UpComingContract.View) : UpComingContract.Present {
    override fun queryUpComingMovies(cityId: Int) {
        mModule.queryUpComingMovies(mContext, cityId)
                .applySchedulers()
                .subscribe { bean ->
                    mView.onResponseMovies(bean)
                }
    }

    var mContext: Context
    var mView: UpComingContract.View
    private val mModule by lazy { UpcomingMovieModule() }

    init {
        mContext = context
        mView = view
    }
}