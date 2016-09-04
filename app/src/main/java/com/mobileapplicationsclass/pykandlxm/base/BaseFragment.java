package com.mobileapplicationsclass.pykandlxm.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/9/4 0004.
 */
public abstract class BaseFragment extends Fragment{

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(setLayoutResouceId(), container, false);
        //初始化控件
        initViews(savedInstanceState);
        return view;
    }

    public abstract int setLayoutResouceId();

    public abstract void initViews(Bundle savedInstanceState);
}

