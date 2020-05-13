package com.cio_app.view.talents.talentsDetail.talentsMarket.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.cio_app.model.CHANNEL;
import com.cio_app.view.talents.talentsDetail.talentsMarket.TalentsMarketContentFragment;

public class TalentsMarketAdapter extends FragmentPagerAdapter {

    private CHANNEL[] mList;

    public TalentsMarketAdapter(FragmentManager fm, CHANNEL[] datas) {
        super(fm);
        mList = datas;
    }

    @Override
    public Fragment getItem(int position) {
        int type = mList[position].getValue();
        switch (type) {
            case CHANNEL.ALL_ID:
                return TalentsMarketContentFragment.newInstance(CHANNEL.ALL_ID);
            case CHANNEL.MANUFACTURE_INDUSTRY_ID:
                return TalentsMarketContentFragment.newInstance(CHANNEL.MANUFACTURE_INDUSTRY_ID);
            case CHANNEL.RETAIL_INDUSTRY_ID:
                return TalentsMarketContentFragment.newInstance(CHANNEL.RETAIL_INDUSTRY_ID);
            case CHANNEL.BUILDING_INDUSTRY_ID:
                return TalentsMarketContentFragment.newInstance(CHANNEL.BUILDING_INDUSTRY_ID);
            case CHANNEL.FINANCIAL_INDUSTRY_ID:
                return TalentsMarketContentFragment.newInstance(CHANNEL.FINANCIAL_INDUSTRY_ID);
        }
        return null;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String plateName = mList[position].getKey();
        if (plateName == null) {
            plateName = "";
        } else if (plateName.length() > 15) {
            plateName = plateName.substring(0, 15) + "...";
        }
        return plateName;
    }
}
