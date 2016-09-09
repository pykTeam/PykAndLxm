package com.mobileapplicationsclass.pykandlxm.adapter;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobileapplicationsclass.pykandlxm.R;
import com.mobileapplicationsclass.pykandlxm.model.StarGrid;

import java.util.List;

/**
 * Created by Administrator on 2016/9/8 0008.
 * 星座列表适配器
 */
public class SgridAdapter extends BaseAdapter {
    private Context context;
    private List<StarGrid> mSg_List;

    public SgridAdapter(Context context, List<StarGrid> mSg_List) {
        this.context = context;
        this.mSg_List = mSg_List;
    }

    @Override
    public int getCount() {
        return mSg_List == null ? 0 : mSg_List.size();
    }

    @Override
    public Object getItem(int position) {
        return mSg_List.get(position);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.stargrid_tiem, null);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.iv_star);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(mSg_List.get(position).getName());
        viewHolder.imageView.setImageResource(mSg_List.get(position).getLogo());
        return convertView;
    }

    private class ViewHolder {
        TextView textView = null;
        ImageView imageView = null;
    }
}
