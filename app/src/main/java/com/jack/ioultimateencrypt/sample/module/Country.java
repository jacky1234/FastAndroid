package com.jack.ioultimateencrypt.sample.module;

import com.safframework.log.L;

/**
 * 2017/8/14.
 * github:[https://github.com/jacky1234]
 *
 * @author jackyang
 */

public class Country {
    private String name;
    private String mark;

    public Country() {
    }

    public Country(String name, String mark) {
        this.name = name;
        this.mark = mark;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", mark='" + mark + '\'' +
                '}';
    }
}
