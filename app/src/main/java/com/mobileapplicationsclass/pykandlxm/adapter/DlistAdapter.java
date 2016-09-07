package com.mobileapplicationsclass.pykandlxm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobileapplicationsclass.pykandlxm.R;
import com.mobileapplicationsclass.pykandlxm.model.DrawerList;

import java.util.List;

/**
 * Created by lxm on 2016/9/2 0002.
 * 抽屜列表適配器
 */
public class DlistAdapter extends BaseAdapter {
    private Context context;
    private List<DrawerList> mDl_List;

    public DlistAdapter(Context context, List<DrawerList> dl_data) {
        this.context = context;
        this.mDl_List = dl_data;
    }

    @Override
    public int getCount() {
        return mDl_List == null ? 0 : mDl_List.size();
    }

    @Override
    public Object getItem(int position) {
        return mDl_List.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.drawerlist_item, null);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.name);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.logo);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(mDl_List.get(position).getName());
        viewHolder.imageView.setImageResource(mDl_List.get(position).getLogo());
        return convertView;
    }

    private class ViewHolder {
        TextView textView = null;
        ImageView imageView = null;
    }


}
