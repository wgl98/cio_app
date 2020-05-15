package com.cio_app.view.policy.policyContent;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cio_app.R;
import com.cio_app.api.RequestCenter;
import com.cio_app.model.policy.policyItem.BasePolicyItem;
import com.cio_app.model.policy.policyModel.BasePolicyModel;
import com.cio_app.model.policy.policyModel.PolicyBodyValue;
import com.e.lib_common_ui.loading_view.LoadingView;
import com.lib_network.listener.DisposeDataListener;
import com.lib_network.request.RequestParams;

import java.util.ArrayList;
import java.util.List;

public class PolicyContentFragment extends Fragment {
    private Context mContext;

    private int parentCategory;
    private int childCategory;
    private int pageNumber;
    private int totalPage;

    private LoadingView loadingView;

    private BasePolicyModel basePolicyModel;
    private List<PolicyBodyValue> mList = new ArrayList<>();

    private ListView mListView;
    private PolicyItemAdapter mAdapter;

    private BasePolicyItem basePolicyItem;

    public PolicyContentFragment(int parentCategory, int childCategory){
        this.parentCategory = parentCategory;
        this.childCategory = childCategory;
    }

    public static PolicyContentFragment newInstance(int parentCategory, int childCategory){
        PolicyContentFragment fragment = new PolicyContentFragment(parentCategory,childCategory);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        loadingView = new LoadingView(mContext,R.style.DialogStyle);
        loadingView.show();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_policy_show_content_layout,null);
        mListView = rootView.findViewById(R.id.listView_in_policy_show_fragment);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("页面加载完","数据请求");
        requestData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    //请求数据
    private void requestData() {
        RequestParams params = new RequestParams();
        params.put("parentId",String.valueOf(parentCategory));
        params.put("childrenId",String.valueOf(childCategory));
        RequestCenter.requestPolicyList(params,new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                Log.d("请求数据","成功");
                basePolicyModel = (BasePolicyModel) responseObj;
                updateView();
            }
            @Override
            public void onFailure(Object reasonObj) {
                Log.d("请求数据","失败");
            }
        });
    }

    //加载更多
    private void requestMore() {
        RequestParams params = new RequestParams();
        params.put("pageNum",String.valueOf(pageNumber));
        params.put("parentId",String.valueOf(parentCategory));
        params.put("childrenId",String.valueOf(childCategory));
        RequestCenter.requestPolicyList(params,new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                Log.d("请求数据","成功");
                BasePolicyModel moreData = (BasePolicyModel) responseObj;
                mList.addAll(moreData.data.content);
                mAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Object reasonObj) {
                Log.d("请求数据","失败");
            }
        });
    }

    //请求文章详情
    private void requestItemData(int id) {
        RequestParams params = new RequestParams();
        params.put("",String.valueOf(id));
        RequestCenter.requestPolicyItem(params,new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                Log.d("请求文章详情数据","成功");
                basePolicyItem = (BasePolicyItem) responseObj;
                PolicyDetail.start(mContext,basePolicyItem.data.content);
            }
            @Override
            public void onFailure(Object reasonObj) {
                Log.d("请求文章详情数据","失败");
            }
        });
    }


    private void updateView(){
        loadingView.dismiss();
        mList = basePolicyModel.data.content;
        pageNumber = basePolicyModel.data.pageNumber+1;
        totalPage = basePolicyModel.data.totalPages;
        mAdapter = new PolicyItemAdapter(mContext,R.layout.list_item_policy_content_layout,mList);
        mAdapter.setOnLoadMoreListener(new PolicyItemAdapter.onLoadMoreListener() {
            @Override
            public void onLoadMore(int position) {
                if(position + 2 == mList.size() ){
                    if(pageNumber < totalPage){
                        pageNumber++;
                        requestMore();
                    }
                }
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                requestItemData(mList.get(position).id);
            }
        });
        mListView.setAdapter(mAdapter);
    }
}
