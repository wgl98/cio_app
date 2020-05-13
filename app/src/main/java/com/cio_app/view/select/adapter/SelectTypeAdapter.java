package com.cio_app.view.select.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cio_app.R;

import java.util.List;

public class SelectTypeAdapter extends ArrayAdapter<String> {

    private int resourceId;
    private Context context;
    private List<String> messagBeanList;
    private onClickListener  listener;
    private int pos = -1;

    public SelectTypeAdapter(Context context, int textViewResourceId, List<String> objects){
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
        messagBeanList = objects;
        this.context = context;
    }

    public void clearList(){
        if(messagBeanList != null){
            messagBeanList.clear();
            notifyDataSetChanged();
        }
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent){
        final String str = getItem(position);
        View view;
        final ViewHolder viewHolder;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.list_item_layout = (RelativeLayout) view.findViewById(R.id.select_company_type_text_layout);
            viewHolder.name = (TextView) view.findViewById(R.id.select_company_type_text);
            viewHolder.tag = (ImageView) view.findViewById(R.id.select_tag);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.name.setText(str);
        if(pos == position ){
            viewHolder.tag.setVisibility(View.VISIBLE);
            if(listener != null){
                listener.onClick(position,str);
            }
        }else {
            viewHolder.tag.setVisibility(View.INVISIBLE);
        }
        return  view;
    }

    class ViewHolder{
        RelativeLayout list_item_layout;
        TextView name;
        ImageView tag;
    }

    public void changeState(int pos) {
        this.pos = pos;
        notifyDataSetChanged();
    }

    public void setOnClickListener(onClickListener onClickListener) {
        this.listener = onClickListener;
    }

    public interface onClickListener{
        void onClick(int position, String str);
    }
}
