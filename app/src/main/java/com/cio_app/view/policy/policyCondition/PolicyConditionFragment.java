package com.cio_app.view.policy.policyCondition;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.cio_app.R;
import com.cio_app.api.RequestCenter;
import com.cio_app.model.policy.policyCategory.BasePolicyCategoryModel;
import com.cio_app.model.policy.policyCategory.PolicyFirstCategoryModelBodyValue;
import com.cio_app.model.policy.policyCategory.PolicySecondCategoryModelBodyValue;
import com.cio_app.view.policy.adapter.PolicyContentFragmentAdapter;
import com.google.android.material.tabs.TabLayout;
import com.lib_network.listener.DisposeDataListener;

import java.util.ArrayList;
import java.util.List;

public class PolicyConditionFragment extends Fragment {

    private Context mContext;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PolicyContentFragmentAdapter mAdapter;
    private BasePolicyCategoryModel policyCategoryModel;
    private List<PolicyFirstCategoryModelBodyValue> firstCategory = new ArrayList<>();
    private List<PolicySecondCategoryModelBodyValue> titleList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_policy_condition_layout,null);
        tabLayout = (TabLayout) rootView.findViewById(R.id.policy_content_tabLayout);
        viewPager = (ViewPager) rootView.findViewById(R.id.policy_content_viewPager);
        mAdapter = new PolicyContentFragmentAdapter(getFragmentManager(),1);
        viewPager.setAdapter(mAdapter);
        tabLayout.setupWithViewPager(viewPager);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        requestData();
    }

    //请求数据
    private void requestData() {
        RequestCenter.requestPolicyType(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                policyCategoryModel = (BasePolicyCategoryModel) responseObj;
                firstCategory = policyCategoryModel.data;                              //一级分类
                titleList = firstCategory.get(0).policyCategoryVOList;
                mAdapter.setData(titleList);
            }
            @Override
            public void onFailure(Object reasonObj) {
                Log.d("Request","请求数据失败");
            }
        });
    }
}
