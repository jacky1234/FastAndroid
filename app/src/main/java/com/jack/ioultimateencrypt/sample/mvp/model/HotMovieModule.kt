package com.jack.ioultimateencrypt.sample.mvp.model

import android.content.Context
import com.jack.ioultimateencrypt.sample.mvp.model.bean.HotMovieBean
import com.jack.ioultimateencrypt.sample.network.RetrofitClient
import com.jack.ioultimateencrypt.sample.network.api.MTimeApi
import io.reactivex.Observable

/**
 * 2017/8/25.
 * github:[https://github.com/jacky1234]
 * @author  jackyang
 *
 */
class HotMovieModule {
    fun queryUpComingMovies(mContext: Context, cityId: Int): Observable<HotMovieBean> {
        val mTimeApi = RetrofitClient.getInstance(mContext!!, MTimeApi.BASE_URL).create(MTimeApi::class.java)
        return mTimeApi!!.queryHotMovies(cityId)
    }
}