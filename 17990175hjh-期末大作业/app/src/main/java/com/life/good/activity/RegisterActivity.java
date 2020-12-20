package com.life.good.activity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.life.good.R;
import com.life.good.base.BaseActivity;
import com.life.good.db.User;
import com.life.good.db.UserDB;


public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private EditText etName;
    private EditText etPwd;
    private Button btLogin;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        onSetTitle("注册");
        init();
    }

    private void init() {
        etName = (EditText)findViewById(R.id.et_write_name);
        etPwd = (EditText)findViewById(R.id.et_write_pwd);
        btLogin = (Button)findViewById(R.id.btn_login);
        btLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                String name=etName.getText().toString().trim();
                String pass=etPwd.getText().toString().trim();
                if (TextUtils.isEmpty(name)){
                    onToast("用户名不能为空");
                    return;
                }
                if (TextUtils.isEmpty(pass)){
                    onToast("密码不能为空");
                    return;
                }
                User user=new User();
                user.setUsername(name);
                user.setUserpwd(pass);
                int result= UserDB.getInstance(getApplicationContext()).registerUser(user);
                if (result==1){
                    Toast.makeText(RegisterActivity.this,"注册成功！",Toast.LENGTH_SHORT).show();
                    finish();
                }else  if (result==-1)
                {
                    Toast.makeText(RegisterActivity.this,"用户名已经存在！！",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(RegisterActivity.this,"注册失败！",Toast.LENGTH_SHORT).show();
                }

                break;

        }
    }
}
