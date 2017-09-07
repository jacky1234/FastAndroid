package com.jack.ioultimateencrypt.sample.constant

/**
 * 2017/8/24.
 * github:[https://github.com/jacky1234]
 * @author  jackyang
 *
 */
class StoreKey {
    companion object {
        //百度定位的城市名称
        val BD_LOCATION = "BD_LOCATION"

        //通过接口返回的所有城市
        val ALL_CITIES = "ALL_CITIES"

        //通过接口返回的所有城市，结合百度定位得到的我所在的城市信息 City
        val MY_CITY = "MY_CITY"

        //我选择的城市，一个List，其中List[0] 是我最近一次选择的城市
        val MY_CHOOSED_CITY = "MY_CHOOSED_CITY"


        val IS_IN_TEST_MODE = "IS_IN_TEST_MODE"
    }
}