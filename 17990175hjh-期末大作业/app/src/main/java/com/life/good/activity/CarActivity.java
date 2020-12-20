package com.life.good.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.life.good.R;
import com.life.good.adapter.CarAdapter;
import com.life.good.db.Car;
import com.life.good.bean.FOrder;
import com.life.good.db.CarDao;

import java.util.ArrayList;
import java.util.List;



public class CarActivity extends AppCompatActivity {
    private List<Car> mDatas = new ArrayList<>();
    private CarAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private TextView mTvCount;
    private Button mBtnPay;
    private int mTotalCount;
    private int mTotalPrice;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);
        initView();
        init();
    }
    private void initView() {
        findViewById(R.id.iv_finish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mRecyclerView =  findViewById(R.id.id_recyclerview);
        mTvCount =  findViewById(R.id.id_tv_count);
        mBtnPay =  findViewById(R.id.id_btn_pay);
        //设置颜色
        mAdapter = new CarAdapter(this, mDatas);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnFoodListener(new CarAdapter.OnFoodListener() {


            @Override
            public void onFoodAdd(int position) {
                Car data = mDatas.get(position);
                data.setGoodsNum(data.getGoodsNum()+1);
                CarDao.getInstance(CarActivity.this).update(data);
                init();

            }

            @Override
            public void onFoodSub(int position) {
                Car data = mDatas.get(position);
                if (data.getGoodsNum()-1>=0){
                    data.setGoodsNum(data.getGoodsNum()-1);
                    CarDao.getInstance(CarActivity.this).update(data);
                    init();
                }
            }

            @Override
            public void onFoodDel(int position) {
                Car data = mDatas.get(position);
                CarDao.getInstance(CarActivity.this).delete(data);

                    init();
            }

            @Override
            public void onFoodCheck(int position,boolean ischeck) {
                Car data = mDatas.get(position);
                if (ischeck){
                    data.setChoosed("1");
                }else{
                    data.setChoosed("0");
                }
                CarDao.getInstance(CarActivity.this).update(data);
                init();
            }
        });
        mBtnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTotalCount == 0) {
                    Toast.makeText(CarActivity.this,"购物车为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer sb = new StringBuffer();
                for (Car food:mDatas){
                    if (food.getChoosed().equals("1")) {
                        CarDao.getInstance(CarActivity.this).delete(food);
                        sb.append(food.getGoodsname()+":"+food.getGoodsNum());
                        sb.append("\n");
                    }
                }


                FOrder mFOrder = new FOrder();
                mFOrder.setCount(mTotalCount);
                mFOrder.setPrice(mTotalPrice+"");
                mFOrder.setContent(sb.toString());
                Intent intent =   new Intent();
                intent.setClass(CarActivity.this,OrderDetailActivity.class);
                intent.putExtra("order", mFOrder);
                startActivity(intent);
                finish();
            }
        });
    }


    public void init() {
        mDatas.clear();
        List<Car> list = CarDao.getInstance(this).searchGoods();
        mDatas.addAll(list);
        mTotalPrice = 0;
        mTotalCount = 0;
        for (Car food:mDatas){
            if (food.getChoosed().equals("1")) {
                mTotalCount += food.getGoodsNum();
                mTotalPrice += food.getGoodsNum() * Float.parseFloat(food.getPrice());
            }
        }
        mTvCount.setText("总数量：" + mTotalCount);
        mBtnPay.setText(mTotalPrice + "元支付");
        mAdapter.notifyDataSetChanged();
    }



}
