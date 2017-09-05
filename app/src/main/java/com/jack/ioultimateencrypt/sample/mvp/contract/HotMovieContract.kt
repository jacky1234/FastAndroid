package com.jack.ioultimateencrypt.sample.mvp.contract

import com.jack.ioultimateencrypt.sample.mvp.BasePresenter
import com.jack.ioultimateencrypt.sample.mvp.BaseView
import com.jack.ioultimateencrypt.sample.mvp.model.bean.HotMovieBean

/**
 * 2017/8/25.
 * github:[https://github.com/jacky1234]
 * @author  jackyang
 *
 */
interface HotMovieContract {
    interface Present : BasePresenter {
        fun queryHotMovies(cityId: Int)
    }

    interface View : BaseView<Present> {
        fun onResponseHotMovies(bean: HotMovieBean)

        fun onError(t: Throwable)
    }
}