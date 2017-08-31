package com.jack.ioultimateencrypt.sample.ui.fragment.movie

import android.graphics.Color
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.jack.ioultimateencrypt.sample.R
import com.jack.ioultimateencrypt.sample.base.BaseFragment
import com.jack.ioultimateencrypt.sample.mvp.contract.UpComingContract
import com.jack.ioultimateencrypt.sample.mvp.model.bean.Location
import com.jack.ioultimateencrypt.sample.mvp.model.bean.MovieCataBean
import com.jack.ioultimateencrypt.sample.mvp.model.bean.UpcomingMovieBean
import com.jack.ioultimateencrypt.sample.mvp.present.UpComingPresent
import com.jack.ioultimateencrypt.sample.rx.rxbus.EventConstant
import com.jack.ioultimateencrypt.sample.rx.rxbus.RxBusManager
import com.jack.ioultimateencrypt.sample.ui.adapter.MovieCataAdapter
import com.jack.ioultimateencrypt.sample.ui.adapter.UpcomingMovieAdapter
import com.jack.ioultimateencrypt.sample.ui.decoration.ItemHeaderDecoration
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
            mCityId = SpUtils.getInstance(context).getCities()?.get(0)?.id      //SpUtils.instance!!->wrong
        }

        if (mCityId == null) {
            mCityId = 292   //shanghai
        }
        mPresent.queryUpComingMovies(mCityId!!)
    }

    override fun onResponseMovies(bean: UpcomingMovieBean?) {
        swipeRefreshLayout?.isRefreshing = false
        swipeRefreshLayout?.setOnRefreshListener(this)
        mAdapter?.setEnableLoadMore(false)   //不需要加载更多

        mUpcomingMovies.clear()
        mUpcomingMovies.addAll(bean?.moviecomings!!)

        cataLists.clear()
        //获取月数
        val month = Calendar.getInstance().get(Calendar.MONTH) + 1
        cataLists.add(MovieCataBean("最受欢迎", null, true))
        cataLists.add(MovieCataBean(String.format("%d月大片", month + 1), month + 1, false))
        cataLists.add(MovieCataBean(String.format("%d月大片", month + 2), month + 2, false))

        mAdapter.notifyDataSetChanged()
        mMovieCataAdapter.notifyDataSetChanged()
    }

    override fun getLayoutResources(): Int {
        return R.layout.fragment_upcoming
    }

    lateinit var mPresent: UpComingContract.Present
    private var mCityId: Int? = null        //caution: 包装类型
    private val mUpcomingMovies = ArrayList<UpcomingMovieBean.MoviecomingsBean>()
    private lateinit var mAdapter: UpcomingMovieAdapter
    private lateinit var mMovieCataAdapter: MovieCataAdapter
    private val cataLists = ArrayList<MovieCataBean>()
    override fun initView() {
        mPresent = UpComingPresent(context, this)

        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        mAdapter = UpcomingMovieAdapter(R.layout.item_upcoming_movie, mUpcomingMovies)
        recyclerView.adapter = mAdapter
        mAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN)
        mAdapter.setNotDoAnimationCount(3)
        mAdapter.setOnItemClickListener { adapter, view, position ->
            //            TODO("请求详情")
        }

        recyclerView.addItemDecoration(object : ItemHeaderDecoration() {
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


        //load more and refresh
        swipeRefreshLayout.setOnRefreshListener(this)
        swipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189))
        mAdapter.setOnLoadMoreListener(this, recyclerView)

        registerListener()

        initHeader()
    }

    private fun registerListener() {
        RxBusManager.register(this, EventConstant.ON_BDLOCATION_SUCCESS, Location.City::class.java)
                .subscribe { city ->
                    mCityId = city.id
                    mPresent.queryUpComingMovies(mCityId!!)
                }
    }

    private fun initHeader() {
        val header = LayoutInflater.from(context).inflate(R.layout.item_header_upcoming, null)
        mAdapter.addHeaderView(header)

        var rvCatalog = header.findViewById(R.id.rvCatalog) as RecyclerView
        var rvDetail = header.findViewById(R.id.rvDetail) as RecyclerView

        //catalog
        rvCatalog.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        mMovieCataAdapter = MovieCataAdapter(R.layout.item_movie_catalog, cataLists)
        rvCatalog.adapter = mMovieCataAdapter
        //checkbox的影响
        mMovieCataAdapter.setOnItemChildClickListener { adapter, _, position ->

            cataLists.forEachIndexed { index, any ->
                any.bChecked = position == index
            }

            adapter.notifyDataSetChanged()
        }


    }

}