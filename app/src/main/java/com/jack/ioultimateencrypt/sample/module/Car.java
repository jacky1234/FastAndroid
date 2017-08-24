package com.jack.ioultimateencrypt.sample.module;

import com.jackyang.android.support.lang.BaseObject;

/**
 * 2017/8/11.
 * github:[https://github.com/jacky1234]
 *
 * @author jackyang
 */

public class Car extends BaseObject {
    private String brand;
    private String manufacturer;

    public Car() {
    }

    public Car(String brand, String manufacturer) {
        this.brand = brand;
        this.manufacturer = manufacturer;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                '}';
    }
}
