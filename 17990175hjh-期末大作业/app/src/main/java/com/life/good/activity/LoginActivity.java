package com.life.good.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.life.good.R;
import com.life.good.db.UserDB;
import com.life.good.utils.SharedPreferencesUtils;
import com.life.good.utils.StatusBarUtil;



public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etName;
    private EditText etPwd;
    private TextView btLogin;
    private TextView tv_register;
    private CheckBox ckSelect;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        StatusBarUtil.immersive(this);
        init();
    }



    private void init() {
        etName = (EditText)findViewById(R.id.et_name);
        etPwd = (EditText)findViewById(R.id.et_pwd);
        btLogin = (TextView)findViewById(R.id.bt_login);
        tv_register = (TextView)findViewById(R.id.tv_register);
        ckSelect = findViewById(R.id.ck_select);
        btLogin.setOnClickListener(this);
        tv_register.setOnClickListener(this);
        int select = SharedPreferencesUtils.getInt(LoginActivity.this, "select", 0);
        if (select==1){
            ckSelect.setSelected(true);
            String name = SharedPreferencesUtils.getString(LoginActivity.this, "name", "");
            String password = SharedPreferencesUtils.getString(LoginActivity.this, "pwd", "");
            etName.setText(name);
            etPwd.setText(password);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_login:
                String name=etName.getText().toString().trim();
                String pass=etPwd.getText().toString().trim();

                int result= UserDB.getInstance(getApplicationContext()).login(pass,name);
                if (result==-2){
                    toast("用户名不存在");
                }else if(result==-1){
                    toast("密码错误");
                }else{
                    if (ckSelect.isChecked()){
                        SharedPreferencesUtils.saveInt(LoginActivity.this,"select",1);
                        SharedPreferencesUtils.saveString(LoginActivity.this,"name",name);
                        SharedPreferencesUtils.saveString(LoginActivity.this,"pwd",pass);
                    }else{
                        SharedPreferencesUtils.saveInt(LoginActivity.this,"select",0);
                    }
                    SharedPreferencesUtils.saveInt(LoginActivity.this,"id",result);
                    Toast.makeText(LoginActivity.this,"登录成功！",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    finish();
                }
                break;
            case R.id.tv_register:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;
        }
    }
    private  void toast(String msg){
        new AlertDialog.Builder(LoginActivity.this)
                .setTitle("提示")
                .setMessage(msg)
                .setPositiveButton("我知道了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).create().show();
    }
}
