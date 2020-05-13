package com.cio_app.view.conference.conferenceFinished;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cio_app.R;
import com.cio_app.model.FragmentTag;
import com.cio_app.view.conference.conferenceContent.ConferenceDetailActivity;

public class ConferenceFinishedFragment extends Fragment {
    private Context mContext;

    public static Fragment newInstance(){
        ConferenceFinishedFragment fragment = new ConferenceFinishedFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_conference_finish_layout,null);
        rootView.findViewById(R.id.conference_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConferenceDetailActivity.start(mContext, FragmentTag.CONFERENCE_FINISH_ID);
            }
        });
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}