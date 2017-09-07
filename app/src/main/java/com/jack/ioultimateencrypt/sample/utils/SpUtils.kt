package com.jack.ioultimateencrypt.sample.utils

import android.content.Context
import com.jack.ioultimateencrypt.sample.constant.StoreKey
import com.jack.ioultimateencrypt.sample.mvp.model.bean.Location
import com.jackyang.android.support.exception.FAndroidException
import com.jackyang.android.support.injection.Injections
import com.jackyang.android.support.repository.KeyValueStore

/**
 * 2017/8/24.
 * github:[https://github.com/jacky1234]
 * @author  jackyang
 *
 */
class SpUtils private constructor(context: Context) {
    val mContext: Context = context
    val mkeyValueStore: KeyValueStore = Injections.getBean(KeyValueStore::class.java)

    fun isInTestMode(): Boolean {
        return mkeyValueStore.getBoolean(StoreKey.IS_IN_TEST_MODE)
    }

    fun saveBDLocation(bdLocation: String?) {
        mkeyValueStore.set(StoreKey.BD_LOCATION, bdLocation)
    }

    fun getBDLocation(): String? {
        return mkeyValueStore.getString(StoreKey.BD_LOCATION)
    }

    fun saveMyCity(city: Location.City?) {
        mkeyValueStore.set(StoreKey.MY_CITY, city)
    }

    fun getMyCity(): Location.City? {
        return mkeyValueStore.get(StoreKey.MY_CITY, Location.City::class.java)
    }

    fun saveCities(lists: List<Location.City>?) {
        mkeyValueStore.set(StoreKey.ALL_CITIES, lists)
    }

    fun getCities(): List<Location.City>? {
        return mkeyValueStore.getList(StoreKey.ALL_CITIES, Location.City::class.java)
    }

    fun clear() {
        mkeyValueStore.clear()
    }

    init {
        try {
            mkeyValueStore!!
        } catch (e: Exception) {
            throw FAndroidException("未在Injections中注册KeyValueStore", e)
        }
    }

    companion object {
        @Volatile
        var instance: SpUtils? = null

        fun getInstance(context: Context): SpUtils {
            if (instance == null) {
                synchronized(SpUtils::class.java) {
                    if (instance == null) {
                        instance = SpUtils(context)
                    }
                }
            }
            return instance!!
        }

    }
}