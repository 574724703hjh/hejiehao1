package com.life.good.activity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.life.good.R;
import com.life.good.bean.Good;
import com.life.good.db.CarDao;
import com.life.good.utils.SharedPreferencesUtils;
import com.life.good.utils.Utils;



public class GoodsDetail extends AppCompatActivity {
    private ImageView ivImg;
    private TextView tvTitle;
    private TextView tvDes;
    private TextView tvPrice;
    private ImageView mIvAdd;
    private ImageView mIvSub;
    private TextView tvCount;
    private Button btAdd;
    private Good goods;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initView();
        goods = (Good) getIntent().getSerializableExtra("goods");

        ivImg.setImageDrawable(Utils.getDrawable(this,goods.getImg()));
        tvTitle.setText(goods.getName());
        tvDes.setText(goods.getDes());
        tvPrice.setText(goods.getPrice()+"");

    }

    private void initView() {
        ivImg = findViewById(R.id.iv_img);
        findViewById(R.id.iv_finish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvTitle = findViewById(R.id.id_tv_name);
        tvPrice = findViewById(R.id.id_tv_price);
        tvDes = findViewById(R.id.id_tv_label);
        tvCount = findViewById(R.id.id_tv_count);
        btAdd = findViewById(R.id.id_btn_pay);
        mIvAdd = (ImageView) findViewById(R.id.id_iv_add);
        mIvSub = (ImageView) findViewById(R.id.id_iv_sub);
        mIvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String count = tvCount.getText().toString();
                tvCount.setText((Integer.parseInt(count)+1)+"");
                goods.setCount(Integer.parseInt(count)+1);
            }
        });
        mIvSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String count = tvCount.getText().toString();
                int num = Integer.parseInt(count);
                if (num>1){
                    tvCount.setText((num-1)+"");
                    goods.setCount(num-1);
                }
            }
        });
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String count = tvCount.getText().toString();
                int num = Integer.parseInt(count);
                int id = SharedPreferencesUtils.getInt(GoodsDetail.this, "id", 0);
                CarDao.getInstance(GoodsDetail.this).insert(id+"",  goods,num);
                Toast.makeText(GoodsDetail.this,goods.getName()+"成功添加至购物车",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
