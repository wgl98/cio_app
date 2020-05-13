package com.cio_app.view.talents;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.cio_app.R;
import com.cio_app.view.talents.talentsDetail.talentsMarket.TalentsMarketFragment;
import com.cio_app.view.talents.talentsDetail.talentsRecruit.TalentsRecruitFragment;
import com.cio_app.view.talents.talentsSubmit.TalentsSubmitActivity;

public class TalentsFragment extends Fragment implements RadioGroup.OnCheckedChangeListener{

    private Context mContext;

    private RadioGroup radioGroup;

    private FragmentManager manager;

    private FragmentTransaction transaction;

    public static Fragment newInstance(){
        TalentsFragment talentsFragment = new TalentsFragment();
        return  talentsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();

        manager = getFragmentManager();
        transaction = manager.beginTransaction();
        transaction.add(R.id.talents_content_layout, TalentsMarketFragment.newInstance());
        transaction.commit();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_talents_layout,null);
        rootView.findViewById(R.id.btn_add_talents).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TalentsSubmitActivity.start(mContext);
            }
        });
        radioGroup = (RadioGroup) rootView.findViewById(R.id.talents_title);
        radioGroup.setOnCheckedChangeListener(this);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        transaction = manager.beginTransaction();
        switch (checkedId){
            case R.id.talents_title_market:
                transaction.replace(R.id.talents_content_layout,TalentsMarketFragment.newInstance());
                break;
            case R.id.talents_title_recruit:
                transaction.replace(R.id.talents_content_layout, TalentsRecruitFragment.newInstance());
                break;
            default:
                break;
        }
        transaction.commit();
    }
}
