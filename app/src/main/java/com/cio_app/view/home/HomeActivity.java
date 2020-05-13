package com.cio_app.view.home;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.cio_app.R;
import com.cio_app.view.conference.ConferenceFragment;
import com.cio_app.view.demand.DemandFragment;
import com.cio_app.view.mine.MineActivity;
import com.cio_app.view.policy.PolicyFragment;
import com.cio_app.view.project.ProjectFragment;
import com.cio_app.view.talents.TalentsFragment;
import com.e.lib_common_ui.base.BaseActivity;
import com.e.lib_common_ui.circle_image_view.CircleImageView;

public class HomeActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private RadioGroup btn_group;

    private TextView title_text;

    private LinearLayout ll_to_mine_page;
    private CircleImageView ci_self_photo;

    private PolicyFragment policyFragment;
    private ConferenceFragment conferenceFragment;
    private DemandFragment demandFragment;
    private TalentsFragment talentsFragment;
    private ProjectFragment projectFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initView();

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        policyFragment = (PolicyFragment) PolicyFragment.newInstance();
        conferenceFragment = (ConferenceFragment) ConferenceFragment.newInstance();
        demandFragment = (DemandFragment) DemandFragment.newInstance();
        talentsFragment = (TalentsFragment) TalentsFragment.newInstance();
        projectFragment = (ProjectFragment) ProjectFragment.newInstance();

        fragmentTransaction.add(R.id.content_layout, policyFragment);
        fragmentTransaction.add(R.id.content_layout, conferenceFragment);
        fragmentTransaction.add(R.id.content_layout, demandFragment);
        fragmentTransaction.add(R.id.content_layout, talentsFragment);
        fragmentTransaction.add(R.id.content_layout, projectFragment);

        fragmentTransaction.show(policyFragment);
        fragmentTransaction.hide(conferenceFragment);
        fragmentTransaction.hide(demandFragment);
        fragmentTransaction.hide(talentsFragment);
        fragmentTransaction.hide(projectFragment);

        fragmentTransaction.commit();
    }

    /**
     * 初始化
     */
    public void initView(){

        title_text = (TextView) findViewById(R.id.title_text);
        title_text.setText("服务大厅");

        btn_group = (RadioGroup) findViewById(R.id.btn_group);
        btn_group.setOnCheckedChangeListener(this);

        ll_to_mine_page = findViewById(R.id.btn_to_mine_page_layout);
        ll_to_mine_page.setOnClickListener(this);
        ci_self_photo = findViewById(R.id.self_photo);
        ci_self_photo.setOnClickListener(this);

    }

    /**
     *响应按钮点击事件
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        fragmentTransaction = fragmentManager.beginTransaction();
        switch (checkedId){
            case R.id.btn_policy:
                fragmentTransaction.show(policyFragment);
                fragmentTransaction.hide(conferenceFragment);
                fragmentTransaction.hide(demandFragment);
                fragmentTransaction.hide(talentsFragment);
                fragmentTransaction.hide(projectFragment);
                title_text.setText("服务大厅");
                break;
            case R.id.btn_conference:
                fragmentTransaction.hide(policyFragment);
                fragmentTransaction.show(conferenceFragment);
                fragmentTransaction.hide(demandFragment);
                fragmentTransaction.hide(talentsFragment);
                fragmentTransaction.hide(projectFragment);
                title_text.setText("活动大厅");
                break;
            case R.id.btn_demand:
                fragmentTransaction.hide(policyFragment);
                fragmentTransaction.hide(conferenceFragment);
                fragmentTransaction.show(demandFragment);
                fragmentTransaction.hide(talentsFragment);
                fragmentTransaction.hide(projectFragment);
                title_text.setText("需求案例");
                break;
            case R.id.btn_talents:
                fragmentTransaction.hide(policyFragment);
                fragmentTransaction.hide(conferenceFragment);
                fragmentTransaction.hide(demandFragment);
                fragmentTransaction.show(talentsFragment);
                fragmentTransaction.hide(projectFragment);
                title_text.setText("人才服务");
                break;
            case R.id.btn_project:
                fragmentTransaction.hide(policyFragment);
                fragmentTransaction.hide(conferenceFragment);
                fragmentTransaction.hide(demandFragment);
                fragmentTransaction.hide(talentsFragment);
                fragmentTransaction.show(projectFragment);
                title_text.setText("服务大厅");
                break;
        }
        fragmentTransaction.commit();
    }

    /**
     * 响应个人中心的点击事件
     */

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_to_mine_page_layout:
            case R.id.self_photo:
                MineActivity.start(this);
                break;
        }
    }

}
