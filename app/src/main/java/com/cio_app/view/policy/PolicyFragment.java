package com.cio_app.view.policy;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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
import com.cio_app.api.RequestCenter;
import com.cio_app.model.policy.policyCategory.BasePolicyCategoryModel;
import com.cio_app.model.policy.policyCategory.PolicyFirstCategoryModelBodyValue;
import com.cio_app.model.policy.policyCategory.PolicySecondCategoryModelBodyValue;
import com.cio_app.view.policy.policyApply.PolicyApplyFragment;
import com.cio_app.view.policy.policyCondition.PolicyConditionFragment;
import com.cio_app.view.policy.policyShow.PolicyShowFragment;
import com.lib_network.listener.DisposeDataListener;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class PolicyFragment extends Fragment implements RadioGroup.OnCheckedChangeListener{

    private Context mContext;

    private FragmentManager manager;
    private FragmentTransaction transaction;

    private RadioGroup radioGroup;                               //选择一级分类

    private PolicyShowFragment showFragment;                  //政策项目展示页面
    private PolicyConditionFragment conditionFragment;        //政策项目动态页面
    private PolicyApplyFragment applyFragment;                //政策项目申报页面

    public static Fragment newInstance() {
        PolicyFragment fragment = new PolicyFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();

        manager = getFragmentManager();
        transaction = manager.beginTransaction();
        conditionFragment = new PolicyConditionFragment();
        showFragment = new PolicyShowFragment();
        applyFragment = new PolicyApplyFragment();
        transaction.add(R.id.policy_content_layout, conditionFragment);
        transaction.add(R.id.policy_content_layout, showFragment);
        transaction.add(R.id.policy_content_layout, applyFragment);
        transaction.show(conditionFragment);
        transaction.hide(showFragment);
        transaction.hide(applyFragment);
        transaction.commit();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_policy_layout,null);
        radioGroup = (RadioGroup) rootView.findViewById(R.id.policy_title);
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
            case R.id.policy_title_condition:
                transaction.show(conditionFragment);
                transaction.hide(applyFragment);
                transaction.hide(showFragment);
                break;
            case R.id.policy_title_apply:
                transaction.show(applyFragment);
                transaction.hide(conditionFragment);
                transaction.hide(showFragment);
                break;
            case R.id.policy_title_show:
                transaction.show(showFragment);
                transaction.hide(applyFragment);
                transaction.hide(conditionFragment);
                break;
        }
        transaction.commit();
    }
}
