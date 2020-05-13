package com.cio_app.view.mine.service.serviceContent;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cio_app.R;
import com.cio_app.view.project.projectDetail.projectPerson.ProjectPersonDetailActivity;

public class ServiceHasPublishedFragment extends Fragment {
    private Context mContext;

    public static Fragment newInstance(){
        ServiceHasPublishedFragment fragment = new ServiceHasPublishedFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_mine_service_has_published_layout,null);
        rootView.findViewById(R.id.mine_person_service_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProjectPersonDetailActivity.start(mContext);
            }
        });
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
