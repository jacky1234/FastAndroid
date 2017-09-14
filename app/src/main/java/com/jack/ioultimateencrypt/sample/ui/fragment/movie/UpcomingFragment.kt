package com.jack.ioultimateencrypt.sample.ui.fragment.movie

import android.graphics.Color
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.jack.ioultimateencrypt.sample.R
import com.jack.ioultimateencrypt.sample.base.BaseFragment
import com.jack.ioultimateencrypt.sample.color
import com.jack.ioultimateencrypt.sample.effect.FloatingEffect
import com.jack.ioultimateencrypt.sample.effect.recyclerview.RecyclerViewHelper
import com.jack.ioultimateencrypt.sample.mvp.contract.UpComingContract
import com.jack.ioultimateencrypt.sample.mvp.model.bean.Location
import com.jack.ioultimateencrypt.sample.mvp.model.bean.MovieCataBean
import com.jack.ioultimateencrypt.sample.mvp.model.bean.UpcomingMovieBean
import com.jack.ioultimateencrypt.sample.mvp.present.UpComingPresent
import com.jack.ioultimateencrypt.sample.rx.rxbus.EventConstant
import com.jack.ioultimateencrypt.sample.rx.rxbus.RxBusManager
import com.jack.ioultimateencrypt.sample.showToast
import com.jack.ioultimateencrypt.sample.ui.adapter.MovieCataAdapter
import com.jack.ioultimateencrypt.sample.ui.adapter.MovieDetailAdapter
import com.jack.ioultimateencrypt.sample.ui.adapter.UpcomingMovieAdapter
import com.jack.ioultimateencrypt.sample.ui.decoration.ItemHeaderDecoration
import com.jack.ioultimateencrypt.sample.ui.decoration.SimpleDividerDecoration
import com.jack.ioultimateencrypt.sample.utils.SpUtils
import kotlinx.android.synthetic.main.fragment_catelog.*
import java.util.*

/**
 * 2017/8/25.
 * github:[https://github.com/jacky1234]
 * @author  jackyang
 *
 */
class UpcomingFragment : BaseFragment(), UpComingContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    override fun onLoadMoreRequested() {
    }

    override fun onRefresh() {
        swipeRefreshLayout.setOnRefreshListener(null)
        mAdapter.setEnableLoadMore(false)

        if (mCityId == null) {
            mCityId = SpUtils.getInstance(context).getCities()?.get(0)?.id      //SpUtils.sp!!->wrong
        }

        if (mCityId == null) {
            mCityId = 292   //shanghai
        }
        mPresent.queryUpComingMovies(mCityId!!)
    }

    override fun onResponseMovies(bean: UpcomingMovieBean?) {
        swipeRefreshLayout?.isRefreshing = false

        if (mAdapter.isLoadMoreEnable) {
            mAdapter.setEnableLoadMore(false)   //不需要加载更多
        }

        if (bean != null && bean.moviecomings?.isEmpty() == false && bean.attention?.isEmpty() == false) {
            swipeRefreshLayout?.setOnRefreshListener(this)
            swipeRefreshLayout.isEnabled = true

            //获取月数
            val month = Calendar.getInstance().get(Calendar.MONTH) + 1
            cataLists.clear()
            cataLists.add(MovieCataBean("最受欢迎", null, true, 0))
            cataLists.add(MovieCataBean(String.format("%d月大片", month + 1), month + 1, false, 1))
            cataLists.add(MovieCataBean(String.format("%d月大片", month + 2), month + 2, false, 2))

            mAdapter.setNewData(bean.moviecomings)
            mMovieDetailAdapter.setNewData(bean.attention)
            mMovieCataAdapter.setNewData(cataLists)
        } else {
            mAdapter.emptyView = noDataView
        }
    }

    override fun onError(t: Throwable) {
        mAdapter.emptyView = errorView
        swipeRefreshLayout?.isRefreshing = false
        swipeRefreshLayout.isEnabled = false
        swipeRefreshLayout?.setOnRefreshListener(null)

        if (mAdapter.isLoadMoreEnable) {
            mAdapter.setEnableLoadMore(false)   //不需要加载更多
        }
    }

    override fun getLayoutResources(): Int {
        return R.layout.fragment_upcoming
    }

    private lateinit var mPresent: UpComingContract.Present
    private var mCityId: Int? = null        //caution: 包装类型
    private val mUpcomingMovies = ArrayList<UpcomingMovieBean.MoviecomingsBean>()
    private lateinit var mAdapter: UpcomingMovieAdapter
    private lateinit var mMovieCataAdapter: MovieCataAdapter
    private lateinit var mMovieDetailAdapter: MovieDetailAdapter
    private val cataLists = ArrayList<MovieCataBean>()
    private val detailLists = ArrayList<UpcomingMovieBean.AttentionBean>()
    override fun initView() {
        errorViewClickListener = View.OnClickListener { v ->
            onRefresh()
        }

        noDataViewClickListener = errorViewClickListener

        mPresent = UpComingPresent(context, this)

        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        mAdapter = UpcomingMovieAdapter(R.layout.item_upcoming_movie, mUpcomingMovies)
        val mFloatingEffect = FloatingEffect(activity)
        mAdapter.inject(mFloatingEffect)
        recyclerView.adapter = mAdapter
        mAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN)
        mAdapter.setNotDoAnimationCount(3)
        mAdapter.setOnItemClickListener { adapter, view, position ->
            //            TODO("请求详情")
        }

        mAdapter.setOnItemChildClickListener { adapter, view, position ->
            when (view.id) {
                R.id.iv_like -> mFloatingEffect.get(position).onClickListener?.onClick(view)
            }
        }

        recyclerView.addItemDecoration(object : ItemHeaderDecoration() {
            override fun getTotalCount(): Int {
                return mAdapter.data.size
            }

            override fun getHeadCount(): Int {
                return mAdapter.headerLayoutCount
            }

            override fun isStickHeaderChange(curPos: Int, nextPos: Int): Boolean {
                val curItem = mAdapter.getItem(curPos)
                val nextItem = mAdapter.getItem(nextPos)
                return curItem?.rMonth != nextItem?.rMonth || curItem?.rDay != nextItem?.rDay
            }

            override fun setDataOnView(curPos: Int, view: View?) {
                val curItem = mAdapter.getItem(curPos)
                val textView = view?.findViewById(R.id.date_tv) as TextView
                textView.text = String.format("%d月%d日", curItem?.rMonth, curItem?.rDay)
            }

            override fun getDecorationLayoutId(): Int {
                return R.id.date_tv
            }
        })

        val simpleDividerDecoration = SimpleDividerDecoration(context, context.color(R.color.gray), 0.5f, RecyclerView.VERTICAL)

        recyclerView.addItemDecoration(simpleDividerDecoration)

        //load more and refresh
        swipeRefreshLayout.setOnRefreshListener(this)
        swipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189))
        mAdapter.setOnLoadMoreListener(this, recyclerView)

        registerListener()

        RecyclerViewHelper.Builder(recyclerView)
                .setParallaxHeader(R.layout.item_header_upcoming)
                .registerOnParallaxHeadSetListener(object : RecyclerViewHelper.OnParallaxHeadSetListener {
                    override fun onParallaxHeadSetListener(head: RecyclerViewHelper.CustomRelativeWrapper) {
                        initHeader(head)
                    }
                })
                .registerParallaxScrollListener(object : RecyclerViewHelper.OnParallaxScroll {
                    override fun onParallaxScroll(percentage: Float, offset: Float, parallax: View) {
                        println("percent:$percentage,offset:$offset")
                    }
                })
                .create()
    }

    private fun registerListener() {
        RxBusManager.register(this, EventConstant.ON_BDLOCATION_SUCCESS, Location.City::class.java)
                .subscribe { city ->
                    mCityId = city.id
                    mPresent.queryUpComingMovies(mCityId!!)
                }
    }

//    override fun onLazyLoad() {
//        super.onLazyLoad()
//        if (mCityId != null) { //loc success
//            mAdapter.emptyView = loadingView
//            mPresent.queryUpComingMovies(mCityId!!)
//        }
//    }

    private fun initHeader(header: View) {
        mAdapter.addHeaderView(header)

        val rvCatalog = header.findViewById(R.id.rvCatalog) as RecyclerView
        val rvDetail = header.findViewById(R.id.rvDetail) as RecyclerView

        val simpleDividerDecoration = SimpleDividerDecoration(context, Color.TRANSPARENT, 12.0f, RecyclerView.HORIZONTAL)
        simpleDividerDecoration.setNeedOffsetHeader(true)
        simpleDividerDecoration.setNeedOffsetFooter(false)
        //catalog
        rvCatalog.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        mMovieCataAdapter = MovieCataAdapter(R.layout.item_movie_catalog, cataLists)
        rvCatalog.adapter = mMovieCataAdapter
        rvCatalog.addItemDecoration(simpleDividerDecoration)
        mMovieCataAdapter.setOnItemChildClickListener { adapter, _, position ->
            context.showToast(getString(R.string.mention_for_test))
            cataLists.forEachIndexed { index, any ->
                any.bChecked = position == index
            }

            adapter.notifyDataSetChanged()
        }

        //detail
        rvDetail.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        mMovieDetailAdapter = MovieDetailAdapter(R.layout.item_movie_detail, detailLists)
        rvDetail.adapter = mMovieDetailAdapter
        rvDetail.addItemDecoration(simpleDividerDecoration)
    }
}

