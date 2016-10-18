package com.sankuai.dsx.sxmeilishuo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.TabHost;

import com.sankuai.dsx.sxmeilishuo.R;
/**
 * Created by dsx on 16/10/18.
 */

public class TabBarActivity extends FragmentActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabchoose);

        TabHost tabhost = (TabHost) findViewById(R.id.mytabhost);

        tabhost.setup();
        tabhost.addTab(tabhost.newTabSpec("one").setIndicator("首页").setContent(R.id.widget_layout_home));
        tabhost.addTab(tabhost.newTabSpec("two").setIndicator("购物").setContent(R.id.widget_layout_shopping));
        tabhost.addTab(tabhost.newTabSpec("middle").setIndicator("相机").setContent(R.id.widget_layout_shopping));
        tabhost.addTab(tabhost.newTabSpec("three").setIndicator("消息").setContent(R.id.widget_layout_message));
        tabhost.addTab(tabhost.newTabSpec("four").setIndicator("我的").setContent(R.id.widget_layout_myinfo));

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransactionHome = fragmentManager.beginTransaction();
        FragmentTransaction fragmentTransactionShopping = fragmentManager.beginTransaction();
        FragmentTransaction fragmentTransactionMessage = fragmentManager.beginTransaction();
        FragmentTransaction fragmentTransactionMyInfo = fragmentManager.beginTransaction();

        HomeFragment fragmentHome = new HomeFragment();
        fragmentTransactionHome.add(R.id.widget_layout_home, fragmentHome);
        fragmentTransactionHome.commit();

        ShoppingFragment fragmentShop = new ShoppingFragment();
        fragmentTransactionShopping.add(R.id.widget_layout_shopping, fragmentShop);
        fragmentTransactionShopping.commit();

        MessageFragment fragmentMsg = new MessageFragment();
        fragmentTransactionMessage.add(R.id.widget_layout_message, fragmentMsg);
        fragmentTransactionMessage.commit();

        MyInfoFragment fragmentMy = new MyInfoFragment();
        fragmentTransactionMyInfo.add(R.id.widget_layout_myinfo, fragmentMy);
        fragmentTransactionMyInfo.commit();

    }
}
