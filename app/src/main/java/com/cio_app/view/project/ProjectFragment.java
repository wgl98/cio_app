package com.cio_app.view.project;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.cio_app.R;
import com.cio_app.view.project.projectDetail.projectCompany.ProjectCompanyFragment;
import com.cio_app.view.project.projectDetail.projectCompany.adapter.ProjectCompanyAdapter;
import com.cio_app.view.project.projectDetail.projectPerson.ProjectPersonFragment;
import com.cio_app.view.project.projectSubmit.projectSubmitCompany.ProjectCompanySubmitActivity;
import com.cio_app.view.project.projectSubmit.projectSubmitPerson.ProjectPersonSubmitActivity;

public class ProjectFragment extends Fragment implements RadioGroup.OnCheckedChangeListener{

    private Context mContext;

    private RadioGroup radioGroup;

    private FragmentManager manager;

    private FragmentTransaction transaction;

    private ImageButton btn_add_project;

    public static Fragment newInstance() {
        ProjectFragment projectFragment = new ProjectFragment();
        return projectFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();

        manager = getFragmentManager();
        transaction = manager.beginTransaction();
        transaction.add(R.id.project_content_layout, ProjectCompanyFragment.newInstance());
        transaction.commit();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_project_layout,null);
        radioGroup = rootView.findViewById(R.id.project_title);
        radioGroup.setOnCheckedChangeListener(this);
        btn_add_project = rootView.findViewById(R.id.btn_add_project);
        btn_add_project.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProjectCompanySubmitActivity.start(mContext);
            }
        });
        return rootView;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        transaction = manager.beginTransaction();
        switch (checkedId){
            case R.id.project_title_company:
                transaction.replace(R.id.project_content_layout,ProjectCompanyFragment.newInstance());
                break;
            case R.id.project_title_person:
                transaction.replace(R.id.project_content_layout, ProjectPersonFragment.newInstance());
                break;
            default:
                break;
        }
        transaction.commit();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
