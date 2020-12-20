package com.life.good.activity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.life.good.R;
import com.life.good.base.BaseActivity;
import com.life.good.bean.FOrder;
import com.life.good.utils.SharedPreferencesUtils;
import com.life.good.utils.Utils;


public class OrderDetailActivity extends BaseActivity {

    private EditText et_address,et_phone;
    private EditText et_name;
    private int id;
    private FOrder mFOrder;
    private View mBtnPay;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        id = SharedPreferencesUtils.getInt(OrderDetailActivity.this, "id", 0);
        mFOrder = (FOrder) getIntent().getSerializableExtra("order");
        mFOrder.setUser(id+"");
        onSetTitle("生成订单");
        initViews();

    }





    private void initViews() {
        et_name = (EditText)findViewById(R.id.et_name);
        et_phone = (EditText)findViewById(R.id.et_phone);
        et_address = (EditText)findViewById(R.id.et_address);
        mBtnPay = findViewById(R.id.id_btn_pay);
        mBtnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = et_name.getText().toString().trim();
                String phone = et_phone.getText().toString().trim();
                String address = et_address.getText().toString().trim();
                if (TextUtils.isEmpty(name)){
                    onToast("请输入收货人姓名");
                    return;
                }
                if (TextUtils.isEmpty(phone)){

                    return;
                }
                if (!Utils.isPhoneNumberValid(phone)){
                    onToast("请填写正确的手机号码");
                    return;
                }
                if (TextUtils.isEmpty(address)){
                    onToast("请输入收货人地址");
                    return;
                }
                mFOrder.setAddress(address);
                mFOrder.setPhone(phone);
                mFOrder.setName(name);
                mFOrder.save();
                onToast("下单成功");
                finish();
            }
        });

    }




}
