package com.cio_app.view.select;


import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.core.SuggestionCity;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.cio_app.R;
import com.cio_app.model.SiteBean;
import com.cio_app.view.select.adapter.SiteAdapter;
import com.e.lib_common_ui.base.BaseActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SelectSiteActivity extends BaseActivity implements View.OnClickListener, LocationSource,
        AMapLocationListener, PoiSearch.OnPoiSearchListener, GeocodeSearch.OnGeocodeSearchListener, AMap.OnCameraChangeListener{


    private SwipeRefreshLayout swipeRefreshLayout;

    private LinearLayout ll_back_in_select_site;
    private ImageButton btn_back_in_select_site;
    private EditText et_search;
    private ImageButton ibtn_search;
    private ListView show_site;
    private String city;
    private Bundle mBundle;
    private AMap aMap;
    private MapView mapView;
    private AMapLocationClient mLocationClient = null;
    public AMapLocationClientOption mLocationOption = null;
    private LocationSource.OnLocationChangedListener mListener = null;
    private LatLng mFinalChoosePosition;
    private Marker locationMarker;
    private boolean isFirstLoc = true;
    private LatLng latLng = null;
    private LatLng latLng_change = null;
    private LatLonPoint lp;
    private String  addressName;
    private int currentPage;
    private PoiSearch.Query query;
    private PoiResult poiResult;
    private PoiSearch poiSearch;
    private List<PoiItem> poiItems;
    private ArrayList<SiteBean> mDatas = new ArrayList<>();
    private int flag = -1;
    private SiteAdapter siteAdapter;
    private GeocodeSearch geocoderSearch;
    private SiteBean firstLocation;
    private ImageButton btn_location;
    private String siteName = " ";
    private String location = " ";
    private double result_latitude;
    private double result_longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_site);
        mBundle = savedInstanceState;
        initView();
        location();
    }

    public void initView(){

        ll_back_in_select_site = (LinearLayout) findViewById(R.id.btn_back_in_select_site_layout);
        ll_back_in_select_site.setOnClickListener(this);
        btn_back_in_select_site = (ImageButton) findViewById(R.id.btn_back_in_select_site);
        btn_back_in_select_site.setOnClickListener(this);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_site);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorBlue);
        //swipeRefreshLayout.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(true);

        geocoderSearch = new GeocodeSearch(this);
        geocoderSearch.setOnGeocodeSearchListener(this);
        siteAdapter = new SiteAdapter(SelectSiteActivity.this);
        mapView = (MapView) findViewById(R.id.site_map);
        show_site = (ListView) findViewById(R.id.show_site);
        show_site.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                siteName = mDatas.get(position).getName();
                location = mDatas.get(position).getLocation();
                siteAdapter.changeState(position);
                mFinalChoosePosition = convertToLatLng(mDatas.get(position).getLatLonPoint());
                result_latitude = mFinalChoosePosition.latitude;
                result_longitude = mFinalChoosePosition.longitude;
                if(locationMarker != null){
                    locationMarker.remove();
                }
                aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mFinalChoosePosition.latitude, mFinalChoosePosition.longitude), 16));
                locationMarker =  aMap.addMarker(new MarkerOptions()
                        .anchor(0.5f, 0.5f)
                        .icon(BitmapDescriptorFactory
                                .fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.site_tag)))
                        .position(new LatLng(mFinalChoosePosition.latitude,mFinalChoosePosition.longitude)));
            }
        });
        show_site.setAdapter(siteAdapter);
        et_search = (EditText) findViewById(R.id.input_search_site);
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0){
                    flag = -1;
                    siteAdapter.changeState(-1);
                }else{
                    flag = 1;
                    siteAdapter.changeState(-1);
                    String search = s.toString().trim();
                    doSearchQueryWithKeyWord(search);
                    siteAdapter.setData(mDatas);

                }
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });
        ibtn_search = (ImageButton) findViewById(R.id.button_search_site);
        ibtn_search.setOnClickListener(this);

        mapView.onCreate(mBundle);
        if(aMap == null){
            aMap = mapView.getMap();

            UiSettings uiSettings = aMap.getUiSettings();
            aMap.setLocationSource(this);
            uiSettings.setMyLocationButtonEnabled(false);
            uiSettings.setZoomControlsEnabled(false);
            aMap.setMyLocationEnabled(true);
            MyLocationStyle myLocationStyle = new MyLocationStyle();
            myLocationStyle.strokeColor(Color.argb(0, 0, 0, 0));// 设置圆形的边框颜色
            myLocationStyle.radiusFillColor(Color.argb(0, 0, 0, 0));// 设置圆形的填充颜色
            myLocationStyle.strokeWidth(0);// 设置圆形的边框粗细
            myLocationStyle.showMyLocation(false);
            //myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.site_tag)));
            aMap.setMyLocationStyle(myLocationStyle);
        }
    }

    private void location() {
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(this);
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为Hight_Accuracy高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(false);
        //设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_back_in_select_site_layout:
            case R.id.btn_back_in_select_site:
                Intent intent = new Intent();
                intent.putExtra("latitude",result_latitude);
                intent.putExtra("longitude",result_longitude);
                intent.putExtra("name",siteName);
                intent.putExtra("location",location);
                setResult(RESULT_OK,intent);
                finish();
                break;
        }
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见官方定位类型表
                aMapLocation.getLatitude();//获取纬度
                aMapLocation.getLongitude();//获取经度
                latLng = new LatLng(aMapLocation.getLatitude(),aMapLocation.getLongitude());
                Log.d("位置",String.valueOf(latLng.latitude));
                getAddress(latLng);
                if(flag == -1 ){
                    doSearchQuery();
                    if(mDatas.size() != 0){
                        siteAdapter.setData(mDatas);
                    }
                }
                // doSearchQuery();
                //siteAdapter.setData(mDatas);
                // city_name.setText(aMapLocation.getCity().replace("市",""));
                aMapLocation.getAccuracy();//获取精度信息
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(aMapLocation.getTime());
                df.format(date);//定位时间
                aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                aMapLocation.getCountry();//国家信息
                aMapLocation.getProvince();//省信息
                aMapLocation.getCity();//城市信息
                city = aMapLocation.getCity();
                //city_name.setText(aMapLocation.getCity().replace("市", ""));
                aMapLocation.getDistrict();//城区信息
                aMapLocation.getStreet();//街道信息
                aMapLocation.getStreetNum();//街道门牌号信息
                aMapLocation.getCityCode();//城市编码
                aMapLocation.getAdCode();//地区编码

                // 如果不设置标志位，此时再拖动地图时，它会不断将地图移动到当前的位置
                if (isFirstLoc) {
                    //设置缩放级别
                    aMap.moveCamera(CameraUpdateFactory.zoomTo(17));
                    //将地图移动到定位点
                    aMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude())));
                    //点击定位按钮 能够将地图的中心移动到定位点
                    mListener.onLocationChanged(aMapLocation);
                    //添加图钉
                    //  aMap.addMarker(getMarkerOptions(amapLocation));
                    //获取定位信息
                    StringBuffer buffer = new StringBuffer();
                    buffer.append(aMapLocation.getCountry() + ""
                            + aMapLocation.getProvince() + ""
                            + aMapLocation.getCity() + ""
                            + aMapLocation.getProvince() + ""
                            + aMapLocation.getDistrict() + ""
                            + aMapLocation.getStreet() + ""
                            + aMapLocation.getStreetNum());
                    Toast.makeText(getApplicationContext(), buffer.toString(), Toast.LENGTH_LONG).show();
                    isFirstLoc = false;
                }


            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
                Toast.makeText(getApplicationContext(), "定位失败", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {

    }

    @Override
    public void onCameraChangeFinish(CameraPosition cameraPosition) {

    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
    }

    @Override
    public void deactivate() {

    }

    @Override
    public void onRegeocodeSearched(RegeocodeResult result, int rCode) {
        if (rCode == 1000) {
            if (result != null && result.getRegeocodeAddress() != null
                    && result.getRegeocodeAddress().getFormatAddress() != null) {
                addressName = result.getRegeocodeAddress().getFormatAddress(); // 逆转地里编码不是每次都可以得到对应地图上的opi
                firstLocation = new SiteBean(addressName,addressName,convertToLatLonPoint(latLng));
            } else {
                Toast.makeText(SelectSiteActivity.this,"转换出错",Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
    }

    @Override
    public void onPoiSearched(PoiResult result, int rcode) {
        if (rcode == 1000) {
            if (result != null && result.getQuery() != null) {// 搜索poi的结果
                if (result.getQuery().equals(query)) {// 是否是同一条
                    poiResult = result;
                    poiItems = poiResult.getPois();// 取得第一页的poiitem数据，页数从数字0开始
                    List<SuggestionCity> suggestionCities = poiResult
                            .getSearchSuggestionCitys();// 当搜索不到poiitem数据时，会返回含有搜索关键字的城市信息
                    mDatas.clear();
                    if(flag == -1){
                        mDatas.add(firstLocation);
                    }
                    //mDatas.add(firstLocation);
                    //if(isFirstLoadList || isBackFromSearchChoose){
                    // mDatas.add(mAddressEntityFirst);// 第一个元素
                    SiteBean siteBean = null;
                    for (PoiItem poiItem : poiItems) {
                        //  L.d("得到的数据 poiItem " + poiItem.getSnippet());
                        // Log.d("得到的数据",poiItem.getSnippet());
                        if(poiItem.getTitle() != null && poiItem.getSnippet() != null && poiItem.getLatLonPoint() != null){
                            siteBean = new SiteBean(poiItem.getTitle(), poiItem.getSnippet(),poiItem.getLatLonPoint());
                            mDatas.add(siteBean);
                        }
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            siteAdapter.notifyDataSetChanged();
                            swipeRefreshLayout.setRefreshing(false);
                            swipeRefreshLayout.setVisibility(View.INVISIBLE);
                        }
                    });
                    // if (isHandDrag) {
                    //      mDatas.get(0).isChoose = true;
                    //   }

                }
                //Log.d("大小",String.valueOf(mDatas.size()));
            } else {
                Toast.makeText(SelectSiteActivity.this,"没有数据",Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {
    }

    public void getAddress(final LatLng latLonPoint) {
        // 第一个参数表示一个Latlng，第二参数表示范围多少米，第三个参数表示是火系坐标系还是GPS原生坐标系
        RegeocodeQuery query = new RegeocodeQuery(convertToLatLonPoint(latLonPoint), 200, GeocodeSearch.AMAP);
        geocoderSearch.getFromLocationAsyn(query);// 设置同步逆地理编码请求
    }
    protected void doSearchQuery() {

        currentPage = 0;
        query = new PoiSearch.Query("", "");// 第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）
        query.setPageSize(30);// 设置每页最多返回多少条poiitem
        query.setPageNum(currentPage);// 设置查第一页

        LatLonPoint lpTemp = convertToLatLonPoint(latLng);

        if (lpTemp != null) {
            poiSearch = new PoiSearch(this, query);
            poiSearch.setOnPoiSearchListener(this);  // 实现  onPoiSearched  和  onPoiItemSearched
            poiSearch.setBound(new PoiSearch.SearchBound(lpTemp, 5000, true));//
            // 设置搜索区域为以lp点为圆心，其周围5000米范围
            poiSearch.searchPOIAsyn();// 异步搜索
        }
    }

    protected void doSearchQueryWithKeyWord(String key) {
        currentPage = 0;
        query = new PoiSearch.Query(key, "");// 第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）
        query.setPageSize(30);// 设置每页最多返回多少条poiitem
        query.setPageNum(currentPage);// 设置查第一页
        query.setCityLimit(true); //限定城市


        poiSearch = new PoiSearch(this, query);
        poiSearch.setOnPoiSearchListener(this);
        poiSearch.searchPOIAsyn();
       /* if (lp != null) {
            poiSearch = new PoiSearch(this, query);
            poiSearch.setOnPoiSearchListener(this);   // 实现  onPoiSearched  和  onPoiItemSearched
            poiSearch.setBound(new PoiSearch.SearchBound(lp, 5000, true));//
            // 设置搜索区域为以lp点为圆心，其周围5000米范围
            poiSearch.searchPOIAsyn();// 异步搜索
        }*/
    }

    public LatLng convertToLatLng(LatLonPoint latLonPoint) {
        return new LatLng(latLonPoint.getLatitude(), latLonPoint.getLongitude());
    }

    /**
     * 把LatLng对象转化为LatLonPoint对象
     */
    public static LatLonPoint convertToLatLonPoint(LatLng latlon) {
        return new LatLonPoint(latlon.latitude, latlon.longitude);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mapView.onDestroy();
        mLocationClient.stopLocation();//停止定位
        mLocationClient.onDestroy();//销毁定位客户端。
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，实现地图生命周期管理
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，实现地图生命周期管理
        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，实现地图生命周期管理
        mapView.onSaveInstanceState(outState);
    }
}
