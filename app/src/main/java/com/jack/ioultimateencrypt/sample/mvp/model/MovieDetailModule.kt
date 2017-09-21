package com.jack.ioultimateencrypt.sample.mvp.model

import com.jack.ioultimateencrypt.sample.MyApplication.gContext
import com.jack.ioultimateencrypt.sample.mvp.model.bean.MovieDetailBean
import com.jack.ioultimateencrypt.sample.network.RetrofitClient
import com.jack.ioultimateencrypt.sample.network.api.MTimeApi
import io.reactivex.Observable

/**
 * 2017/9/17.
 * github:[https://github.com/jacky1234]
 * @author  jackyang
 *
 */
class MovieDetailModule {
    fun queryMovieDetailInfo(locationId: String, movieId: String): Observable<MovieDetailBean> {
        val mTimeApi = RetrofitClient.getInstance(gContext!!, MTimeApi.BASE_URL).create(MTimeApi::class.java)
        return mTimeApi!!.queryMovieDetail(locationId, movieId)
    }
}