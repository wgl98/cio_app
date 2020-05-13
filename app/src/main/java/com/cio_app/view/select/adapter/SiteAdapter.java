package com.cio_app.view.select.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.cio_app.R;
import com.cio_app.model.SiteBean;

import java.util.List;

public class SiteAdapter extends BaseAdapter {
    private Context mContext;
    private List<SiteBean> siteList;
    private int selectorPosition = -1;

    public SiteAdapter(Context context){
        mContext = context;
    }
    public void setData(List<SiteBean> data){
        siteList = data;
        notifyDataSetChanged();
    }
    public void clearList(){
        if(siteList != null){
            siteList.clear();
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return siteList == null ? 0 : siteList.size();
    }

    @Override
    public SiteBean getItem(int position) {
        return siteList == null ? null : siteList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        final SiteViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.list_item_select_site_layout, parent, false);
            holder = new SiteViewHolder();
            holder.site_name = (TextView) view.findViewById(R.id.show_site_name);
            holder.site_near_area = (TextView) view.findViewById(R.id.show_site_near_area);
            holder.selected_tag = (ImageView) view.findViewById(R.id.selected_site);
            view.setTag(holder);
        } else {
            holder = (SiteViewHolder) view.getTag();
        }

        if(siteList.get(position) != null){
            holder.site_name.setText(siteList.get(position).getName());
            holder.site_near_area.setText(siteList.get(position).getLocation());
        }

        if(selectorPosition == position){
         holder.selected_tag.setVisibility(View.VISIBLE);
        }else {
            holder.selected_tag.setVisibility(View.GONE);
        }
        return view;
    }

    public static class SiteViewHolder {
        TextView site_name;
        TextView site_near_area;
        ImageView selected_tag;
    }

    public void changeState(int pos) {
        selectorPosition = pos;
        notifyDataSetChanged();

    }
}
