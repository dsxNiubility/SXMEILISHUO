package com.sankuai.dsx.sxmeilishuo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.sankuai.dsx.platform.SXUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("aa",String.valueOf(SXUtils.sum(5,5)));

        Intent intent = new Intent(MainActivity.this, TabBarActivity.class);
        startActivity(intent);
        finish();
    }
}