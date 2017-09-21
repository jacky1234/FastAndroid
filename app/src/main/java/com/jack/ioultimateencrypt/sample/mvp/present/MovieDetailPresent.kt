package com.jack.ioultimateencrypt.sample.mvp.present

import android.content.Context
import com.jack.ioultimateencrypt.sample.applySchedulers
import com.jack.ioultimateencrypt.sample.mvp.contract.MovieDetailConstract
import com.jack.ioultimateencrypt.sample.mvp.model.MovieDetailModule

/**
 * 2017/9/17.
 * github:[https://github.com/jacky1234]
 * @author  jackyang
 *
 */
class MovieDetailPresent(context: Context, view: MovieDetailConstract.View) : MovieDetailConstract.Present {
    override fun queryMovieDetail(locationId: String, movieId: String) {
        mModule.queryMovieDetailInfo(locationId, movieId)
                .applySchedulers()
                .subscribe({ item ->
                    mView.onMovieDetailResponse(item)
                }, { t ->
                    mView.onError(t)
                })
    }

    val mContext: Context = context
    val mView: MovieDetailConstract.View = view
    val mModule: MovieDetailModule by lazy { MovieDetailModule() }


}