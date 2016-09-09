package com.mobileapplicationsclass.pykandlxm.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;


/**
 * Created by Administrator on 2016/9/4 0004.
 */
public abstract class BaseFragment extends Fragment {
    /**
     * 贴附的activity
     */
    protected FragmentActivity mActivity;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(setLayoutResouceId(), container, false);
        //初始化黄油刀控件绑定框架
        ButterKnife.bind(this, view);
        //初始化控件
        initView(view, savedInstanceState);
        return view;
    }


    public abstract int setLayoutResouceId();

    protected abstract void initView(View view, Bundle savedInstanceState);


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mActivity = getActivity();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

