package com.jack.ioultimateencrypt.sample.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.jack.ioultimateencrypt.sample.R
import com.jack.ioultimateencrypt.sample.base.BaseActiivty
import com.jack.ioultimateencrypt.sample.mvp.contract.MovieDetailConstract
import com.jack.ioultimateencrypt.sample.mvp.model.bean.MovieDetailBean
import com.jack.ioultimateencrypt.sample.mvp.present.MovieDetailPresent
import com.jack.ioultimateencrypt.sample.utils.SpUtils
import kotlinx.android.synthetic.main.activity_movie_detail.*

/**
 * 2017/9/14.
 * github:[https://github.com/jacky1234]
 * @author  jackyang
 *
 */
class MovieDetailActivity : BaseActiivty(), MovieDetailConstract.View {
    private lateinit var mPresent: MovieDetailConstract.Present
//    private lateinit var mAdapter: MovieDetailAdapter

    override fun onMovieDetailResponse(movieDetailBean: MovieDetailBean) {

    }

    override fun onError(t: Throwable) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        initView()

        mPresent = MovieDetailPresent(this, this)
        val myCity = SpUtils.getInstance(this).getMyCity()
        val movieId: String = intent.getStringExtra(INTENT_MOVIEID)
        mPresent!!.queryMovieDetail(myCity!!.id.toString(), movieId)
    }

    private fun initView() {
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        recyclerView.adapter =
    }


    companion object {
        val INTENT_MOVIEID = "INTENT_MOVIEID"

        @JvmStatic
        fun lauch(a: Activity, movieId: String) {
            val intent = Intent(a, MovieDetailActivity::class.java)
            intent.putExtra(INTENT_MOVIEID, movieId)
            a.startActivity(intent)
        }
    }

}