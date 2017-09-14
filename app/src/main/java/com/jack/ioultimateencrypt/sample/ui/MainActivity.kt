package com.jack.ioultimateencrypt.sample.ui

import android.Manifest
import android.os.Bundle
import android.os.Handler
import android.support.annotation.IntRange
import android.support.annotation.NonNull
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.baidu.location.BDAbstractLocationListener
import com.baidu.location.BDLocation
import com.baidu.location.LocationClient
import com.baidu.location.LocationClientOption
import com.jack.ioultimateencrypt.sample.R
import com.jack.ioultimateencrypt.sample.mvp.contract.MainConstract
import com.jack.ioultimateencrypt.sample.mvp.model.bean.Location
import com.jack.ioultimateencrypt.sample.mvp.present.MainPresent
import com.jack.ioultimateencrypt.sample.rx.rxbus.EventConstant
import com.jack.ioultimateencrypt.sample.rx.rxbus.RxBusManager
import com.jack.ioultimateencrypt.sample.showToast
import com.jack.ioultimateencrypt.sample.ui.fragment.CatalogFragment
import com.jack.ioultimateencrypt.sample.ui.fragment.movie.MovieFragment
import com.jack.ioultimateencrypt.sample.utils.SpUtils
import com.jackyang.android.support.injection.Injections
import com.jackyang.android.support.repository.KeyValueStore
import com.safframework.log.L
import kotlinx.android.synthetic.main.activity_main.*
import permissions.dispatcher.*

@RuntimePermissions
class MainActivity : AppCompatActivity(), MainConstract.View {
    private lateinit var mCatalogFragment: CatalogFragment
    private lateinit var mMovieFragment: MovieFragment
    lateinit var mPresent: MainConstract.Present
    private var mLocationClient: LocationClient? = null
    lateinit var mkeyValueStore: KeyValueStore
    private var bExit = false
    private val mFragments = ArrayList<Fragment>()

    override fun onResponseCities(lists: List<Location.City>) {
        var city: Location.City? = null
        val location = SpUtils.instance!!.getBDLocation()
        if (location != null) {
            //计算匹配的city
            city = lists.firstOrNull { it.n!!.contains(location) || location.contains(it.n!!) }
        }

        if (city == null) {
            city = lists[0]
        }

        SpUtils.getInstance(this)!!.saveMyCity(city)
        RxBusManager.post(EventConstant.ON_BDLOCATION_SUCCESS, city)
    }

    override fun onDestroy() {
        super.onDestroy()

        mLocationClient?.stop()
        mLocationClient?.unRegisterLocationListener(locationListener)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFragment(savedInstanceState)

        mPresent = MainPresent(this, this)
        mkeyValueStore = Injections.getBean(KeyValueStore::class.java)
        MainActivityPermissionsDispatcher.startBDLocateWithCheck(this)

        menu_group.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.tab_main -> switchToFragment(0)
                R.id.tab_test -> switchToFragment(1)
            }
        }
        menu_group.check(R.id.tab_main)
    }

    private val locationListener: BDAbstractLocationListener = object : BDAbstractLocationListener() {
        override fun onReceiveLocation(location: BDLocation) {
            L.json(location)
            if (location.address.city != null) {
                SpUtils.getInstance(applicationContext).saveBDLocation(location.address.city)
            } else {
                showToast(location.locTypeDescription)
            }

            mPresent.queryCities()      //查询地址
        }

        /**
         * 回调定位诊断信息，开发者可以根据相关信息解决定位遇到的一些问题
         * 自动回调，相同的diagnosticType只会回调一次

         * @param locType           当前定位类型
         * *
         * @param diagnosticType    诊断类型（1~9）
         * *
         * @param diagnosticMessage 具体的诊断信息释义
         */
        override fun onLocDiagnosticMessage(locType: Int, diagnosticType: Int, diagnosticMessage: String) {
            when (diagnosticType) {
                LocationClient.LOC_DIAGNOSTIC_TYPE_BETTER_OPEN_GPS -> {
                    //建议打开GPS

                }
                LocationClient.LOC_DIAGNOSTIC_TYPE_BETTER_OPEN_WIFI -> {
                    //建议打开wifi，不必连接，这样有助于提高网络定位精度！

                }
                LocationClient.LOC_DIAGNOSTIC_TYPE_NEED_CHECK_LOC_PERMISSION -> {
                    //定位权限受限，建议提示用户授予APP定位权限！

                }
                LocationClient.LOC_DIAGNOSTIC_TYPE_NEED_CHECK_NET -> {
                    //网络异常造成定位失败，建议用户确认网络状态是否异常！

                }
                LocationClient.LOC_DIAGNOSTIC_TYPE_NEED_CLOSE_FLYMODE -> {
                    //手机飞行模式造成定位失败，建议用户关闭飞行模式后再重试定位！

                }
                LocationClient.LOC_DIAGNOSTIC_TYPE_NEED_INSERT_SIMCARD_OR_OPEN_WIFI -> {
                    //无法获取任何定位依据，建议用户打开wifi或者插入sim卡重试！

                }
                LocationClient.LOC_DIAGNOSTIC_TYPE_NEED_OPEN_PHONE_LOC_SWITCH -> {
                    //无法获取有效定位依据，建议用户打开手机设置里的定位开关后重试！

                }
                LocationClient.LOC_DIAGNOSTIC_TYPE_SERVER_FAIL -> {
                    //百度定位服务端定位失败
                    //建议反馈location.getLocationID()和大体定位时间到loc-bugs@baidu.com

                }
                LocationClient.LOC_DIAGNOSTIC_TYPE_FAIL_UNKNOWN -> {
                    //无法获取有效定位依据，但无法确定具体原因
                    //建议检查是否有安全软件屏蔽相关定位权限
                    //或调用LocationClient.restart()重新启动后重试！

                }
            }
        }
    }

    @NeedsPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
    fun startBDLocate() {
        if (mLocationClient == null) {
            val locationClientOption = LocationClientOption()
            locationClientOption.locationMode = LocationClientOption.LocationMode.Hight_Accuracy
            locationClientOption.setIsNeedAddress(true)
//            locationClientOption.setIsNeedLocationDescribe(true)
//            locationClientOption.setIsNeedLocationPoiList(true)

            mLocationClient = LocationClient(this, locationClientOption)
        }

        mLocationClient!!.registerLocationListener(locationListener)
        mLocationClient!!.start()
    }

    @OnShowRationale(Manifest.permission.ACCESS_COARSE_LOCATION)
    fun showRationaleForLocation(request: PermissionRequest) {
        AlertDialog.Builder(this)
                .setMessage(R.string.permission_locate_rationale)
                .setPositiveButton(R.string.button_allow, { _, _ -> request.proceed() })
                .setNegativeButton(R.string.button_deny, { _, _ -> request.cancel() })
                .show()
    }

    @OnPermissionDenied(Manifest.permission.ACCESS_COARSE_LOCATION)
    fun showDenyForLoaction() {
        showToast(resources.getString(R.string.permission_locate_deny))
    }

    override fun onRequestPermissionsResult(requestCode: Int, @NonNull permissions: Array<String>, @NonNull grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // NOTE: delegate the permission handling to generated method
        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults)
    }

    private fun initFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            //异常情况
            val fragments = supportFragmentManager.fragments
            for (item in fragments) {
                if (item is CatalogFragment) {
                    mCatalogFragment = item
                }

                if (item is MovieFragment) {
                    mMovieFragment = item
                }
            }

        } else {
            mCatalogFragment = CatalogFragment()
            mMovieFragment = MovieFragment()

            val transaction = supportFragmentManager.beginTransaction()
            transaction
                    .add(R.id.frameLayout, mMovieFragment)
                    .add(R.id.frameLayout, mCatalogFragment)
                    .hide(mMovieFragment)
                    .hide(mCatalogFragment)
                    .commit()
        }
        mFragments.clear()
        mFragments.add(mMovieFragment)
        mFragments.add(mCatalogFragment)

    }

    private fun switchToFragment(@IntRange(from = 0, to = 1) index: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        mFragments.forEachIndexed { i, f ->
            if (i == index) {
                transaction.show(f)
            } else
                transaction.hide(f)
        }
        transaction.commit()
    }

    override fun onBackPressed() {
        if (!bExit) {
            bExit = true
            showToast(resources.getString(R.string.exit_notify))
            Handler().postDelayed({ bExit = false }, 200)
        } else {
            super.onBackPressed()
        }
    }
}
