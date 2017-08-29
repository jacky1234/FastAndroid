package com.jack.ioultimateencrypt.sample.ui.fragment.movie

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.jack.ioultimateencrypt.sample.R
import com.jack.ioultimateencrypt.sample.base.BaseFragment
import com.jack.ioultimateencrypt.sample.mvp.contract.UpComingContract
import com.jack.ioultimateencrypt.sample.mvp.model.bean.Location
import com.jack.ioultimateencrypt.sample.mvp.model.bean.UpcomingMovieBean
import com.jack.ioultimateencrypt.sample.mvp.present.UpComingPresent
import com.jack.ioultimateencrypt.sample.rx.rxbus.EventConstant
import com.jack.ioultimateencrypt.sample.rx.rxbus.RxBusManager
import com.jack.ioultimateencrypt.sample.ui.adapter.UpcomingMovieAdapter
import com.jack.ioultimateencrypt.sample.ui.decoration.ItemHeaderDecoration
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
        mAdapter = UpcomingMovieAdapter(R.layout.item_upcoming_movie, mUpcomingMovies)
        recyclerView.adapter = mAdapter
        mAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN)
        mAdapter.setNotDoAnimationCount(3)
        mAdapter.setOnItemClickListener { adapter, view, position ->
            //            TODO("请求详情")
//            val item = mAdapter.getItem(position)
//            item?.id
        }

        recyclerView.addItemDecoration(object : ItemHeaderDecoration() {
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


        RxBusManager.register(this, EventConstant.ON_BDLOCATION_SUCCESS, Location.City::class.java)
                .subscribe { city ->
                    mCityId = city.id
                    mPresent.queryUpComingMovies(mCityId!!)
                }
    }

}