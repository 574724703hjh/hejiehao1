package com.life.good.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.life.good.R;
import com.life.good.base.BaseActivity;
import com.life.good.db.User;
import com.life.good.db.UserDB;
import com.life.good.utils.SharedPreferencesUtils;
import com.life.good.utils.StatusBarUtil;



/**
 * 个人信息页面
 */
public class PersonActivity extends BaseActivity implements View.OnClickListener {
    private PersonActivity context;
    private TextView tv_phone;
    private TextView tv_email;
    private TextView tv_des;
    private TextView tv_age;
    private TextView tv_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_person_info);
        onSetTitle("个人信息");
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //登录成功,获取用户id
        int id = SharedPreferencesUtils.getInt(context, "id", 0);
        //根据用户id获取用户信息
        User user = UserDB.getInstance(PersonActivity.this).loadUser(id);
        //初始化姓名
        tv_name.setText(user.getUsername());
        //获取用户手机号
        String phone = user.getMobile();
        //判断是否为空
        if (null!=phone){
           tv_phone.setText(phone);
        }else{
            tv_phone.setText("未设置");
        }
        //获取用户email
        String email = user.getEmail();
        //判断是否为空
        if (null!=email){
            tv_email.setText(email);
        }else{
            tv_email.setText("未设置");
        }
        String gxqm = user.getGxqm();
        if (null!=email){
            tv_des.setText("个性签名: "+gxqm);
        }else{
            tv_des.setText("未设置");
        }
        String age = user.getAge();
        if (null!=email){
            tv_age.setText(age);
        }else{
            tv_age.setText("未设置");
        }


    }
    private void initView() {
        tv_phone =  findViewById(R.id.tv_phone);
        tv_name =  findViewById(R.id.tv_name);
        tv_des =  findViewById(R.id.tv_des);
        tv_age =  findViewById(R.id.tv_age);
        tv_email =  findViewById(R.id.tv_email);
        RelativeLayout rl_email =  findViewById(R.id.rl_email);
        RelativeLayout rl_age =  findViewById(R.id.rl_age);
        RelativeLayout rl_pwd =  findViewById(R.id.rl_pwd);
        rl_email.setOnClickListener(this);
        rl_age.setOnClickListener(this);
        rl_pwd.setOnClickListener(this);
        tv_des.setOnClickListener(this);
        RelativeLayout rl_phone =  findViewById(R.id.rl_phone);
        rl_phone.setOnClickListener(this);


    }



    /**
     * 点击不同的控件跳转到修改信息页面,传不同的type过去
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.rl_phone:
                Intent PIntent = new Intent();
                PIntent.setClass(context,ChangeActivity.class);
                PIntent.putExtra("type","phone");
                startActivity(PIntent);
                break;
            case R.id.rl_email:
                Intent eIntent = new Intent();
                eIntent.setClass(context,ChangeActivity.class);
                eIntent.putExtra("type","email");
                startActivity(eIntent);
                break;
            case R.id.rl_pwd:
                Intent passWordIntent = new Intent();
                passWordIntent.setClass(context,ChangeActivity.class);
                passWordIntent.putExtra("type","password");
                startActivity(passWordIntent);
                break;
            case R.id.tv_des:
                Intent DesIntent = new Intent();
                DesIntent.setClass(context,ChangeActivity.class);
                DesIntent.putExtra("type","des");
                startActivity(DesIntent);
                break;
            case R.id.rl_age:
                Intent AgeIntent = new Intent();
                AgeIntent.setClass(context,ChangeActivity.class);
                AgeIntent.putExtra("type","age");
                startActivity(AgeIntent);
                break;


        }
    }
}
