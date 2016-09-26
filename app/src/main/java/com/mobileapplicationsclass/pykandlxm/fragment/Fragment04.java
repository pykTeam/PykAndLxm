package com.mobileapplicationsclass.pykandlxm.fragment;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mobileapplicationsclass.pykandlxm.R;
import com.mobileapplicationsclass.pykandlxm.adapter.SideslipAdapter;
import com.mobileapplicationsclass.pykandlxm.adapter.Sideslip_collectAdapter;
import com.mobileapplicationsclass.pykandlxm.base.BaseFragment;
import com.mobileapplicationsclass.pykandlxm.model.FineModel;
import com.mobileapplicationsclass.pykandlxm.sqlite.FineEntity;
import com.mobileapplicationsclass.pykandlxm.sqlite.SQLiteDao;
import com.yanzhenjie.recyclerview.swipe.Closeable;
import com.yanzhenjie.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/4 0004.
 */
public class Fragment04 extends BaseFragment {

    List<FineEntity> list;
    //    private List<FineModel.ResultBean.ListBean> list;
    private Sideslip_collectAdapter sideslip_collectAdapter;
    private SQLiteDao sqLiteDao;

    /**
     * 标志位，标志已经初始化完成
     */
    private boolean isPrepared;

    @Bind(R.id.recycler_view)
    SwipeMenuRecyclerView recyclerView;
    @Bind(R.id.btn)
    FloatingActionButton btn;

    @Override
    public int setLayoutResouceId() {
        return R.layout.fragment04;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        isPrepared = true;
        initContent();
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible) {
            return;
        }
        query();
    }


    private void initContent() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));// 布局管理器。
        recyclerView.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        recyclerView.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。

        // 设置侧滑菜单创建器。
        recyclerView.setSwipeMenuCreator(swipeMenuCreator);
        // 设置侧滑菜单Item点击监听。
        recyclerView.setSwipeMenuItemClickListener(menuItemClickListener);


    }

    private void query() {
        sqLiteDao = new SQLiteDao(getActivity());
        list = sqLiteDao.queryAll();
        sideslip_collectAdapter = new Sideslip_collectAdapter(getActivity(), list);
        recyclerView.setAdapter(sideslip_collectAdapter);
        sideslip_collectAdapter.notifyDataSetChanged();
    }

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

    /**
     * 侧滑菜单点击监听。
     */
    private OnSwipeMenuItemClickListener menuItemClickListener = new OnSwipeMenuItemClickListener() {
        /**
         * Item的菜单被点击的时候调用。
         *
         * @param closeable       closeable. 用来关闭菜单。
         * @param adapterPosition adapterPosition. 这个菜单所在的item在Adapter中position。
         * @param menuPosition    menuPosition. 这个菜单的position。比如你为某个Item创建了2个MenuItem，那么这个position可能是是 0、1，
         * @param direction       如果是左侧菜单，值是：SwipeMenuRecyclerView#LEFT_DIRECTION，如果是右侧菜单，值是：SwipeMenuRecyclerView#RIGHT_DIRECTION.
         */
        @Override
        public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition, int direction) {
            closeable.smoothCloseMenu();// 关闭被点击的菜单。
            //右侧菜单
            if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
                Toast.makeText(getActivity(), "条目第" + (adapterPosition + 1) + "; 右侧菜单第" + (menuPosition + 1), Toast.LENGTH_SHORT).show();
                sqLiteDao.delete(list.get(adapterPosition).getId());
            }
            // TODO 推荐调用Adapter.notifyItemRemoved(position)，也可以Adapter.notifyDataSetChanged();
            if (menuPosition == 0) {// 删除按钮被点击。
                list.remove(adapterPosition);
                sideslip_collectAdapter.notifyItemRemoved(adapterPosition);
            }

        }
    };
}