package com.jack.ioultimateencrypt.sample.mvp.present

import android.content.Context
import com.jack.ioultimateencrypt.sample.applySchedulers
import com.jack.ioultimateencrypt.sample.mvp.contract.HotMovieContract
import com.jack.ioultimateencrypt.sample.mvp.model.HotMovieModule

/**
 * 2017/8/25.
 * github:[https://github.com/jacky1234]
 * @author  jackyang
 *
 */
class HotMoviesPresent(context: Context, view: HotMovieContract.View) : HotMovieContract.Present {
    override fun queryHotMovies(cityId: Int) {
        mModule.queryUpComingMovies(mContext, cityId)
                .applySchedulers()
                .subscribe({ bean ->
                    mView.onResponseHotMovies(bean)
                }, { t ->
                    mView.onError(t)
                })
    }

    var mContext: Context = context
    var mView: HotMovieContract.View = view
    private val mModule by lazy { HotMovieModule() }

}