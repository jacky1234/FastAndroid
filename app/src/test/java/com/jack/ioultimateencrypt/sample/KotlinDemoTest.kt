package com.jack.ioultimateencrypt.sample

import com.jack.ioultimateencrypt.sample.module.School
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

/**
 * 2017/8/14.
 * github:[https://github.com/jacky1234]
 * @author  jackyang
 *
 */
class KotlinDemoTest {

    @Test
    fun module() {
        val school = School("suzhou university", 1920, true)
        Utils.P(school.toString())

        Utils.P(school.history)

        school.name = "nantong university"
        school.is211 = false
        Utils.P(school.toString())
    }

    /**
     *
     */
    @Test
    fun section() {
        for (i in 0..10) {
            print(i)
        }
        println()

        //倒序迭代
        for (i in 10 downTo 0) {
            print(i)
        }
        println()

        //步长为2的迭代
        for (i in 0..10 step 2) {
            print(i)
        }
        println()

        //i在[0,10)区间，排除了10
        for (i in 0 until 10) {
            print(i)
        }
        println()
    }

    fun Date.format(pattern: String = "yyyy-MM-dd"): String {
        return SimpleDateFormat(pattern).format(this)
    }

    /**
     * 更方便的扩展
     */
    @Test
    fun extent_test() {
        Utils.P(Date().format())
    }

    /**
     *
    what is this (the receiver)
    what is it (the argument)
    what is the result
     */
    @Test
    fun let_test() {
        val str: String = "_HelloWorld"
        val result = str.let {
            print(this) // Receiver
            print(it) // Argument
            10L // Block return value
        }
        println("\n" + result)
    }

    @Test
    fun apply_test() {
        ArrayList<String>().apply {
            add("testApply")
            add("testApply")
            add("testApply")
            println("this = " + this)
        }.let { println(it) }
    }

}