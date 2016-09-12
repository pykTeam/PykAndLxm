package com.mobileapplicationsclass.pykandlxm.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.mobileapplicationsclass.pykandlxm.R;
import com.mobileapplicationsclass.pykandlxm.base.BaseActivity;
import com.mobileapplicationsclass.pykandlxm.model.StarModel;
import com.mobileapplicationsclass.pykandlxm.retrofit.StarServerInterface;
import com.mobileapplicationsclass.pykandlxm.utils.ToastUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/9/9 0009.
 */
public class StarActivity extends BaseActivity {

    private static final String TAG = "StarActivity";
    private String name;
    private Call<StarModel> call_text = null;

    @Bind(R.id.re_pb)
    ProgressBar rePb;
    @Bind(R.id.re_btn)
    Button reBtn;
    @Bind(R.id.re_rl_pb)
    RelativeLayout reRlPb;
    @Bind(R.id.sv)
    ScrollView sv;
    @Bind(R.id.tb_toolbar)
    Toolbar mToolbar;

    //详情页文本
    @Bind(R.id.tv_data)
    TextView tvData;
    @Bind(R.id.tv_starname)
    TextView tvStarname;
    @Bind(R.id.rl_pb)
    RelativeLayout rlPb;
    @Bind(R.id.tv_data2)
    TextView tvData2;
    @Bind(R.id.tv_all)
    TextView tvAll;
    @Bind(R.id.tv_color)
    TextView tvColor;
    @Bind(R.id.tv_health)
    TextView tvHealth;
    @Bind(R.id.tv_love)
    TextView tvLove;
    @Bind(R.id.tv_money)
    TextView tvMoney;
    @Bind(R.id.tv_number)
    TextView tvNumber;
    @Bind(R.id.tv_qfriend)
    TextView tvQfriend;
    @Bind(R.id.tv_summary)
    TextView tvSummary;
    @Bind(R.id.tv_work)
    TextView tvWork;

    @Override
    public int getLayoutId() {
        return R.layout.activity_star;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("starname");
        retrofit(name, "today");


//  延时操作：
//        Timer timer=new Timer();//实例化Timer类
//        timer.schedule(new TimerTask(){
//            public void run(){
//                this.cancel();}},1500);//五百毫秒

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(3000);
//                    Log.d(TAG, "" + Thread.currentThread().getId());
//                    retrofit(name, "today");
//
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();

    }

    @Override
    public void initToolBar() {
        mToolbar.setTitle(name);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void retrofit(String consName, String type) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://web.juhe.cn:8080/").addConverterFactory(GsonConverterFactory.create()).build();
        StarServerInterface starServerInterface = retrofit.create(StarServerInterface.class);
        Map<String, String> map = new HashMap<>();
        map.put("consName", consName);
        map.put("type", type);
        map.put("key", "fe7f73e0ae8b50de07507ce4769ab67e");
        call_text = starServerInterface.getResult(map);
        call_text.enqueue(new Callback<StarModel>() {
            @Override
            public void onResponse(Call<StarModel> call, Response<StarModel> response) {
                if (response.isSuccess() && response.body() != null) {
                    StarModel starModel = response.body();
                    if (starModel.getResultcode().equals("200")) {
                        rlPb.setVisibility(View.GONE);
                        reRlPb.setVisibility(View.GONE);
                        sv.setVisibility(View.VISIBLE);
                        tvData.setText("" + starModel.getDate());
                        tvStarname.setText(starModel.getName());
                        tvData2.setText(starModel.getDatetime());
                        tvAll.setText(starModel.getAll());
                        tvColor.setText(starModel.getColor());
                        tvHealth.setText(starModel.getHealth());
                        tvLove.setText(starModel.getLove());
                        tvMoney.setText(starModel.getMoney());
                        tvNumber.setText(""+starModel.getNumber());
                        tvQfriend.setText(starModel.getQFriend());
                        tvSummary.setText(starModel.getSummary());
                        tvWork.setText(starModel.getWork());
                    } else {
                        Log.d(TAG, "错误码为：" + starModel.getError_code());
                    }
                }
            }

            @Override
            public void onFailure(Call<StarModel> call, Throwable t) {
                rlPb.setVisibility(View.GONE);
                reRlPb.setVisibility(View.VISIBLE);
                reBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        rePb.setVisibility(View.VISIBLE);
                        retrofit(name, "today");
//                        boolean isNetConnected = CommonUtils.isNetworkAvailable(this);
//                        if (!isNetConnected) {
//                            ToastUtil.showToast(this, "您的手机网络不太顺畅哦");
//                            return;
//                        }
                    }
                });
//                reRlPb.setVisibility(View.GONE);
                ToastUtil.showToast(StarActivity.this, "您的手机网络不太顺畅哦");
            }
        });
    }

}
