package com.life.good.db;

public class Car {
    int id;
    String userId;
    int goodsNum;
    String goodsname;
    String goodimg;
    String price;
    String choosed;
    public Car(String userId,  int goodsNum, String goodsname, String price, String goodimg, String choosed) {
        this.userId = userId;
        this.goodsNum = goodsNum;
        this.goodsname = goodsname;
        this.price = price;
        this.goodimg = goodimg;
        this.choosed = choosed;
    }

    public Car(int id, String userId,  int goodsNum, String goodsname, String price, String goodimg, String choosed) {
        this.id = id;
        this.userId = userId;
        this.goodsNum = goodsNum;
        this.goodsname = goodsname;
        this.price = price;
        this.goodimg = goodimg;
        this.choosed = choosed;
    }

    public String getGoodimg() {
        return goodimg;
    }

    public void setGoodimg(String goodimg) {
        this.goodimg = goodimg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public int getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(int goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getChoosed() {
        return choosed;
    }

    public void setChoosed(String choosed) {
        this.choosed = choosed;
    }
}
