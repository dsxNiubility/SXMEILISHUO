package com.sankuai.dsx.sxmeilishuo;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dsx on 16/10/18.
 */

public class HomeFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onPause()
    {
        super.onPause();
        System.out.println("HomeFragment--onPause");
    }

    @Override
    public void onResume()
    {
        super.onResume();
        System.out.println("HomeFragment--onResume");
    }

    @Override
    public void onStop()
    {
        super.onStop();
        System.out.println("HomeFragment--onStop");
    }
}
