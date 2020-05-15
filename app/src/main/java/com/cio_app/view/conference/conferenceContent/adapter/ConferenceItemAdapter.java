package com.cio_app.view.conference.conferenceContent.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cio_app.R;
import com.cio_app.model.conference.conferenceModel.ConferenceBodyValue;
import com.cio_app.view.policy.policyContent.PolicyItemAdapter;
import com.e.lib_common_ui.circle_image_view.RoundAngleImageView;

import java.util.List;

public class ConferenceItemAdapter extends ArrayAdapter<ConferenceBodyValue> {
    private int resourceId;
    private Context context;
    private onLoadMoreListener listener;
    public ConferenceItemAdapter(Context context, int ResourceId, List<ConferenceBodyValue> objects){
        super(context,ResourceId,objects);
        resourceId = ResourceId;
        this.context = context;
    }
    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        final ConferenceBodyValue value = getItem(position);
        View view;
        final ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.img = view.findViewById(R.id.conference_picture);
            viewHolder.title = view.findViewById(R.id.conference_title);
            viewHolder.date = view.findViewById(R.id.conference_date);
            viewHolder.state = view.findViewById(R.id.conference_state);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        Glide.with(context).load(value.coverImage).into(viewHolder.img);
        viewHolder.title.setText(value.title);
        viewHolder.date.setText(value.startTime);
        viewHolder.state.setText(value.status);
        if(value.status.equals("报名中")){
            viewHolder.state.setTextColor(context.getResources().getColor(R.color.colorBlue));
        }else if (value.status.equals("进行中")){
            viewHolder.state.setTextColor(context.getResources().getColor(R.color.colorGreen));
        }else {
            viewHolder.state.setTextColor(context.getResources().getColor(R.color.colorGray));
        }
        if(listener != null){
            listener.onLoadMore(position);
        }
        return view;
    }

        class ViewHolder{
            RoundAngleImageView img;
            TextView title;
            TextView date;
            TextView state;
        }


    public void setOnLoadMoreListener(onLoadMoreListener listener) {
        this.listener = listener;
    }

    public interface onLoadMoreListener{
        void onLoadMore(int position);
    }

}
