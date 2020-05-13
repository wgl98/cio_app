package com.lib_pickerview.style.pickerview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.lib_pickerview.Interface.OnItemClickListener;
import com.lib_pickerview.R;
import com.lib_pickerview.style.citypickerview.widget.CanShow;
import com.lib_pickerview.style.citypickerview.widget.wheel.OnWheelChangedListener;
import com.lib_pickerview.style.citypickerview.widget.wheel.WheelView;
import com.lib_pickerview.style.citypickerview.widget.wheel.adapters.ArrayWheelAdapter;
import com.lib_pickerview.utils.utils;

import java.util.ArrayList;
import java.util.List;

public class PickerView implements CanShow, OnWheelChangedListener {

    private PopupWindow popwindow;

    private View popview;

    private WheelView mWheelView;

    private TextView mTvOK;

    private TextView mTvCancel;

    private OnItemClickListener mBaseListener;

    private Context context;

    private List<String> proArra = new ArrayList<>();

    private ArrayWheelAdapter arrayWheelAdapter;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mBaseListener = listener;
    }

    public PickerView() {

    }

    public void init(Context context,List list) {
        this.context = context;
        this.proArra = list;
        arrayWheelAdapter = new ArrayWheelAdapter<String>(context, proArra);
    }

    /**
     *初始化popWindow
     */

    private void initPickerPopwindow(){
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        popview = layoutInflater.inflate(R.layout.pop_pickerview, null);
        mWheelView = (WheelView)popview.findViewById(R.id.id_content);
        mTvOK = (TextView) popview.findViewById(R.id.tv_confirm);
        mTvCancel = (TextView) popview.findViewById(R.id.tv_cancel);
        popwindow = new PopupWindow(popview, LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        popwindow.setAnimationStyle(R.style.AnimBottom);
        popwindow.setBackgroundDrawable(new ColorDrawable());
        popwindow.setTouchable(true);
        popwindow.setOutsideTouchable(false);
        popwindow.setFocusable(true);

        popwindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                    utils.setBackgroundAlpha(context, 1.0f);
            }
        });

        mWheelView.addChangingListener(this);

        // 添加onclick事件
        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBaseListener.onCancel();
                hide();
            }
        });

        //确认选择
        mTvOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBaseListener.onSelected(arrayWheelAdapter.getItemText(mWheelView.getCurrentItem()).toString());
                hide();
            }
        });


        //显示省市区数据
        setUpData();

        utils.setBackgroundAlpha(context, 0.5f);
    }

    private void setUpData() {
        mWheelView.setViewAdapter(arrayWheelAdapter);
        arrayWheelAdapter.setItemResource(R.layout.item);
        arrayWheelAdapter.setItemTextResource(R.id.item_name_tv);
    }
    public void showCityPicker() {
        initPickerPopwindow();
        if (!isShow()) {
            popwindow.showAtLocation(popview, Gravity.BOTTOM, 0, 0);
        }
    }

    @Override
    public void hide() {
        if (isShow()) {
            popwindow.dismiss();
        }
    }

    @Override
    public boolean isShow() {
        return popwindow.isShowing();
    }

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
    }
}
