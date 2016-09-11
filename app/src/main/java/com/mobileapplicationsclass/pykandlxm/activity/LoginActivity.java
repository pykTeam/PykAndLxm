package com.mobileapplicationsclass.pykandlxm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.mobileapplicationsclass.pykandlxm.R;
import com.mobileapplicationsclass.pykandlxm.base.BaseActivity;
import com.mobileapplicationsclass.pykandlxm.model.StarGrid;
import com.mobileapplicationsclass.pykandlxm.utils.CommonUtils;
import com.mobileapplicationsclass.pykandlxm.utils.ToastUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/9/6 0006.
 */
public class LoginActivity extends BaseActivity{
    @Bind(R.id.tb_toolbar)
    Toolbar mToolbar;
    @Bind(R.id.et_username)
    EditText et_username;
    @Bind(R.id.et_password)
    EditText et_password;
    @Bind(R.id.iv_icon_left)
    ImageView ivIconLeft;
    @Bind(R.id.iv_icon_right)
    ImageView ivIconRight;
    @Bind(R.id.btn_login)
    Button btnLogin;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

        et_username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                ivIconLeft.setBackgroundResource(R.mipmap.ic_22);
                ivIconRight.setBackgroundResource(R.mipmap.ic_33);
            }
        });

        et_password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                ivIconLeft.setBackgroundResource(R.mipmap.ic_22_hide);
                ivIconRight.setBackgroundResource(R.mipmap.ic_33_hide);
            }
        });

    }

    @Override
    public void initToolBar() {
//        mToolbar.setTitle("星座运势");
        setSupportActionBar(mToolbar);
        //开启ActionBar上App icon功能
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @OnClick(R.id.btn_login)
    public void onClick() {
        boolean isNetConnected = CommonUtils.isNetworkAvailable(this);
        if (!isNetConnected) {
            ToastUtil.showToast(this, "当前网络不可用,请检查网络设置");
            return;
        }
        login();
    }

    private void login() {

        String name = et_username.getText().toString();
        String password = et_password.getText().toString();

        if (TextUtils.isEmpty(name)) {
            ToastUtil.showToast(this, "用户名不能为空");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            ToastUtil.showToast(this, "密码不能为空");
            return;
        }
        startActivity(new Intent(LoginActivity.this,MainActivity.class));
//        overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
        finish();
    }
}
