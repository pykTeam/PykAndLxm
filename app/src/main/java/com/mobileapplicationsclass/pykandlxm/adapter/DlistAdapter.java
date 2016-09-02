package com.mobileapplicationsclass.pykandlxm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mobileapplicationsclass.pykandlxm.R;
import com.mobileapplicationsclass.pykandlxm.model.DrawerList;

import java.util.List;

/**
 * Created by Administrator on 2016/9/2 0002.
 */
public class DlistAdapter extends BaseAdapter {
    private Context context;
    private List<DrawerList> dl_List;

    public DlistAdapter(Context context, List<DrawerList> dl_data) {
        this.context = context;
        this.dl_List = dl_data;
    }

    @Override
    public int getCount() {
        return dl_List == null ? 0 : dl_List.size();
    }

    @Override
    public Object getItem(int position) {
        return dl_List.get(position);
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
            viewHolder.textView = (TextView) convertView.findViewById(R.id.text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(dl_List.get(position).getName());
        return convertView;
    }

    private class ViewHolder {
        TextView textView = null;
    }


}
