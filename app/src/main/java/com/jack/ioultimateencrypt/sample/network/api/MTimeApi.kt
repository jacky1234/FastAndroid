package com.jack.ioultimateencrypt.sample.network.api

import com.jack.ioultimateencrypt.sample.mvp.model.bean.Location
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * 2017/8/24.
 * github:[https://github.com/jacky1234]
 * @author  jackyang
 *
 */
interface MTimeApi {
    companion object {
        val BASE_URL: String
            get() = "https://api-m.mtime.cn/"
    }

    @GET("Showtime/HotCitiesByCinema.api")
    fun queryCities(): Observable<Location>
}