package com.mobileapplicationsclass.pykandlxm.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobileapplicationsclass.pykandlxm.R;
import com.mobileapplicationsclass.pykandlxm.model.JokeModel;

import java.util.List;

/**
 * Created by Administrator on 2016/9/22 0022.
 */
public class JokeAdapter extends RecyclerView.Adapter<JokeAdapter.ViewHolder> {
    private Context context;
    private List<JokeModel.ResultBean.DataBean> list;

    public JokeAdapter(List<JokeModel.ResultBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private TextView textView2;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_context);
            textView2 = (TextView) itemView.findViewById(R.id.tv_updatetime);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.joke_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(list.get(position).getContent());
        holder.textView2.setText(list.get(position).getUpdatetime());
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }
}
