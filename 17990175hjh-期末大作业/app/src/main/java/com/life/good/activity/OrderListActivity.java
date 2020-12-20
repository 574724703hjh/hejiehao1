package com.life.good.activity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;


import com.life.good.R;
import com.life.good.adapter.HistoryAdapter;
import com.life.good.base.BaseActivity;
import com.life.good.bean.FOrder;
import com.life.good.utils.SharedPreferencesUtils;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;


public class OrderListActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerview;
    private List<FOrder> list = new ArrayList<>();
    private HistoryAdapter adapter;
    private String type;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        type =  getIntent().getStringExtra("type");
        onSetTitle("订单");
        initView();
        getData();
    }

    private void initView() {
        swipeRefreshLayout = findViewById(R.id.swiperefreshlayout);
        recyclerview = findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        recyclerview.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        adapter = new HistoryAdapter(this,list);
        recyclerview.setAdapter(adapter);
        swipeRefreshLayout.setOnRefreshListener(this);

    }

    @Override
    public void onRefresh() {
        getData();
    }
    private void getData() {
        if (!TextUtils.isEmpty(type)) {
            List<FOrder> all = DataSupport.findAll(FOrder.class);
            list.addAll(all);
        }else{
            int id = SharedPreferencesUtils.getInt(OrderListActivity.this, "id", 0);
            List<FOrder> all = DataSupport.where("user=?",""+id).find(FOrder.class);
            list.addAll(all);
        }
        adapter.notifyDataSetChanged();
    }
}
