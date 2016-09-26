package com.mobileapplicationsclass.pykandlxm.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mobileapplicationsclass.pykandlxm.R;
import com.mobileapplicationsclass.pykandlxm.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/14 0014.
 */
public class WebActivity extends BaseActivity {
    @Bind(R.id.wb)
    WebView wb;
    @Bind(R.id.tb_toolbar)
    Toolbar tbToolbar;

    private String webUrl;

    @Override
    public int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        webUrl = bundle.getString("weburl");

        initWeb();
    }

    @Override
    public void initToolBar() {
        tbToolbar.setTitle("精选");
        setSupportActionBar(tbToolbar);
        //开启ActionBar上App icon功能
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tbToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initWeb() {
        wb.loadUrl(webUrl);
        wb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
        //启用支持javascript
        WebSettings settings = wb.getSettings();
        settings.setJavaScriptEnabled(true);
    }

    @Override
    protected void onPause() {
        wb.reload();
        super.onPause();
    }
}
