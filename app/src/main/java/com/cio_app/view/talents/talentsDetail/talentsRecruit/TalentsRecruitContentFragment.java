package com.cio_app.view.talents.talentsDetail.talentsRecruit;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cio_app.R;
import com.cio_app.model.CHANNEL;

public class TalentsRecruitContentFragment extends Fragment {
    private Context mContext;
    private int type;
    public TalentsRecruitContentFragment(int type){
        this.type = type;
    }

    public static TalentsRecruitContentFragment newInstance(int type){
        TalentsRecruitContentFragment fragment = new TalentsRecruitContentFragment(type);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_talents_recruit_content_layout,null);
        switch (type){
            case CHANNEL.ALL_ID:
                break;
            case CHANNEL.MANUFACTURE_INDUSTRY_ID:
                break;
            case CHANNEL.RETAIL_INDUSTRY_ID:
                break;
            case CHANNEL.BUILDING_INDUSTRY_ID:
                break;
            case CHANNEL.FINANCIAL_INDUSTRY_ID:
                break;
        }
        rootView.findViewById(R.id.talents_content_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TalentsRecruitDetailActivity.start(mContext);
            }
        });
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
