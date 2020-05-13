package com.cio_app.view.talents.talentsDetail.talentsMarket;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.cio_app.R;
import com.cio_app.model.CHANNEL;
import com.cio_app.view.talents.talentsDetail.talentsMarket.adapter.TalentsMarketAdapter;
import com.google.android.material.tabs.TabLayout;

public class TalentsMarketFragment extends Fragment {

    private static final CHANNEL[] CHANNELS =
            new CHANNEL[]{CHANNEL.ALL, CHANNEL.MANUFACTURE_INDUSTRY, CHANNEL.RETAIL_INDUSTRY,CHANNEL.BUILDING_INDUSTRY,CHANNEL.FINANCIAL_INDUSTRY};

    private Context mContext;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TalentsMarketAdapter mAdapter;

    public static Fragment newInstance(){
        TalentsMarketFragment talentsFragment = new TalentsMarketFragment();
        return  talentsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_talents_market_layout,null);
        tabLayout = (TabLayout) rootView.findViewById(R.id.talents_market_content_tabLayout);
        viewPager = (ViewPager) rootView.findViewById(R.id.talents_market_content_viewPager);
        mAdapter = new TalentsMarketAdapter(getActivity().getSupportFragmentManager(),CHANNELS);
        viewPager.setAdapter(mAdapter);
        tabLayout.setupWithViewPager(viewPager);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
