package com.sankuai.dsx.sxmeilishuo.shopping;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.util.AsyncListUtil;

import com.google.gson.Gson;
import com.sankuai.dsx.platform.Base.BaseDataCallBacks;
import com.sankuai.dsx.platform.Base.BaseLoaderCallBacks;
import com.sankuai.dsx.platform.Base.BaseModelImplements;
import com.sankuai.dsx.sxmeilishuo.bean.ShoppingContentResponse;
import com.sankuai.dsx.sxmeilishuo.bean.ShoppingHeaderResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * Created by dsx on 2016/11/30.
 */


public class ShoppingModel extends BaseModelImplements{

    public ShoppingModel(LoaderManager manager) {
        super(manager);
    }

    /**
     * 获取上部数据的网络请求
     */
    public void loadTopData(final Context context, final BaseDataCallBacks<ShoppingHeaderResponse> goData){
        startLoader(new BaseLoaderCallBacks<ShoppingHeaderResponse>() {
            @Override
            protected ShoppingHeaderResponse loadData(Bundle args) {
                StringBuilder sb = new StringBuilder();
                try {
                    InputStream is = context.getAssets().open("shopping_top_data.json");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                    String line = null;
                    while ((line = reader.readLine()) != null){
                        sb.append(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Gson gson = new Gson();
                return gson.fromJson(sb.toString(),ShoppingHeaderResponse.class);
            }

            @Override
            public void onLoadFinished(Loader<ShoppingHeaderResponse> loader, ShoppingHeaderResponse data) {
                destroyLoader(this);
                goData.onFinished(data);
            }
        });
    };


    /**
     * 获取下部数据的网络请求
     * @param position 坐标
     */
    public void loadSubData(int position){
        startLoader(new LoaderManager.LoaderCallbacks<ShoppingContentResponse>() {
            @Override
            public Loader<ShoppingContentResponse> onCreateLoader(int id, Bundle args) {
                return null;
            }

            @Override
            public void onLoadFinished(Loader<ShoppingContentResponse> loader, ShoppingContentResponse data) {

            }

            @Override
            public void onLoaderReset(Loader<ShoppingContentResponse> loader) {

            }
        });
    }
}
