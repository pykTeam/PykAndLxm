package com.mobileapplicationsclass.pykandlxm.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobileapplicationsclass.pykandlxm.R;
import com.mobileapplicationsclass.pykandlxm.adapter.JokeAdapter;
import com.mobileapplicationsclass.pykandlxm.api.Constant;
import com.mobileapplicationsclass.pykandlxm.base.BaseFragment;
import com.mobileapplicationsclass.pykandlxm.model.JokeModel;
import com.mobileapplicationsclass.pykandlxm.retrofit.JokeServerInterface;
import com.mobileapplicationsclass.pykandlxm.utils.CommonUtils;
import com.mobileapplicationsclass.pykandlxm.utils.OkHttp3Utils;
import com.mobileapplicationsclass.pykandlxm.utils.ToastUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.HashMap;
import java.util.List;
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
 * Created by Administrator on 2016/9/4 0004.
 */
public class Fragment02 extends BaseFragment implements PullLoadMoreRecyclerView.PullLoadMoreListener {

    private List<JokeModel.ResultBean.DataBean> list;
    private Retrofit retrofit;
    private JokeAdapter jokeAdapter;
    private int page = 1;

    protected static final String TAG = "Fragment02";


    @Bind(R.id.pullLoadMoreRecyclerView)
    PullLoadMoreRecyclerView pullLoadMoreRecyclerView;
    @Bind(R.id.btn)
    FloatingActionButton btn;

    @Override
    public int setLayoutResouceId() {
        return R.layout.fragment02;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        OkHttpClient client = OkHttp3Utils.getOkHttpSingletonInstance();
        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.URL_BASE_JOKE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        initContext();
        retrofit(page);


        //点击置顶
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pullLoadMoreRecyclerView.scrollToTop();
            }
        });

    }

    @Override
    protected void lazyLoad() {

    }

    private void initContext() {
        pullLoadMoreRecyclerView.setLinearLayout();
        pullLoadMoreRecyclerView.setOnPullLoadMoreListener(this);
        jokeAdapter = new JokeAdapter(list, getActivity());
        pullLoadMoreRecyclerView.setAdapter(jokeAdapter);
    }

    private void retrofit(final int page) {
        JokeServerInterface jokeServerInterface = retrofit.create(JokeServerInterface.class);
        Map<String, String> map = new HashMap<>();
        map.put("page", String.valueOf(page));
        map.put("pagesize", "10");
        map.put("dtype", "json");
        map.put("key", "7ba330cc1fb591d5580b4386bf4f99e4");
        Call<JokeModel> call_joke = jokeServerInterface.getResult(map);
        call_joke.enqueue(new Callback<JokeModel>() {
            @Override
            public void onResponse(Call<JokeModel> call, Response<JokeModel> response) {
                if (response.isSuccess() && response.body() != null) {
                    try {
                        JokeModel jokeModel = response.body();
                        if (page > 1) {
                            list.addAll(jokeModel.getResult().getData());
                            jokeAdapter.notifyDataSetChanged();
                        } else {
                            list = jokeModel.getResult().getData();
                            jokeAdapter = new JokeAdapter(list, getActivity());
                            pullLoadMoreRecyclerView.setAdapter(jokeAdapter);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<JokeModel> call, Throwable t) {
                ToastUtil.showToast(getActivity(), "您的手机网络不太顺畅哦");
            }
        });
    }

    //下拉刷新
    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        retrofit(page);
                        pullLoadMoreRecyclerView.setPullLoadMoreCompleted();
                    }
                });
            }
        }, 1000);
    }

    //上拉加载
    @Override
    public void onLoadMore() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        boolean isNetConnected = CommonUtils.isNetworkAvailable(getActivity());
                        if (!isNetConnected) {
                            ToastUtil.showToast(getActivity(), "您的手机网络不太顺畅哦");
                            return;
                        } else {
                            if (page < 5) {
                                retrofit(page += 1);
                            } else {
                                ToastUtil.showToast2(getActivity(), "已无更多");
                            }
                        }
                        pullLoadMoreRecyclerView.setPullLoadMoreCompleted();
                    }
                });
            }
        }, 1000);
    }
}
