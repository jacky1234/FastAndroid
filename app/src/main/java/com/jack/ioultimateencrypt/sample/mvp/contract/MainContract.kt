package com.jack.ioultimateencrypt.sample.mvp.contract

import com.jack.ioultimateencrypt.sample.mvp.BasePresenter
import com.jack.ioultimateencrypt.sample.mvp.BaseView
import com.jack.ioultimateencrypt.sample.mvp.model.bean.Location

/**
 * 2017/8/23.
 * github:[https://github.com/jacky1234]
 * @author  jackyang
 *
 */
interface MainContract {
    interface Present : BasePresenter {
        fun queryCities()
    }

    interface View : BaseView<Present> {
        @Throws(Exception::class)
        fun onResponseCities(lists: List<Location.City>)
    }


}