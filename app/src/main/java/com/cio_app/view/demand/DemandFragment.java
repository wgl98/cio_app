package com.cio_app.view.demand;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cio_app.R;
import com.cio_app.view.demand.demandDetail.DemandDetailActivity;
import com.cio_app.view.demand.demandSubmit.DemandSubmitActivity;

public class DemandFragment extends Fragment {

    private Context mContext;

    public static Fragment  newInstance() {
        DemandFragment demandFragment = new DemandFragment();
        return  demandFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_demand_layout,null);
        rootView.findViewById(R.id.layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DemandDetailActivity.start(mContext);
            }
        });

        rootView.findViewById(R.id.btn_add_demand).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DemandSubmitActivity.start(mContext);
            }
        });
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
