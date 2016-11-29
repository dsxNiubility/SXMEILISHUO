package com.sankuai.dsx.sxmeilishuo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.sankuai.dsx.platform.SXUtils;
import com.sankuai.dsx.sxmeilishuo.bean.ShoppingContentResponse;
import com.sankuai.dsx.sxmeilishuo.bean.ShoppingInfoItem;
import com.sankuai.dsx.sxmeilishuo.root.TabBarActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<ShoppingInfoItem> mShoppingInfoItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("aa",String.valueOf(SXUtils.sum(5,5)));

        Intent intent = new Intent(MainActivity.this, TabBarActivity.class);
        startActivity(intent);
        finish();

        StringBuilder sb = new StringBuilder();
        try {
            InputStream is = getAssets().open("test.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while ((line = reader.readLine()) != null){
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gsonn = new Gson();
        ShoppingContentResponse response = gsonn.fromJson(sb.toString(),ShoppingContentResponse.class);
        mShoppingInfoItems = response.getData().getList();

        Log.d("aaa",sb.toString());
    }
}