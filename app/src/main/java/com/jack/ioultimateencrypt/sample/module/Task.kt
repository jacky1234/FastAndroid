package com.jack.ioultimateencrypt.sample.module

/**
 * 2017/8/16.
 * github:[https://github.com/jacky1234]
 * @author  jackyang
 *
 */
class Task(var id: Int, var name: String, var duration: Int, var isFinalMission: Boolean) {
    override fun toString(): String {
        return "Task(id=$id, name='$name', duration=$duration)"
    }
}