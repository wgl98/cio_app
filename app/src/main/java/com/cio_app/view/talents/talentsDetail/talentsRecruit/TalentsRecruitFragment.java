package com.cio_app.view.talents.talentsDetail.talentsRecruit;

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
import com.cio_app.view.talents.talentsDetail.talentsRecruit.adapter.TalentsRecruitAdapter;
import com.google.android.material.tabs.TabLayout;

public class TalentsRecruitFragment extends Fragment {

    private static final CHANNEL[] CHANNELS =
            new CHANNEL[]{CHANNEL.ALL, CHANNEL.UI_ENGINEER, CHANNEL.SOFTWARE_ENGINEER,CHANNEL.SYSTEM_ENGINEER};

    private Context mContext;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TalentsRecruitAdapter mAdapter;

    public static Fragment newInstance(){
        TalentsRecruitFragment talentsFragment = new TalentsRecruitFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_talents_recruit_layout,null);
        tabLayout = (TabLayout) rootView.findViewById(R.id.talents_recruit_content_tabLayout);
        viewPager = (ViewPager) rootView.findViewById(R.id.talents_recruit_content_viewPager);
        mAdapter = new TalentsRecruitAdapter(getActivity().getSupportFragmentManager(),CHANNELS);
        viewPager.setAdapter(mAdapter);
        tabLayout.setupWithViewPager(viewPager);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
