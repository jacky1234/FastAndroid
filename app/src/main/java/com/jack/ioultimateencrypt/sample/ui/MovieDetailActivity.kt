package com.jack.ioultimateencrypt.sample.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.blankj.utilcode.util.SpanUtils
import com.bumptech.glide.Glide
import com.jack.ioultimateencrypt.sample.R
import com.jack.ioultimateencrypt.sample.base.BaseActiivty
import com.jack.ioultimateencrypt.sample.color
import com.jack.ioultimateencrypt.sample.effect.recyclerview.RecyclerViewHelper
import com.jack.ioultimateencrypt.sample.mvp.contract.MovieDetailContract
import com.jack.ioultimateencrypt.sample.mvp.model.bean.MovieDetailBean
import com.jack.ioultimateencrypt.sample.mvp.model.bean.MovieDetailBean.MovieDetailType
import com.jack.ioultimateencrypt.sample.mvp.present.MovieDetailPresent
import com.jack.ioultimateencrypt.sample.showToast
import com.jack.ioultimateencrypt.sample.ui.adapter.MovieDetailAdapter
import com.jack.ioultimateencrypt.sample.ui.decoration.SimpleDividerDecoration
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

        Glide.with(this).load(movieDetailBean.image)
                .centerCrop()
                .placeholder(R.drawable.welcome)
                .dontAnimate()
                .into(mAdapter.headerLayout.findViewById(R.id.iv_cover) as ImageView)
        mAdapter.headerLayout.findViewById(R.id.iv_play).setOnClickListener { showToast(R.string.mention_for_dev) }
        (mAdapter.headerLayout.findViewById(R.id.tv_mv_name_zh) as TextView).text = movieDetailBean.titleCn
        (mAdapter.headerLayout.findViewById(R.id.tv_mv_name_en) as TextView).text = movieDetailBean.titleEn
        (mAdapter.headerLayout.findViewById(R.id.tv_duration) as TextView).text = movieDetailBean.runTime

        val sb = StringBuffer()
        movieDetailBean.type?.forEachIndexed { index, s ->
            if (index > 2) return@forEachIndexed
            sb.append(s).append("/")
        }

        (mAdapter.headerLayout.findViewById(R.id.tv_classify) as TextView).text = sb.substring(0, sb.length - 1)
        (mAdapter.headerLayout.findViewById(R.id.tv_time_show) as TextView).text = movieDetailBean.year + "年上映"

        if (!TextUtils.isEmpty(movieDetailBean.commonSpecial)) {
            (mAdapter.headerLayout.findViewById(R.id.tv_commonSpecial) as TextView).text = SpanUtils()
                    .append("''").setFontSize(16, true).setForegroundColor(color(R.color.orange))
                    .append(movieDetailBean.commonSpecial!!).setFontSize(12, true).setForegroundColor(color(R.color.orange))
                    .create()
        } else mAdapter.headerLayout.findViewById(R.id.tv_commonSpecial).visibility = View.GONE

        mAdapter.headerLayout.findViewById(R.id.iv_iMax).visibility = if (movieDetailBean.isIMAX) View.VISIBLE else View.OVER_SCROLL_NEVER
        mAdapter.headerLayout.findViewById(R.id.iv_3d).visibility = if (movieDetailBean.is3D) View.VISIBLE else View.OVER_SCROLL_NEVER

        val rating = movieDetailBean.rating!!
        if (!TextUtils.isEmpty(rating) && rating.length == 3) {
            (mAdapter.headerLayout.findViewById(R.id.tv_score) as TextView).text = SpanUtils()
                    .append(rating.substring(0, 2)).setFontSize(16, true).setBold()
                    .append(rating.subSequence(2, 3)).setFontSize(12, true).setBold()
                    .create()
        } else mAdapter.headerLayout.findViewById(R.id.tv_score).visibility = View.GONE
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
                    adapter.notifyItemChanged(position + adapter.headerLayoutCount)
                }
            }
        }
    }

    private fun initView() {
        recyclerView.setHasFixedSize(false)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.addItemDecoration(SimpleDividerDecoration(this, color(R.color.gray), 15.0f, RecyclerView.VERTICAL))
        val mHeader = LayoutInflater.from(this).inflate(R.layout.view_mv_detail_header, null)

        //init adapter
        mTypes.add(MovieDetailType(MovieDetailType.TYPE_STORY))
        mTypes.add(MovieDetailType(MovieDetailType.TYPE_STAFF))
        mTypes.add(MovieDetailType(MovieDetailType.TYPE_RANK))
        mAdapter = MovieDetailAdapter(mTypes)

        RecyclerViewHelper.Builder(recyclerView)
                .setParallaxHeader(mHeader)
                .registerOnParallaxHeadSetListener(object : RecyclerViewHelper.OnParallaxHeadSetListener {
                    override fun onParallaxHeadSetListener(head: RecyclerViewHelper.CustomRelativeWrapper) {
                        mAdapter.addHeaderView(head)
                    }
                })
                .setScrollMultiplier(0.5f)
                .registerParallaxScrollListener(object : RecyclerViewHelper.OnParallaxScroll {
                    override fun onParallaxScroll(percentage: Float, offset: Float, parallax: View) {
                        val drawable = layout_title.background
                        drawable.alpha = Math.round(255 * percentage)
                        layout_title.setBackgroundDrawable(drawable)
                    }
                })
                .create()
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