package com.jack.ioultimateencrypt.sample.mvp.contract

import com.jack.ioultimateencrypt.sample.mvp.BasePresenter
import com.jack.ioultimateencrypt.sample.mvp.BaseView
import com.jack.ioultimateencrypt.sample.mvp.model.bean.MovieDetailBean

/**
 * 2017/9/14.
 * github:[https://github.com/jacky1234]
 * @author  jackyang
 *
 */
interface MovieDetailContract {
    interface Present : BasePresenter {
        fun queryMovieDetail(locationId: String, movieId: String)
    }

    interface View : BaseView<Present> {
        fun onMovieDetailResponse(movieDetailBean: MovieDetailBean)
        fun onError(t: Throwable)
    }
}