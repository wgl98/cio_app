package com.cio_app.view.mine.member.companyMemberSubmit.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.cio_app.R;

import java.util.List;

public class LicenseAdapter extends ArrayAdapter<String> {
    private int resourceId;
    private Context context;
    private List<String> imgList;

    public LicenseAdapter(Context context, int textViewResourceId, List<String> objects){
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
        imgList = objects;
        this.context = context;
    }

    public void clearList(){
        if(imgList != null){
            imgList.clear();
            notifyDataSetChanged();
        }
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent){
        final String path = getItem(position);
        View view;
        final ViewHolder viewHolder;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.img = (ImageView) view.findViewById(R.id.imageView_item);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.img.setImageBitmap(BitmapFactory.decodeFile(path));
        return  view;
    }

    class ViewHolder{
        ImageView img;
    }
}
