package com.jack.ioultimateencrypt.sample

import com.jack.ioultimateencrypt.sample.mvp.model.bean.Location
import org.junit.Test

/**
 * 2017/9/4.
 * github:[https://github.com/jacky1234]
 * @author  jackyang
 *
 */
class CollectorTest {
    @Test
    fun set_test() {
        val set = HashSet<Int>()

        set.add(2)
        set.add(4)
        set.add(3)
        set.add(5)
        set.add(1)
        set.add(1)

        set.forEach { i ->
            print(i.toString() + "\t")
        }
        println()
    }

    /**
     * LinkedHashSet
     */
    @Test
    fun set_city() {
        val set = LinkedHashSet<Location.City>()
        val city0 = Location.City(10, 1, "上海", "shanghai", "sh")
        val city1 = Location.City(10, 2, "上海", "shanghai", "sh")
        val city2 = Location.City(10, 3, "上海", "shanghai", "sh")

        set.add(city1)
        set.add(city0)
        set.add(city2)

        set.forEach { c->
            print(c.toString())
        }
        println()
    }
}