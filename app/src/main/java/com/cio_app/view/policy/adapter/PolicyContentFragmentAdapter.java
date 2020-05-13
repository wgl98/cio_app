package com.cio_app.view.policy.adapter;

import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.cio_app.model.policy.policyCategory.PolicySecondCategoryModelBodyValue;
import com.cio_app.view.policy.policyContent.PolicyContentFragment;

import java.util.ArrayList;
import java.util.List;

public class PolicyContentFragmentAdapter extends FragmentPagerAdapter {

    private List<PolicySecondCategoryModelBodyValue> titleList;
    private int parentCategory;

    public PolicyContentFragmentAdapter(FragmentManager fm, int category) {
        super(fm);
        this.parentCategory = category;
        titleList = new ArrayList<>();
    }

    public void setData(List list){
        titleList = list;
        notifyDataSetChanged();
    }

    //这种方式，避免一次性创建所有的framgent
    @Override
    public Fragment getItem(int position) {
        int childrenCategory = titleList.get(position).id;
        Log.d("类型",String.valueOf(childrenCategory));
        return PolicyContentFragment.newInstance(parentCategory,childrenCategory);
    }

    @Override
    public int getCount() {
        return titleList == null ? 0 : titleList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String plateName = titleList.get(position).categoryName;
        if (plateName == null) {
            plateName = "";
        } else if (plateName.length() > 15) {
            plateName = plateName.substring(0, 15) + "...";
        }
        return plateName;
    }
}
