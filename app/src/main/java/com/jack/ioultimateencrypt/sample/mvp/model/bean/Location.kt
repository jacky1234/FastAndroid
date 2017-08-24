package com.jack.ioultimateencrypt.sample.mvp.model.bean

/**
 * 2017/8/23.
 * github:[https://github.com/jacky1234]
 * @author  jackyang
 *
 */
data class Location(var p: List<City>) {
    data class City(var count: Int, var id: Int, var n: String?, var pinyinFull: String?, var pinyinShort: String?)
}

