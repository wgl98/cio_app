package com.cio_app.view.policy.policyContent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cio_app.R;
import com.cio_app.model.policy.policyModel.PolicyBodyValue;

import java.util.List;

public class PolicyItemAdapter extends ArrayAdapter<PolicyBodyValue> {

    private onLoadMoreListener listener;
    private int resourceId;
    private Context context;
    public PolicyItemAdapter(Context context, int ResourceId, List<PolicyBodyValue> objects){
        super(context,ResourceId,objects);
        resourceId = ResourceId;
        this.context = context;
    }
    @Override
    public View getView(final int position, final View convertView, ViewGroup parent){
        final PolicyBodyValue value  = getItem(position);
        View view;
        final ViewHolder viewHolder;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.img = view.findViewById(R.id.policy_content_picture);
            viewHolder.title = view.findViewById(R.id.policy_content_title);
            viewHolder.date = view.findViewById(R.id.policy_content_date);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        Glide.with(context).load(value.cover).into(viewHolder.img);
        viewHolder.title.setText(value.title);
        viewHolder.date.setText(value.createTime);
        if(listener != null){
            listener.onLoadMore(position);
        }
        return view;
    }

    class ViewHolder{
        ImageView img;
        TextView title;
        TextView date;
    }

    public void setOnLoadMoreListener(onLoadMoreListener listener) {
        this.listener = listener;
    }

    public interface onLoadMoreListener{
        void onLoadMore(int position);
    }

}
