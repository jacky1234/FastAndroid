package com.jack.ioultimateencrypt.sample.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.jack.ioultimateencrypt.sample.base.BaseActiivty
import com.jack.ioultimateencrypt.sample.utils.SpUtils

/**
 * 2017/9/14.
 * github:[https://github.com/jacky1234]
 * @author  jackyang
 *
 */
class MovieDetailActivity : BaseActiivty() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bdLocation = SpUtils.getInstance(this).getBDLocation()
        val movieId:String = intent.getStringExtra(INTENT_MOVIEID)


    }


    companion object {
        val INTENT_MOVIEID = "INTENT_MOVIEID"

        @JvmStatic
        fun lauch(a: Activity, movieId: String) {
            val intent = Intent(a, MovieDetailActivity::class.java)
            intent.putExtra(INTENT_MOVIEID, movieId)
            a.startActivity(intent)
        }
    }

}