package com.jack.ioultimateencrypt.sample;

import com.jack.ioultimateencrypt.sample.module.Country;
import com.safframework.tony.common.reflect.Reflect;

import org.junit.Test;

/**
 * 2017/8/14.
 * github:[https://github.com/jacky1234]
 *
 * @author jackyang
 */

public class ReflectDemoTest {
    @Test
    public void simpleTest() {
        final String s = Reflect.on("com.jack.ioultimateencrypt.sample.module.Country").create()
                .set("name", "China")
                .set("mark", "CH").toString();

        Utils.P(s);

    }

    @Test
    public void constructTest() {
        final Reflect reflect = Reflect.on("com.jack.ioultimateencrypt.sample.module.Country").create("China", "Ch");
        Utils.P(reflect.get("name"));
    }

    @Test
    public void getRealTest() {
        final Country c = Reflect.on("com.jack.ioultimateencrypt.sample.module.Country").create("China", "Ch").get();
        Utils.P(c.toString());
    }

}
