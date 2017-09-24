package com.jack.ioultimateencrypt.sample.ui.testui

import android.view.View
import android.widget.Spinner
import com.blankj.utilcode.util.SPUtils
import com.jack.ioultimateencrypt.sample.R
import com.jack.ioultimateencrypt.sample.aop.AopShow
import com.jack.ioultimateencrypt.sample.base.BaseTestActivity
import com.jack.ioultimateencrypt.sample.toJson
import com.jack.ioultimateencrypt.sample.utils.SpUtils
import com.jack.test.logger.Log
import com.jackyang.android.aop.annotation.HookMethod
import com.safframework.cache.Cache
import com.safframework.log.L
import com.safframework.prefs.AppPrefs

/**
 * 2017/8/11.
 * github:[https://github.com/jacky1234]
 *
 * @author jackyang
 * 测试用 baseActivity
 */

class AopTestActivity : BaseTestActivity() {

    override fun description() {
        Log.d(TAG, "Aop test...")
    }

    override fun getArrayId(): Int {
        return R.array.aop
    }

    override fun onSpinnerItemClick(sp: Spinner, view: View, position: Int) {
        Log.d(TAG, getSpInfo(position))
        when (position) {
            0 -> AopShow.asynTest()
            1 -> {
                AopShow.cacheable_save()
                AopShow.cacheable_save2()
            }
            2 -> {
                val cache = Cache.get(this)
                Log.d(TAG, "read person:" + cache.getObject("person")?.toString())
                Log.d(TAG, "read car:" + cache.getObject("car")?.toString())
            }
            3 -> AopShow.debugTest()
            4 -> hookTest()
            5 -> AopShow.logTest("jackyang", 20)
            6 -> AopShow.pref_save()
            7 -> {
                val appPrefs = AppPrefs.get(this)
                Log.d(TAG, "read person:" + appPrefs.getObject("Person").toString())
            }
            8 -> AopShow.safe_test()
            9 -> {
                SPUtils.getInstance("test")

                val cities = SpUtils.getInstance(this).getCities()!!
                val mJson = cities.toJson()

                AopShow.writeXml(mJson)
                val readXml = AopShow.readXml()
                L.d(readXml.toJson())
            }
            10 -> {
                val cities = SpUtils.getInstance(this).getCities()!!

                AopShow.writeLists(cities)
                val list = AopShow.getObj(this)
                L.d(list.toJson())
            }
        }
    }


    @HookMethod(beforeMethod = "method1", afterMethod = "method2")
    private fun hookTest() {
        Log.d(TAG, "originExecute...")
    }

    private fun method1() {
        Log.d(TAG, "method1() is called before hookTest()")
    }

    private fun method2() {
        Log.d(TAG, "method2() is called after hookTest()")
    }
}
