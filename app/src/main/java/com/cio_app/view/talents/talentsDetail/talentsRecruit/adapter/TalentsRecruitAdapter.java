package com.cio_app.view.talents.talentsDetail.talentsRecruit.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.cio_app.model.CHANNEL;
import com.cio_app.view.talents.talentsDetail.talentsRecruit.TalentsRecruitContentFragment;

public class TalentsRecruitAdapter extends FragmentPagerAdapter {
    private CHANNEL[] mList;

    public TalentsRecruitAdapter(FragmentManager fm, CHANNEL[] datas) {
        super(fm);
        mList = datas;
    }

    @Override
    public Fragment getItem(int position) {
        int type = mList[position].getValue();
        switch (type) {
            case CHANNEL.ALL_ID:
                return TalentsRecruitContentFragment.newInstance(CHANNEL.ALL_ID);
            case CHANNEL.UI_ENGINEER_ID:
                return TalentsRecruitContentFragment.newInstance(CHANNEL.UI_ENGINEER_ID);
            case CHANNEL.SOFTWARE_ENGINEER_ID:
                return TalentsRecruitContentFragment.newInstance(CHANNEL.SOFTWARE_ENGINEER_ID);
            case CHANNEL.SYSTEM_ENGINEER_ID:
                return TalentsRecruitContentFragment.newInstance(CHANNEL.SYSTEM_ENGINEER_ID);
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
