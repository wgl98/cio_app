package com.cio_app.view.mine.member.memberApproveStatus;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cio_app.R;
import com.cio_app.view.mine.member.personApproveInformation.PersonApproveInformationActivity;

public class MemberApproveSuccessFragment extends Fragment {
    private Context mContext;

    public static Fragment newInstance(){
        MemberApproveSuccessFragment fragment = new MemberApproveSuccessFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_member_approve_success_layout,null);
        rootView.findViewById(R.id.btn_look_approve_information).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PersonApproveInformationActivity.start(mContext);
            }
        });
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }
}
