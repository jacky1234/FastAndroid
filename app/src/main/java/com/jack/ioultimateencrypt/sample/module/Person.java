package com.jack.ioultimateencrypt.sample.module;

import com.jackyang.android.support.lang.BaseObject;

/**
 * 2017/8/8.
 * github:[https://github.com/jacky1234]
 *
 * @author jackyang
 */

public class Person extends BaseObject{
    private int age;
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
