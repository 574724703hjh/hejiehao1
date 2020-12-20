package com.life.good.activity;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.life.good.R;
import com.life.good.base.BaseActivity;
import com.life.good.db.User;
import com.life.good.db.UserDB;
import com.life.good.utils.SharedPreferencesUtils;
import com.life.good.utils.Utils;



/**
 * 修改个人信息的页面,邮箱,密码等,根据个人页面传过来不同的值修改不同内容
 */
public class ChangeActivity extends BaseActivity {
    TextView atTitle;
    Toolbar atToolbar;
    EditText etWritePwd;
    Button btnLogin;
    private ChangeActivity context;
    private String type;
    private int id;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        initView();
        context = this;
        //个人详情页面传过来的数据,不同type对应修改不同的内容
        type = getIntent().getStringExtra("type");
        init();
        //登录成功,获取用户id
        id = SharedPreferencesUtils.getInt(context, "id", 0);
        //根据用户id获取用户信息
        user = UserDB.getInstance(ChangeActivity.this).loadUser(id);
    }

    private void initView() {
        atTitle = (TextView) findViewById(R.id.at_title);
        atToolbar = (Toolbar) findViewById(R.id.at_toolbar);
        etWritePwd = (EditText) findViewById(R.id.et_write_pwd);
        btnLogin = (Button) findViewById(R.id.btn_login);
        //点击修改按钮事件
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pwd = etWritePwd.getText().toString().trim();
                //type如果是password那么就是修改密码
                if (type.equals("password")) {
                    if (TextUtils.isEmpty(pwd)) {
                        Toast.makeText(context, "新密码密码不能为空", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (pwd.length() < 6 || pwd.length() > 8) {
                        Toast.makeText(context, "密码长度应为6到8位", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    user.setUserpwd(pwd);
                    //更新数据库
                    UserDB.getInstance(ChangeActivity.this).updataUser(user);
                    Toast.makeText(context, "修改成功", Toast.LENGTH_SHORT).show();

                    //退出页面
                    finish();
                }
                if (type.equals("phone")){
                    if (TextUtils.isEmpty(pwd)) {
                        Toast.makeText(context, "新的手机号不能为空", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (!Utils.isPhoneNumberValid(pwd)){
                        onToast("请填写正确的手机号码");
                        return;
                    }
                    user.setMobile(pwd);
                    UserDB.getInstance(ChangeActivity.this).updataUser(user);
                    Toast.makeText(context, "修改成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
                if (type.equals("email")){
                    if (TextUtils.isEmpty(pwd)) {
                        Toast.makeText(context, "邮箱不能为空", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (!Utils.isMailboxValid(pwd)){
                        onToast("请输入正确的邮箱");
                        return;
                    }
                    user.setEmail(pwd);
                    UserDB.getInstance(ChangeActivity.this).updataUser(user);
                    Toast.makeText(context, "修改成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
                if (type.equals("des")){
                    if (TextUtils.isEmpty(pwd)) {
                        Toast.makeText(context, "个性签名不能为空", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    user.setGxqm(pwd);
                    UserDB.getInstance(ChangeActivity.this).updataUser(user);
                    Toast.makeText(context, "修改成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
                if (type.equals("age")){
                    if (TextUtils.isEmpty(pwd)) {
                        Toast.makeText(context, "年龄不能为空", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    user.setAge(pwd);
                    UserDB.getInstance(ChangeActivity.this).updataUser(user);
                    Toast.makeText(context, "修改成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    /**
     * 根据不同的type,修改不同的title和下面的提示文字
     */
    private void init() {
        switch (type){
            case "phone":
                onSetTitle("修改手机号");
                etWritePwd.setHint("请输入手机号");
                break;
            case "email":
                onSetTitle("修改邮箱");
                etWritePwd.setHint("请输入邮箱");
                break;
            case "password":
                onSetTitle("修改密码");
                etWritePwd.setHint("请输入新的密码");
                break;
            case "des":
                onSetTitle("修改个性签名");
                etWritePwd.setHint("请输入个性签名");
                break;
            case "age":
                onSetTitle("修改年龄");
                etWritePwd.setHint("请输入你的年龄");
                break;
        }
    }




}
