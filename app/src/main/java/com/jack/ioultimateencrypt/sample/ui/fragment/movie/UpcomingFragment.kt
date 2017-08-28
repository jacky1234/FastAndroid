package com.jack.ioultimateencrypt.sample.ui.fragment.movie

import android.support.v7.widget.LinearLayoutManager
import com.jack.ioultimateencrypt.sample.R
import com.jack.ioultimateencrypt.sample.base.BaseFragment
import com.jack.ioultimateencrypt.sample.mvp.contract.UpComingContract
import com.jack.ioultimateencrypt.sample.mvp.model.bean.Location
import com.jack.ioultimateencrypt.sample.mvp.model.bean.UpcomingMovieBean
import com.jack.ioultimateencrypt.sample.mvp.present.UpComingPresent
import com.jack.ioultimateencrypt.sample.rx.rxbus.EventConstant
import com.jack.ioultimateencrypt.sample.rx.rxbus.RxBusManager
import com.jack.ioultimateencrypt.sample.ui.adapter.UpcomingMovieAdapter
import kotlinx.android.synthetic.main.fragment_catelog.*

/**
 * 2017/8/25.
 * github:[https://github.com/jacky1234]
 * @author  jackyang
 *
 */
class UpcomingFragment : BaseFragment(), UpComingContract.View {

    override fun onResponseMovies(bean: UpcomingMovieBean?) {
        mUpcomingMovies.addAll(bean?.moviecomings!!)
        mAdapter?.notifyDataSetChanged()
    }

    override fun getLayoutResources(): Int {
        return R.layout.fragment_upcoming
    }

    lateinit var mPresent: UpComingContract.Present
    private var mCityId: Int? = null
    private val mUpcomingMovies = ArrayList<UpcomingMovieBean.MoviecomingsBean>()
    private lateinit var mAdapter: UpcomingMovieAdapter
    override fun initView() {
        mPresent = UpComingPresent(context, this)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        mAdapter = UpcomingMovieAdapter(R.layout.item_upcoming_movie, R.id.date_tv, mUpcomingMovies)
        recyclerView.adapter = mAdapter
        mAdapter.setOnItemClickListener { adapter, view, position ->
            TODO("请求详情")
            val item = mAdapter.getItem(position)
            item?.id
        }

        RxBusManager.register(this, EventConstant.ON_BDLOCATION_SUCCESS, Location.City::class.java)
                .subscribe { city ->
                    mCityId = city.id
                    mPresent.queryUpComingMovies(mCityId!!)
                }
    }

}