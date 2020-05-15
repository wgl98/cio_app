package com.cio_app.view.conference.conferenceSigning;

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
import com.cio_app.model.conference.conferenceModel.BaseConferenceModel;
import com.cio_app.model.conference.conferenceModel.ConferenceBodyValue;
import com.cio_app.view.conference.conferenceContent.ConferenceDetailActivity;
import com.cio_app.view.conference.conferenceContent.adapter.ConferenceItemAdapter;
import com.e.lib_common_ui.loading_view.LoadingView;
import com.lib_network.listener.DisposeDataListener;
import com.lib_network.request.RequestParams;

import java.util.List;

public class ConferenceSignFragment extends Fragment {

    private Context mContext;

    private int pageNumber;
    private int totalPage;

    private BaseConferenceModel baseConferenceModel;
    private List<ConferenceBodyValue> mList;

    private LoadingView loadingView;

    private ListView mListView;
    private ConferenceItemAdapter mAdapter;

    public static Fragment newInstance(){
        ConferenceSignFragment fragment = new ConferenceSignFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_conference_sign_layout,null);
        mListView = rootView.findViewById(R.id.listView_in_conference_sign_fragment);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        requestData();
    }

    //请求数据
    private void requestData() {
        RequestParams params = new RequestParams();
        params.put("status","SIGNING");
        RequestCenter.requestConferenceList(params,new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                Log.d("请求数据","成功");
                baseConferenceModel = (BaseConferenceModel) responseObj;
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
        params.put("status","SIGNING");
        RequestCenter.requestConferenceList(params,new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                Log.d("请求数据","成功");
                BaseConferenceModel moreData = (BaseConferenceModel) responseObj;
                mList.addAll(moreData.data.data);
                mAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Object reasonObj) {
                Log.d("请求数据","失败");
            }
        });
    }

    private void updateView(){
        loadingView.dismiss();
        mList = baseConferenceModel.data.data;
        pageNumber = baseConferenceModel.data.pageNum+1;
        totalPage = baseConferenceModel.data.total;
        mAdapter = new ConferenceItemAdapter(mContext,R.layout.list_item_conference_content_layout,mList);
        mAdapter.setOnLoadMoreListener(new ConferenceItemAdapter.onLoadMoreListener() {
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
                ConferenceDetailActivity.start(mContext,mList.get(position).id);
            }
        });
        mListView.setAdapter(mAdapter);
    }

}
