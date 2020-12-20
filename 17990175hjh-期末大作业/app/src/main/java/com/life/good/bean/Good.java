package com.life.good.bean;


import org.litepal.crud.DataSupport;

import java.io.Serializable;
public class Good extends DataSupport implements Serializable {
    private int id;
    public String name;
    public double price;
    public String des;
    public String img;
    public int count;
    public Good() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Good(String name, double price, String des, String img) {
        this.name = name;
        this.price = price;
        this.des = des;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
