package com.life.good.activity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;

import com.life.good.R;
import com.life.good.bean.Good;
import com.life.good.fragment.HomeFragment;
import com.life.good.fragment.MoreFragment;
import com.life.good.utils.StatusBarUtil;

import org.litepal.crud.DataSupport;

import java.lang.reflect.Method;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout layout_first;
    LinearLayout layout_five;
    private LinearLayout[] mLayouts;
    private static final String KEY_FRAGMENT_TAG = "fragment_tag";
    private static final String FRAGMENT_FIRST = "fragment_first";
    private static final String FRAGMENT_FIVE = "fragment_five";
    private String[] mFragmentTags = new String[]{ FRAGMENT_FIRST,FRAGMENT_FIVE};
    private String mFragmentCurrentTag = FRAGMENT_FIRST;
    private HomeFragment mProductFragment;
    private MoreFragment mMoreFragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            restoreFragments();
            mFragmentCurrentTag = savedInstanceState.getString(KEY_FRAGMENT_TAG);
        }
        setContentView(R.layout.activity_home);
        StatusBarUtil.darkMode(this);
        initView();
        initData();
        setListener();
        initTestData();
    }
    private void initTestData() {
        List<Good> list = DataSupport.findAll(Good.class);
        if (list.size()<=0){
            new Good("德宝", 100, "", "debao").save();
            new Good("城市", 60, "", "city").save();
            new Good("法老", 99, "", "falao").save();
            new Good("特工", 995, "", "falao").save();
            new Good("大电影", 660, "", "falao").save();
            new Good("英雄工厂", 220, "", "falao").save();
            new Good("生化战du士", 365, "", "falao").save();
            new Good("气功传奇", 368, "", "falao").save();
            new Good("超级英雄", 299, "", "falao").save();
            new Good("玩具总动员", 188, "", "falao").save();
            new Good("波斯", 188, "", "falao").save();
        }

    }

    private void initView() {
        layout_first = findViewById(R.id.layout_first);
        layout_five = findViewById(R.id.layout_five);

    }

    private void initData() {
        mLayouts = new LinearLayout[]{ layout_first,layout_five};
    }
    private void setListener() {
        for (int i = 0; i < mLayouts.length; i++) {
            mLayouts[i].setOnClickListener(this);
        }

       if (TextUtils.equals(FRAGMENT_FIRST, mFragmentCurrentTag)) {
           layout_first.performClick();
        }else if (TextUtils.equals(FRAGMENT_FIVE, mFragmentCurrentTag)) {
           layout_five.performClick();
       }

    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(KEY_FRAGMENT_TAG, mFragmentCurrentTag);
        super.onSaveInstanceState(outState);
    }
    @Override
    public void onClick(View v) {
        onTabSelect((LinearLayout) v);
    }
    /**
     * 切换tab页
     * @param itemLayout
     */
    public void onTabSelect(LinearLayout itemLayout) {
        int id = itemLayout.getId();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        hideFragments(manager, transaction);

        for (int i = 0; i < mLayouts.length; i++) {
            mLayouts[i].setSelected(false);
        }
        itemLayout.setSelected(true);

      if (id == R.id.layout_first) {
            selectedFragment(transaction, mProductFragment, HomeFragment.class, FRAGMENT_FIRST);
      }  else if (id == R.id.layout_five) {
          selectedFragment(transaction, mMoreFragment, MoreFragment.class, FRAGMENT_FIVE);
      }
        transaction.commit();
    }
    private void selectedFragment(FragmentTransaction transaction, Fragment fragment, Class<?> clazz, String tag) {
        mFragmentCurrentTag = tag;
        if (fragment == null) {
            try {
                Method newInstanceMethod = clazz.getDeclaredMethod("newInstance");
                fragment = (Fragment) newInstanceMethod.invoke(null);
                if (fragment instanceof HomeFragment) {
                    mProductFragment = (HomeFragment)fragment;
                }else if (fragment instanceof MoreFragment) {
                    mMoreFragment = (MoreFragment)fragment;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            transaction.add(R.id.fragment_container, fragment, tag);
        }
        transaction.show(fragment);
    }
    /**
     * 先全部隐藏
     * @param fragmentManager
     * @param transaction
     */
    private void hideFragments(FragmentManager fragmentManager, FragmentTransaction transaction) {
        for (int i = 0; i < mFragmentTags.length; i++) {
            Fragment fragment = fragmentManager.findFragmentByTag(mFragmentTags[i]);
            if (fragment != null && fragment.isVisible()) {
                transaction.hide(fragment);
            }
        }
    }
    /**
     * 恢复fragment
     */
    private void restoreFragments() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        for (int i = 0; i < mFragmentTags.length; i++) {
            Fragment fragment = manager.findFragmentByTag(mFragmentTags[i]);
            if (fragment instanceof HomeFragment) {
                mProductFragment = (HomeFragment)fragment;
            }else if (fragment instanceof MoreFragment) {
                mMoreFragment = (MoreFragment)fragment;
            }
            transaction.hide(fragment);
        }
        transaction.commit();
    }
}
