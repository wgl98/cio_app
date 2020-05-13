package com.cio_app.view.conference.conferenceContent.conferenceIntroduce;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.TextureMapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.cio_app.R;

public class ConferenceIntroduceFragment extends Fragment {

    private View view;

    private TextureMapView mapView;
    private AMap aMap;

    private TextView tv_is_approve_state;

    private Context mContext;

    public static Fragment newInstance(){
        ConferenceIntroduceFragment fragemnt = new ConferenceIntroduceFragment();
        return fragemnt;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_conference_content_introduce_layout,null);
        view = rootView;
        tv_is_approve_state = (TextView) rootView.findViewById(R.id.conference_content_detail_in_approve_state);
        mapView = (TextureMapView) rootView.findViewById(R.id.conference_content_detail_map);
        mapView.onCreate(savedInstanceState);
        if(aMap == null){
            aMap = mapView.getMap();
            aMap.moveCamera(CameraUpdateFactory.zoomTo(16));
            UiSettings uiSettings = aMap.getUiSettings();
            uiSettings.setZoomControlsEnabled(false);
            uiSettings.setAllGesturesEnabled(false);
            aMap.addMarker(new MarkerOptions()
                    .position(new LatLng(39.906901,116.397972))
                    .icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.map_blue_tag))));
            aMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(39.906901,116.397972)));
        }
        return rootView;
    }

    public void showIsApprove(){
        TextView textView = view.findViewById(R.id.conference_content_detail_in_approve_state);
        textView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }
}
