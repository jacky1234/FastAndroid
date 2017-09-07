package com.jack.ioultimateencrypt.sample.ui.fragment.movie

import android.graphics.Color
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.jack.ioultimateencrypt.sample.R
import com.jack.ioultimateencrypt.sample.base.BaseFragment
import com.jack.ioultimateencrypt.sample.mvp.contract.HotMovieContract
import com.jack.ioultimateencrypt.sample.mvp.model.bean.HotMovieBean
import com.jack.ioultimateencrypt.sample.mvp.model.bean.Location
import com.jack.ioultimateencrypt.sample.mvp.present.HotMoviesPresent
import com.jack.ioultimateencrypt.sample.rx.rxbus.EventConstant
import com.jack.ioultimateencrypt.sample.rx.rxbus.RxBusManager
import com.jack.ioultimateencrypt.sample.ui.adapter.HotMovieAdapter
import com.jack.ioultimateencrypt.sample.utils.SpUtils
import kotlinx.android.synthetic.main.fragment_catelog.*

/**
 * 2017/9/2.
 * github:[https://github.com/jacky1234]
 * @author  jackyang
 *
 */
class HotMovieFragment : BaseFragment(), HotMovieContract.View, SwipeRefreshLayout.OnRefreshListener {
    override fun onRefresh() {
        swipeRefreshLayout.setOnRefreshListener(null)

        if (mCityId == null) {
            mCityId = SpUtils.getInstance(context).getCities()?.get(0)?.id      //SpUtils.sp!!->wrong
        }

        if (mCityId == null) {
            mCityId = 292   //shanghai
        }
        mPresent.queryHotMovies(mCityId!!)
    }

    override fun onResponseHotMovies(bean: HotMovieBean) {
        swipeRefreshLayout?.isRefreshing = false

        if (bean.ms?.isEmpty() == false) {
            swipeRefreshLayout.setOnRefreshListener(this)
            mAdapter.setNewData(bean.ms)
        } else {
            swipeRefreshLayout.setOnRefreshListener(null)
            mAdapter.emptyView = noDataView
        }
    }

    override fun onError(t: Throwable) {
        mAdapter.emptyView = errorView
        swipeRefreshLayout?.isRefreshing = false
        swipeRefreshLayout.isEnabled = false
        swipeRefreshLayout?.setOnRefreshListener(null)
    }

    private lateinit var mAdapter: HotMovieAdapter
    private lateinit var mPresent: HotMovieContract.Present
    private val mHotMovies = ArrayList<HotMovieBean.MsBean>()
    private var mCityId: Int? = null        //caution: 包装类型

    override fun getLayoutResources(): Int {
        return R.layout.fragment_hot_movies
    }

    override fun initView() {
        mPresent = HotMoviesPresent(context, this)

        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        mAdapter = HotMovieAdapter(R.layout.item_hot_movie, mHotMovies)
        mAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN)
        mAdapter.setNotDoAnimationCount(3)

        recyclerView.adapter = mAdapter

        swipeRefreshLayout.setOnRefreshListener(this)
        swipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189))


        //listener
        RxBusManager.register(this, EventConstant.ON_BDLOCATION_SUCCESS, Location.City::class.java)
                .subscribe { city ->
                    swipeRefreshLayout.setOnRefreshListener(null)
                    mCityId = city.id
                    mPresent.queryHotMovies(mCityId!!)
                }

        noDataViewClickListener = View.OnClickListener { onRefresh() }
        errorViewClickListener = noDataViewClickListener
    }
}