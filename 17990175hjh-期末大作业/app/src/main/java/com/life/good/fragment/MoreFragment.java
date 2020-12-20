package com.life.good.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.life.good.R;
import com.life.good.activity.LoginActivity;
import com.life.good.activity.OrderListActivity;
import com.life.good.activity.PersonActivity;
import com.life.good.db.User;
import com.life.good.db.UserDB;
import com.life.good.utils.SharedPreferencesUtils;
import com.life.good.utils.StatusBarUtil;


public class MoreFragment extends Fragment implements View.OnClickListener {


    TextView mUserName;
    ImageView mCircularHeadimg;

    public static MoreFragment newInstance() {
        MoreFragment fragment = new MoreFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_setting, null);
        StatusBarUtil.setMargin(getActivity(), view.findViewById(R.id.at_toolbar));
        initView(view);

        return view;

    }

    private void initView(View view) {
        mUserName= view.findViewById(R.id.user_name);
        mCircularHeadimg= view.findViewById(R.id.circular_headimg);
        LinearLayout rlMe = view.findViewById(R.id.rl_me);
        TextView tv_logout = view.findViewById(R.id.tv_logout);
        LinearLayout ll_order = view.findViewById(R.id.ll_order);
        rlMe.setOnClickListener(this);
        ll_order.setOnClickListener(this);
        tv_logout.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        int id = SharedPreferencesUtils.getInt(getActivity(), "id", 0);
        //根据用户id获取用户信息
        User user = UserDB.getInstance(getActivity()).loadUser(id);
        mUserName.setText(user.getUsername());

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_order:
                startActivity(new Intent(getActivity(), OrderListActivity.class));
                break;
            case R.id.rl_me:
                Intent mIntent = new Intent();
                mIntent.setClass(getActivity(), PersonActivity.class);
                startActivity(mIntent);
                break;

            case R.id.tv_logout:
                new AlertDialog.Builder(getActivity())
                        .setTitle("提示")
                        .setMessage("确定退出吗?")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                startActivity(new Intent(getActivity(), LoginActivity.class));
                                SharedPreferencesUtils.saveInt(getActivity(), "id", -1);
                                getActivity().finish();
                                dialogInterface.dismiss();
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();

                break;
        }
    }


}
