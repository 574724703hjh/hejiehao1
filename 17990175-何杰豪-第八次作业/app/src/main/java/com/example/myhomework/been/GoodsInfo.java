package com.example.myhomework.been;

import com.example.myhomework.R;

import java.util.ArrayList;

public class GoodsInfo {
    public long rowid; // 行号
    public int sn; // 序号
    public String name; // 名称
    public String desc; // 描述
    public float price; // 价格
    public String thumb_path; // 小图的保存路径
    public String pic_path; // 大图的保存路径
    public int thumb; // 小图的资源编号
    public int pic; // 大图的资源编号

    public GoodsInfo() {
        rowid = 0L;
        sn = 0;
        name = "";
        desc = "";
        price = 0;
        thumb_path = "";
        pic_path = "";
        thumb = 0;
        pic = 0;
    }

    // 声明一个手机商品的名称数组
    private static String[] mNameArray = {
            "郁金香", "白桃", "小羊猫", "笑脸", "渐变小熊", "冷淡风","小熊","波点怪兽"
    };
    // 声明一个手机商品的描述数组
    private static String[] mDescArray = {
            "小羊山坡羊猫伞娃郁金香手机壳",
            "壳空间白桃手机壳",
            "小羊山坡四娃羊手机壳",
            "气囊防摔笑脸nice happy手机壳",
            "渐变色小熊手机壳",
            "荧光冷淡ins风手机壳",
            "透明小熊手机壳",
            "北海道实验室波点怪兽手机壳"
    };
    // 声明一个手机商品的价格数组
    private static float[] mPriceArray = {39, 48, 39, 10, 32,24, 19,35};
    // 声明一个手机商品的小图数组
    private static int[] mThumbArray = {
            R.drawable.oo, R.drawable.tt, R.drawable.thth,
            R.drawable.ff,R.drawable.fifi, R.drawable.ss,
            R.drawable.sese, R.drawable.ee
    };
    // 声明一个手机商品的大图数组
    private static int[] mPicArray = {
            R.drawable.o, R.drawable.t, R.drawable.th,
            R.drawable.f, R.drawable.fi, R.drawable.s,
            R.drawable.se, R.drawable.e
    };

    // 获取默认的手机信息列表
    public static ArrayList<GoodsInfo> getDefaultList() {
        ArrayList<GoodsInfo> goodsList = new ArrayList<GoodsInfo>();
        for (int i = 0; i < mNameArray.length; i++) {
            GoodsInfo info = new GoodsInfo();
            info.name = mNameArray[i];
            info.desc = mDescArray[i];
            info.price = mPriceArray[i];
            info.thumb = mThumbArray[i];
            info.pic = mPicArray[i];
            goodsList.add(info);
        }
        return goodsList;
    }
}