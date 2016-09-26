package com.mobileapplicationsclass.pykandlxm.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobileapplicationsclass.pykandlxm.R;
import com.mobileapplicationsclass.pykandlxm.listener.OnItemClickListener;
import com.mobileapplicationsclass.pykandlxm.sqlite.FineEntity;
import com.squareup.picasso.Picasso;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;

import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 2016/9/24 0024.
 */
public class Sideslip_collectAdapter extends SwipeMenuAdapter<Sideslip_collectAdapter.CollectViewHolder> {
    private Context context;
    private List<FineEntity> list;
    private OnItemClickListener mOnItemClickListener;
    private String isNull = "isNull";

    public Sideslip_collectAdapter(Context context, List<FineEntity> list) {
        this.context = context;
        this.list = list;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.fine_item, parent, false);
    }

    @Override
    public Sideslip_collectAdapter.CollectViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        return new CollectViewHolder(realContentView);
    }


    @Override
    public void onBindViewHolder(Sideslip_collectAdapter.CollectViewHolder holder, int position) {
        holder.setData(list.get(position).getTitle(), list.get(position).getSource(), list.get(position).getIcon().equals("") ? isNull : list.get(position).getIcon(), context);
        holder.setOnItemClickListener(mOnItemClickListener);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


    static class CollectViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvTitle;
        TextView tvSource;
        ImageView imageView;
        OnItemClickListener mOnItemClickListener;

        public CollectViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvSource = (TextView) itemView.findViewById(R.id.tv_source);
            imageView = (ImageView) itemView.findViewById(R.id.iv_icon);
        }

        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.mOnItemClickListener = onItemClickListener;
        }

        public void setData(String title, String source, String img, Context context) {

            this.tvTitle.setText(title);
            this.tvSource.setText(source);
            if (!img.equals("isNull")) {
                Picasso.with(context)
                        .load(img)
                        .resize(300, 200)
                        .centerCrop()
                        .placeholder(R.mipmap.is_loading)
                        .error(R.mipmap.is_loading)
                        .into(imageView);
            } else {
                Picasso.with(context).load(R.mipmap.is_loading).resize(300, 200);
            }

        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(v.getRootView(), getLayoutPosition());
            }
        }
    }


}
