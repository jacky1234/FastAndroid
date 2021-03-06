package com.jack.ioultimateencrypt.sample.mvp.present

import android.content.Context
import com.jack.ioultimateencrypt.sample.applySchedulers
import com.jack.ioultimateencrypt.sample.mvp.contract.MainContract
import com.jack.ioultimateencrypt.sample.mvp.model.bean.Location
import com.jack.ioultimateencrypt.sample.network.RetrofitClient
import com.jack.ioultimateencrypt.sample.network.api.MTimeApi
import com.jack.ioultimateencrypt.sample.utils.SpUtils
import com.jackyang.android.support.injection.Injections
import com.jackyang.android.support.repository.KeyValueStore
import com.safframework.log.L
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * 2017/8/23.
 * github:[https://github.com/jacky1234]
 * @author  jackyang
 *
 */
class MainPresent(context: Context, view: MainContract.View) : MainContract.Present {

    override fun queryCities() {
        var value = SpUtils.getInstance(mContext).getCities()
        if (value == null || (value != null && value.isEmpty())) {
            val mTimeApi = RetrofitClient.getInstance(mContext!!, MTimeApi.BASE_URL).create(MTimeApi::class.java)
            mTimeApi!!.queryCities()
                    .flatMap { (p) ->
                        Observable.fromArray(p)
                    }
                    .applySchedulers()
                    .subscribe(object : Observer<List<Location.City>> {
                        override fun onComplete() {

                        }

                        override fun onSubscribe(d: Disposable) {
                        }

                        override fun onNext(lists: List<Location.City>) {
                            SpUtils.instance!!.saveCities(lists)
                            mView!!.onResponseCities(lists)
                        }

                        override fun onError(e: Throwable) {
                            SpUtils.instance!!.clear()
                        }
                    })
        } else {
            try {
                mView!!.onResponseCities(value)
            } catch (e: Exception) {
                SpUtils.instance!!.clear()
                L.e("exception->", e)
            }
        }
    }

    private var mContext: Context = context
    var mView: MainContract.View = view
    private var mkeyValueStore: KeyValueStore = Injections.getBean(KeyValueStore::class.java)

}
