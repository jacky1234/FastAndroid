package com.jack.ioultimateencrypt.sample.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.jack.ioultimateencrypt.sample.R
import com.jack.ioultimateencrypt.sample.base.BaseActiivty
import com.jack.ioultimateencrypt.sample.mvp.contract.MovieDetailContract
import com.jack.ioultimateencrypt.sample.mvp.model.bean.MovieDetailBean
import com.jack.ioultimateencrypt.sample.mvp.model.bean.MovieDetailBean.MovieDetailType
import com.jack.ioultimateencrypt.sample.mvp.present.MovieDetailPresent
import com.jack.ioultimateencrypt.sample.showToast
import com.jack.ioultimateencrypt.sample.ui.adapter.MovieDetailAdapter
import com.jack.ioultimateencrypt.sample.utils.SpUtils
import kotlinx.android.synthetic.main.activity_movie_detail.*
import java.util.*

/**
 * 2017/9/14.
 * github:[https://github.com/jacky1234]
 * @author  jackyang
 *
 */
class MovieDetailActivity : BaseActiivty(), MovieDetailContract.View {
    private lateinit var mPresent: MovieDetailContract.Present
    private lateinit var mAdapter: MovieDetailAdapter
    private val mTypes = ArrayList<MovieDetailType>()

    override fun onMovieDetailResponse(movieDetailBean: MovieDetailBean) {
        mAdapter.mMovieDetailBean = movieDetailBean
        recyclerView.adapter = mAdapter
    }

    override fun onError(t: Throwable) {
        showToast(R.string.request_error)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        initView()
        initListener()

        mPresent = MovieDetailPresent(this, this)
        val myCity = SpUtils.getInstance(this).getMyCity()
        val movieId: String = intent.getStringExtra(INTENT_MOVIEID)
        mPresent!!.queryMovieDetail(myCity!!.id.toString(), movieId)
    }

    private fun initListener() {
        mAdapter.setOnItemChildClickListener { adapter, view, position ->
            when (view.id) {
                R.id.tv_story -> {
                    mAdapter.mMovieDetailBean.isExpanded = !mAdapter.mMovieDetailBean!!.isExpanded
                    adapter.notifyItemChanged(position)
                }
            }
        }
    }

    private fun initView() {
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        //init adapter
        mTypes.add(MovieDetailType(MovieDetailType.TYPE_STORY))
        mTypes.add(MovieDetailType(MovieDetailType.TYPE_STAFF))
        mTypes.add(MovieDetailType(MovieDetailType.TYPE_RANK))
        mAdapter = MovieDetailAdapter(mTypes)
    }


    companion object {
        val INTENT_MOVIEID = "INTENT_MOVIEID"

        @JvmStatic
        fun launch(a: Activity, movieId: String) {
            val intent = Intent(a, MovieDetailActivity::class.java)
            intent.putExtra(INTENT_MOVIEID, movieId)
            a.startActivity(intent)
        }
    }
}