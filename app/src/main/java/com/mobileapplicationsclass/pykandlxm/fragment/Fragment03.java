package com.mobileapplicationsclass.pykandlxm.fragment;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mobileapplicationsclass.pykandlxm.R;
import com.mobileapplicationsclass.pykandlxm.activity.WebActivity;
import com.mobileapplicationsclass.pykandlxm.adapter.SideslipAdapter;
import com.mobileapplicationsclass.pykandlxm.api.Constant;
import com.mobileapplicationsclass.pykandlxm.base.BaseFragment;
import com.mobileapplicationsclass.pykandlxm.listener.OnItemClickListener;
import com.mobileapplicationsclass.pykandlxm.model.FineModel;
import com.mobileapplicationsclass.pykandlxm.retrofit.FineServerInterface;
import com.mobileapplicationsclass.pykandlxm.sqlite.FineEntity;
import com.mobileapplicationsclass.pykandlxm.sqlite.SQLiteDao;
import com.mobileapplicationsclass.pykandlxm.utils.CommonUtils;
import com.mobileapplicationsclass.pykandlxm.utils.OkHttp3Utils;
import com.mobileapplicationsclass.pykandlxm.utils.ToastUtil;
import com.yanzhenjie.recyclerview.swipe.Closeable;
import com.yanzhenjie.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/9/4 0004.
 */
public class Fragment03 extends BaseFragment {

    protected static final String TAG = "Fragment03";

    private List<FineModel.ResultBean.ListBean> list;
    private SideslipAdapter sideslipAdapter;
    private int png = 1;
    private Retrofit retrofit;
    private SQLiteDao sqLiteDao;
    private Call<FineModel> call_fine = null;


    /**
     * 标志位，标志已经初始化完成
     */
    private boolean isPrepared;
    /**
     * 是否已被加载过一次，第二次就不再去请求数据了
     */
    private boolean mHasLoadedOnce;

    @Bind(R.id.recycler_view)
    SwipeMenuRecyclerView recyclerView;
    @Bind(R.id.swipe_layout)
    SwipeRefreshLayout swipeLayout;
    @Bind(R.id.btn)
    FloatingActionButton btn;

    @Override
    public int setLayoutResouceId() {
        return R.layout.fragment03;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        isPrepared = true;

        //下拉刷新监听
        swipeLayout.setOnRefreshListener(mOnRefreshListener);
        // 添加滚动监听。
        recyclerView.addOnScrollListener(mOnScrollListener);


//        retrofit(png);
        Log.d(TAG, "lazyLoad: 2");
        initContent();


        //点击置顶
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.scrollToPosition(0);
            }
        });

    }

    @Override
    protected void lazyLoad() {

        if (!isPrepared || !isVisible || mHasLoadedOnce) {
            return;
        }
        retrofit(png);
        Log.d(TAG, "lazyLoad: 加载精选数据");
    }


    private void initContent() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));// 布局管理器。
        recyclerView.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        recyclerView.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
//        recyclerView.addItemDecoration(new ItemDivider(getActivity(),R.drawable.divider_recycler));// 添加分割线。

        // 设置侧滑菜单创建器。
        recyclerView.setSwipeMenuCreator(swipeMenuCreator);


        // 设置侧滑菜单Item点击监听。
        recyclerView.setSwipeMenuItemClickListener(menuItemClickListener);


    }

    private void retrofit(int pno) {
        OkHttpClient client = OkHttp3Utils.getOkHttpSingletonInstance();
        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.URL_BASE_FINE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        FineServerInterface fineServerInterface = retrofit.create(FineServerInterface.class);
        Map<String, String> map = new HashMap<>();
        map.put("pno", String.valueOf(pno));
        map.put("ps", "10");
        map.put("dtype", "json");
        map.put("key", "84b5cb59bd3cdcdf640200bdd0394627");
        call_fine = fineServerInterface.getResult(map);
        call_fine.enqueue(new Callback<FineModel>() {
            @Override
            public void onResponse(Call<FineModel> call, Response<FineModel> response) {
                if (response.isSuccess() && response.body() != null) {
                    FineModel fineModel = response.body();
                    try {
                        if (png > 1) {
                            list.addAll(fineModel.getResult().getList());
                            sideslipAdapter.notifyDataSetChanged();
                        } else {
                            list = fineModel.getResult().getList();
                            sideslipAdapter = new SideslipAdapter(list, getActivity());
                            sideslipAdapter.setOnItemClickListener(onItemClickListener);
                            recyclerView.setAdapter(sideslipAdapter);

//                            sideslipAdapter.notifyDataSetChanged();

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                //标记已加载
                mHasLoadedOnce = true;
            }

            @Override
            public void onFailure(Call<FineModel> call, Throwable t) {
                ToastUtil.showToast(getActivity(), "您的手机网络不太顺畅哦");
            }
        });

    }

    /**
     * 条目点击监听。
     */
    private OnItemClickListener onItemClickListener = new OnItemClickListener() {


        @Override
        public void onItemClick(int position) {
            try {
                Intent intent = new Intent(getActivity(), WebActivity.class);
                Bundle bundle = new Bundle();
                Log.d("TAG", "onItemClick: " + position);
                bundle.putString("weburl", list.get(position).getUrl());
                intent.putExtras(bundle);
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    };


    /**
     * 侧滑菜单点击监听。
     */
    private OnSwipeMenuItemClickListener menuItemClickListener = new OnSwipeMenuItemClickListener() {
        /**
         * Item的菜单被点击的时候调用。
         * @param closeable       closeable. 用来关闭菜单。
         * @param adapterPosition adapterPosition. 这个菜单所在的item在Adapter中position。
         * @param menuPosition    menuPosition. 这个菜单的position。比如你为某个Item创建了2个MenuItem，那么这个position可能是是 0、1，
         * @param direction       如果是左侧菜单，值是：SwipeMenuRecyclerView#LEFT_DIRECTION，如果是右侧菜单，值是：SwipeMenuRecyclerView#RIGHT_DIRECTION.
         */
        @Override
        public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition, int direction) {
            Log.d(TAG, "adapterPosition：" + adapterPosition);
            closeable.smoothCloseMenu();// 关闭被点击的菜单。
            //右侧菜单
            if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {

                sqLiteDao = new SQLiteDao(getActivity());
                FineEntity fineEntity = new FineEntity();
                try {
                    if (sqLiteDao.find(list.get(adapterPosition).getId())) {
                        ToastUtil.showToast(getActivity(), "已收藏！");
                    } else {
                        fineEntity.setSource(list.get(adapterPosition).getSource());
                        fineEntity.setTitle(list.get(adapterPosition).getTitle());
                        fineEntity.setIcon(list.get(adapterPosition).getFirstImg());
                        fineEntity.setUrl(list.get(adapterPosition).getUrl());
                        fineEntity.setWechat_id(list.get(adapterPosition).getId());
                        sqLiteDao.insert(fineEntity);
                        ToastUtil.showToast(getActivity(), "收藏成功！");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    sideslipAdapter.notifyDataSetChanged();
                    ToastUtil.showToast(getActivity(), "收藏失败！请重试一遍！");
                }

            }

            //左侧菜单
//                    else if (direction == SwipeMenuRecyclerView.LEFT_DIRECTION) {
//                        Toast.makeText(getActivity(), "list第" + (adapterPosition+1) + "; 左侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
//                    }

//                    // TODO 推荐调用Adapter.notifyItemRemoved(position)，也可以Adapter.notifyDataSetChanged();
//                    if (menuPosition == 0) {// 删除按钮被点击。
//                        mStrings.remove(adapterPosition);
//                        mMenuAdapter.notifyItemRemoved(adapterPosition);
//                    }
        }
    };


    /**
     * 下拉刷新监听。
     */
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    boolean isNetConnected = CommonUtils.isNetworkAvailable(getActivity());
                    if (!isNetConnected) {
                        ToastUtil.showToast(getActivity(), "您的手机网络不太顺畅哦");
                    } else {
                        if (!mHasLoadedOnce) {
                            retrofit(png);
                        }
                    }
                    swipeLayout.setRefreshing(false);
                }
            }, 1000);
        }
    };

    /**
     * 加载更多
     */
    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            if (!recyclerView.canScrollVertically(1)) {// 手指不能向上滑动了
                // TODO 这里有个注意的地方，如果你刚进来时没有数据，但是设置了适配器，这个时候就会触发加载更多，需要开发者判断下是否有数据，如果有数据才去加载更多。
                //先判断网络
                boolean isNetConnected = CommonUtils.isNetworkAvailable(getActivity());
                if (!isNetConnected) {
                    ToastUtil.showToast(getActivity(), "您的手机网络不太顺畅哦");
                    return;
                } else {
                    if (png < 10) {
                        ToastUtil.showToast(getActivity(), "加载更多...");
                        retrofit(png += 1);
                    } else {
                        ToastUtil.showToast(getActivity(), "已无更多");
                    }
                }
            }
            sideslipAdapter.notifyDataSetChanged();
        }
    };

    /**
     * 侧滑菜单创建器。在Item要创建菜单的时候调用。
     */
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {

//            SwipeMenuItem addItem = new SwipeMenuItem(getActivity())
//                    .setBackgroundDrawable(R.drawable.selector_green)// 点击的背景。
//                    .setImage(R.mipmap.ic_action_add) // 图标。
//                    .setWidth(size) // 宽度。
//                    .setHeight(size); // 高度。
//            swipeLeftMenu.addMenuItem(addItem); // 添加一个按钮到左侧菜单。

            int height = ViewGroup.LayoutParams.MATCH_PARENT;

            SwipeMenuItem deleteItem = new SwipeMenuItem(getActivity())
                    .setBackgroundDrawable(R.color.bacground)
                    .setImage(R.mipmap.ic_action_add) // 图标。
                    .setText("收藏") // 文字。
                    .setTextColor(R.color.title_bacground) // 文字颜色。
                    .setWidth(150)
                    .setHeight(height);

            swipeRightMenu.addMenuItem(deleteItem);// 添加一个按钮到右侧侧菜单。.

            // 上面的菜单哪边不要菜单就不要添加。
        }
    };


//    @Override
//    public void onResume() {
//        super.onResume();
//        if(sideslipAdapter!=null){
//            sideslipAdapter.notifyDataSetChanged();
//            Log.d(TAG, "onResume: 恢复并且刷新");
//        }

}
