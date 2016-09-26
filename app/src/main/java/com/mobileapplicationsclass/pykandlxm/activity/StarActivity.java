package com.mobileapplicationsclass.pykandlxm.activity;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mobileapplicationsclass.pykandlxm.R;
import com.mobileapplicationsclass.pykandlxm.api.Constant;
import com.mobileapplicationsclass.pykandlxm.base.BaseActivity;
import com.mobileapplicationsclass.pykandlxm.model.TandTModel;
import com.mobileapplicationsclass.pykandlxm.model.WandNModel;
import com.mobileapplicationsclass.pykandlxm.retrofit.TandTServerInterface;
import com.mobileapplicationsclass.pykandlxm.retrofit.WandNServerInterface;
import com.mobileapplicationsclass.pykandlxm.utils.OkHttp3Utils;
import com.mobileapplicationsclass.pykandlxm.utils.ToastUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/9/9 0009.
 */
public class    StarActivity extends BaseActivity {

    private static final String TAG = "StarActivity";

    private Retrofit retrofit;

    private String name;

    private Call<TandTModel> call_tandt = null;
    private Call<WandNModel> call_wandn = null;

    //详情页加载控件
    @Bind(R.id.str_toolbar)
    Toolbar strToolbar;
    @Bind(R.id.rl_pb)
    RelativeLayout rlPb;
    @Bind(R.id.re_rl_pb)
    RelativeLayout reRlPb;
    @Bind(R.id.sv)
    NestedScrollView sv;
    @Bind(R.id.sv2)
    NestedScrollView sv2;
    @Bind(R.id.re_btn)
    Button reBtn;
    @Bind(R.id.re_pb)
    ProgressBar rePb;

    //详情页今天和明天文本控件
    @Bind(R.id.tv_starname)
    TextView tvStarname;
    @Bind(R.id.tv_data)
    TextView tvData;
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

    //详情页这周和下周文本控件
    @Bind(R.id.tv_date2)
    TextView tvDate2;
    @Bind(R.id.tv_starname2)
    TextView tvStarname2;
    @Bind(R.id.tv_health2)
    TextView tvHealth2;
    @Bind(R.id.tv_job2)
    TextView tvJob2;
    @Bind(R.id.tv_love2)
    TextView tvLove2;
    @Bind(R.id.tv_money2)
    TextView tvMoney2;
    @Bind(R.id.tv_work2)
    TextView tvWork2;

    @Override
    public int getLayoutId() {
        return R.layout.activity_star;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("starname");

        OkHttpClient client = OkHttp3Utils.getOkHttpSingletonInstance();
        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.URL_BASE_STAR)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TandTretrofit(name, "today");


//  延时操作
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
//                    TandTretrofit(name, "today");
//
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();

    }

    @Override
    public void initToolBar() {
        strToolbar.setTitle(name);
        setSupportActionBar(strToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        strToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_period, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.today:
                TandTretrofit(name, "today");
                break;
            case R.id.tomorrow:
                TandTretrofit(name, "tomorrow");
                break;
            case R.id.week:
                WandNTretrofit(name, "week");
                break;
            case R.id.nextweek:
                WandNTretrofit(name, "nextweek");
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void TandTretrofit(String consName, String type) {
//        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://web.juhe.cn:8080/").addConverterFactory(GsonConverterFactory.create()).build();
        TandTServerInterface tandTServerInterface = retrofit.create(TandTServerInterface.class);
        Map<String, String> map = new HashMap<>();
        map.put("consName", consName);
        map.put("type", type);
        map.put("key", "fe7f73e0ae8b50de07507ce4769ab67e");
        call_tandt = tandTServerInterface.getResult(map);
        call_tandt.enqueue(new Callback<TandTModel>() {
            @Override
            public void onResponse(Call<TandTModel> call, Response<TandTModel> response) {
                if (response.isSuccess() && response.body() != null) {
                    TandTModel tandTModel = response.body();
                    if (tandTModel.getResultcode().equals("200")) {
                        tvStarname.setText(tandTModel.getName());
                        tvData.setText(tandTModel.getDatetime());
                        tvAll.setText(tandTModel.getAll());
                        tvColor.setText(tandTModel.getColor());
                        tvHealth.setText(tandTModel.getHealth());
                        tvLove.setText(tandTModel.getLove());
                        tvMoney.setText(tandTModel.getMoney());
                        tvNumber.setText("" + tandTModel.getNumber());
                        tvQfriend.setText(tandTModel.getQFriend());
                        tvSummary.setText(tandTModel.getSummary());
                        tvWork.setText(tandTModel.getWork());

                        //布局隐藏与出现
                        sv2.setVisibility(View.GONE);
                        rlPb.setVisibility(View.INVISIBLE);
                        reRlPb.setVisibility(View.INVISIBLE);
                        sv.setVisibility(View.VISIBLE);
                    } else {
                        Log.d(TAG, "错误码为：" + tandTModel.getError_code());
                    }
                }
            }

            @Override
            public void onFailure(Call<TandTModel> call, Throwable t) {
                sv.setVisibility(View.GONE);
                sv2.setVisibility(View.GONE);
                rlPb.setVisibility(View.GONE);
                reRlPb.setVisibility(View.VISIBLE);
                reBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TandTretrofit(name, "today");
                    }
                });
                ToastUtil.showToast(StarActivity.this, "您的手机网络不太顺畅哦");
            }
        });
    }

    private void WandNTretrofit(String consName, String type) {
//        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://web.juhe.cn:8080/").addConverterFactory(GsonConverterFactory.create()).build();
        WandNServerInterface wandNServerInterface = retrofit.create(WandNServerInterface.class);
        Map<String, String> map = new HashMap<>();
        map.put("consName", consName);
        map.put("type", type);
        map.put("key", "fe7f73e0ae8b50de07507ce4769ab67e");
        call_wandn = wandNServerInterface.getResult(map);
        call_wandn.enqueue(new Callback<WandNModel>() {
            @Override
            public void onResponse(Call<WandNModel> call, Response<WandNModel> response) {
                if (response.isSuccess() && response.body() != null) {
                    WandNModel wandNModel = response.body();
                    if (wandNModel.getResultcode().equals("200")) {
                        tvStarname2.setText(wandNModel.getName());
                        tvDate2.setText(wandNModel.getDate());
                        tvHealth2.setText(wandNModel.getHealth());
                        tvJob2.setText(wandNModel.getJob());
                        tvLove2.setText(wandNModel.getLove());
                        tvMoney2.setText(wandNModel.getMoney());
                        tvWork2.setText(wandNModel.getWork());

                        //布局隐藏与出现
                        sv.setVisibility(View.GONE);
                        rlPb.setVisibility(View.INVISIBLE);
                        reRlPb.setVisibility(View.INVISIBLE);
                        sv2.setVisibility(View.VISIBLE);
                    } else {
                        Log.d(TAG, "错误码为：" + wandNModel.getError_code());
                    }
                }
            }

            @Override
            public void onFailure(Call<WandNModel> call, Throwable t) {
                sv.setVisibility(View.GONE);
                sv2.setVisibility(View.GONE);
                rlPb.setVisibility(View.GONE);
                reRlPb.setVisibility(View.VISIBLE);
                reBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        WandNTretrofit(name, "week");
                    }
                });
                ToastUtil.showToast(StarActivity.this, "您的手机网络不太顺畅哦");
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        //让当前网络请求停止
        call_tandt.cancel();
    }


}
