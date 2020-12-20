package com.life.good.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.life.good.R;
import com.life.good.activity.CarActivity;
import com.life.good.activity.GoodsDetail;
import com.life.good.adapter.GoodsAdapter;
import com.life.good.adapter.ImageAdapter;
import com.life.good.bean.Good;
import com.life.good.utils.StatusBarUtil;
import com.youth.banner.Banner;
import com.youth.banner.indicator.RectangleIndicator;
import com.youth.banner.util.BannerUtils;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment  {
    private ArrayList<Good> contentList = new ArrayList();
    private GoodsAdapter adapter;
    private SwipeRefreshLayout swiperefreshlayout;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        StatusBarUtil.setMargin(getActivity(), view.findViewById(R.id.at_toolbar));
        initView(view);
        initCategory();
        return view;
    }


    private void initCategory() {
        List<Good> all = DataSupport.findAll(Good.class);
        contentList.clear();
        contentList.addAll(all);
        adapter.notifyDataSetChanged();
    }
    private void initView(View view) {
        view.findViewById(R.id.iv_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getActivity(),CarActivity.class);
                startActivity(intent);
            }
        });
        swiperefreshlayout = view.findViewById(R.id.swiperefreshlayout);
        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initCategory();
                swiperefreshlayout.setRefreshing(false);
            }
        });
        Banner banner = view.findViewById(R.id.banner);
        ArrayList<String> bannerList = new ArrayList<>();
        bannerList.add("banner1");
        bannerList.add("banner2");
        bannerList.add("banner3");
        ImageAdapter imageAdapter = new ImageAdapter(getActivity(),bannerList);
        banner.setAdapter(imageAdapter);
        banner.setIndicator(new RectangleIndicator(getActivity()));
        banner.setIndicatorNormalWidth((int) BannerUtils.dp2px(12));
        banner.setIndicatorSpace((int) BannerUtils.dp2px(4));
        banner.setIndicatorRadius(0);
        RecyclerView mSecondCategoryRv = view.findViewById(R.id.mSecondCategoryRv);
         adapter = new GoodsAdapter(getActivity(), contentList);
        mSecondCategoryRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mSecondCategoryRv.setAdapter(adapter);

        adapter.setOnItemClickListener(new GoodsAdapter.ItemClickListener() {
            @Override
            public void setOnItemClickListener(int position) {
                Intent intent =  new Intent(getActivity(), GoodsDetail.class);
                intent.putExtra("goods",contentList.get(position));
                startActivity(intent);
            }



        });
    }

}
