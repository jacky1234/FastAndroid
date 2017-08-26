package com.jack.ioultimateencrypt.sample.network.api

import com.jack.ioultimateencrypt.sample.mvp.model.bean.Location
import com.jack.ioultimateencrypt.sample.mvp.model.bean.UpcomingMovieBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

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

    @GET("https://api-m.mtime.cn/Movie/MovieComingNew.api")
    fun queryUpcomingMovies(@Query("locationId") cityId: String): Observable<UpcomingMovieBean>
}