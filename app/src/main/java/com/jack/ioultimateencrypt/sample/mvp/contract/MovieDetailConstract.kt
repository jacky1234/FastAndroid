package com.jack.ioultimateencrypt.sample.mvp.contract

import com.jack.ioultimateencrypt.sample.mvp.BasePresenter
import com.jack.ioultimateencrypt.sample.mvp.BaseView
import com.jack.ioultimateencrypt.sample.mvp.model.bean.CatalogBean

/**
 * 2017/9/14.
 * github:[https://github.com/jacky1234]
 * @author  jackyang
 *
 */
interface MovieDetailConstract {
    interface Present : BasePresenter {
        fun start()
    }

    interface View : BaseView<Present> {
        fun setCatalogs(lists: List<CatalogBean>)

    }
}