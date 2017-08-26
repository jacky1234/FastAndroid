package com.jack.ioultimateencrypt.sample.mvp.contract

import com.jack.ioultimateencrypt.sample.mvp.BasePresenter
import com.jack.ioultimateencrypt.sample.mvp.BaseView
import com.jack.ioultimateencrypt.sample.mvp.model.bean.UpcomingMovieBean
import io.reactivex.Observable

/**
 * 2017/8/25.
 * github:[https://github.com/jacky1234]
 * @author  jackyang
 *
 */
interface UpComingContract {
    interface Present : BasePresenter {
        fun queryUpComingMovies(cityId: String)
    }

    interface View : BaseView<Present> {
        fun onResponseMovies(bean: UpcomingMovieBean?)
    }
}