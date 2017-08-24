package com.jack.ioultimateencrypt.sample.mvp.present

import android.content.Context
import com.jack.ioultimateencrypt.sample.Constant
import com.jack.ioultimateencrypt.sample.applySchedulers
import com.jack.ioultimateencrypt.sample.mvp.contract.MainCostract
import com.jack.ioultimateencrypt.sample.mvp.model.bean.Location
import com.jack.ioultimateencrypt.sample.network.RetrofitClient
import com.jack.ioultimateencrypt.sample.network.api.MTimeApi
import com.jackyang.android.support.injection.Injections
import com.jackyang.android.support.repository.KeyValueStore
import io.reactivex.Observable

/**
 * 2017/8/23.
 * github:[https://github.com/jacky1234]
 * @author  jackyang
 *
 */
class MainPresent(context: Context, view: MainCostract.View) : MainCostract.Present {

    override fun queryCities() {
        var value = mkeyValueStore!!.get(Constant.STORE_KEY.CITIES, List::class.java).value
        if (value == null) {
            val mTimeApi = RetrofitClient.getInstance(mContext!!, MTimeApi.BASE_URL).create(MTimeApi::class.java)
            mTimeApi!!.queryCities()
                    .flatMap { (p) -> Observable.fromArray(p) }
                    .applySchedulers()
                    .subscribe({ lists: List<Location.City> ->
                        mView!!.onResponseCities(lists)
                    }, { e: Throwable -> println(e) })
        } else {
            mView!!.onResponseCities(value as List<Location.City>)
        }
    }

    var mContext: Context? = null
    var mView: MainCostract.View? = null
    var mkeyValueStore: KeyValueStore? = null

    init {
        this.mContext = context
        this.mView = view
        this.mkeyValueStore = Injections.getBean(KeyValueStore::class.java)
    }
}
