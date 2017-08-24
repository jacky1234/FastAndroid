package com.jack.ioultimateencrypt.sample.mvp.present

import android.content.Context
import com.jack.ioultimateencrypt.sample.applySchedulers
import com.jack.ioultimateencrypt.sample.mvp.contract.CatalogCostract
import com.jack.ioultimateencrypt.sample.mvp.model.CatalogModule
import com.jack.ioultimateencrypt.sample.mvp.model.bean.CatalogBean
import io.reactivex.Observable

/**
 * 2017/8/21.
 * github:[https://github.com/jacky1234]
 * @author  jackyang
 *
 */
class CatalogPresent(context: Context, view: CatalogCostract.View) : CatalogCostract.Present {

    var context: Context? = null
    var view: CatalogCostract.View? = null
    var module: CatalogModule? = null


    init {
        this.context = context
        this.view = view
        module = CatalogModule()
    }

    override fun start() {
        getCatalogs()
    }

    override fun getCatalogs() {
        var obs: Observable<List<CatalogBean>>? = context?.let {
            module?.getCatalogs(it, "com.jack.ioultimateencrypt.sample.ui")
        }

        obs?.applySchedulers()?.subscribe({ lists: List<CatalogBean> ->
            view?.setCatalogs(lists)
        }, { e: Throwable -> e.printStackTrace()})
    }
}



