package com.cio_app.view.project.projectSubmit.projectSubmitCompany;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cio_app.R;

public class ProjectCompanySubmitFirstFragment extends Fragment {

    private Context mContext;

    private RadioButton rb_type_OA;
    private RadioButton rb_type_HR;
    private RadioButton rb_type_CRM;
    private RadioButton rb_type_SRM;
    private RadioButton rb_type_ERP;
    private RadioButton rb_type_BI;
    private RadioButton rb_type_MES;
    private RadioButton rb_type_APS;
    private RadioButton rb_type_BPM;
    private RadioButton rb_type_WMS;
    private RadioButton rb_type_safe;
    private RadioButton rb_type_contacts;
    private RadioButton rb_type_data;
    private RadioButton rb_type_cloud;
    private RadioButton rb_type_office;
    private RadioButton rb_type_consult;
    private RadioButton rb_type_AI;
    private RadioButton rb_type_develop;
    private RadioButton rb_type_other;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_project_company_submit_first_layout,null);

        rb_type_OA = rootView.findViewById(R.id.project_company_submit_content_type_OA);
        rb_type_HR = rootView.findViewById(R.id.project_company_submit_content_type_HR);
        rb_type_CRM = rootView.findViewById(R.id.project_company_submit_content_type_CRM);
        rb_type_SRM = rootView.findViewById(R.id.project_company_submit_content_type_SRM);
        rb_type_ERP = rootView.findViewById(R.id.project_company_submit_content_type_ERP);
        rb_type_BI = rootView.findViewById(R.id.project_company_submit_content_type_BI);
        rb_type_MES = rootView.findViewById(R.id.project_company_submit_content_type_MES);
        rb_type_BPM = rootView.findViewById(R.id.project_company_submit_content_type_BPM);
        rb_type_WMS = rootView.findViewById(R.id.project_company_submit_content_type_WMS);
        rb_type_APS = rootView.findViewById(R.id.project_company_submit_content_type_APS);
        rb_type_safe = rootView.findViewById(R.id.project_company_submit_content_type_safe);
        rb_type_contacts = rootView.findViewById(R.id.project_company_submit_content_type_contacts);
        rb_type_data = rootView.findViewById(R.id.project_company_submit_content_type_data);
        rb_type_cloud = rootView.findViewById(R.id.project_company_submit_content_type_cloud);
        rb_type_office = rootView.findViewById(R.id.project_company_submit_content_type_office);
        rb_type_consult = rootView.findViewById(R.id.project_company_submit_content_type_consult);
        rb_type_AI = rootView.findViewById(R.id.project_company_submit_content_type_AI);
        rb_type_develop = rootView.findViewById(R.id.project_company_submit_content_type_develop);
        rb_type_other = rootView.findViewById(R.id.project_company_submit_content_type_other);

        initRadioButton();

        return rootView;
    }


    private void initRadioButton(){
        int width = dpToPx(mContext,316);
        // 这里设置的是radio button的高度
        int height = dpToPx(mContext, 21);

        LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) rb_type_HR.getLayoutParams();
        params1.setMargins(width / 3, -height, 0, 0);
        rb_type_HR.setLayoutParams(params1);

        LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) rb_type_CRM.getLayoutParams();
        params2.setMargins((width / 3) * 2, -height, 0, 0);
        rb_type_CRM.setLayoutParams(params2);

        LinearLayout.LayoutParams params3 = (LinearLayout.LayoutParams) rb_type_ERP.getLayoutParams();
        params3.setMargins(width / 3, -height, 0, 0);
        rb_type_ERP.setLayoutParams(params3);

        LinearLayout.LayoutParams params4 = (LinearLayout.LayoutParams) rb_type_BI.getLayoutParams();
        params4.setMargins((width / 3) * 2, -height, 0, 0);
        rb_type_BI.setLayoutParams(params4);

        LinearLayout.LayoutParams params5 = (LinearLayout.LayoutParams) rb_type_BPM.getLayoutParams();
        params5.setMargins(width / 3, -height, 0, 0);
        rb_type_BPM.setLayoutParams(params5);

        LinearLayout.LayoutParams params6 = (LinearLayout.LayoutParams) rb_type_WMS.getLayoutParams();
        params6.setMargins((width / 3) * 2, -height, 0, 0);
        rb_type_WMS.setLayoutParams(params6);

        LinearLayout.LayoutParams params7 = (LinearLayout.LayoutParams) rb_type_safe.getLayoutParams();
        params7.setMargins(width / 3, -height, 0, 0);
        rb_type_safe.setLayoutParams(params7);

        LinearLayout.LayoutParams params8 = (LinearLayout.LayoutParams) rb_type_contacts.getLayoutParams();
        params8.setMargins((width / 3) * 2, -height, 0, 0);
        rb_type_contacts.setLayoutParams(params8);

        LinearLayout.LayoutParams params9 = (LinearLayout.LayoutParams) rb_type_cloud.getLayoutParams();
        params9.setMargins(width / 3, -height, 0, 0);
        rb_type_cloud.setLayoutParams(params9);

        LinearLayout.LayoutParams params10 = (LinearLayout.LayoutParams) rb_type_office.getLayoutParams();
        params10.setMargins((width / 3) * 2, -height, 0, 0);
        rb_type_office.setLayoutParams(params10);

        LinearLayout.LayoutParams params11 = (LinearLayout.LayoutParams) rb_type_AI.getLayoutParams();
        params11.setMargins(width / 3, -height, 0, 0);
        rb_type_AI.setLayoutParams(params11);

        LinearLayout.LayoutParams params12 = (LinearLayout.LayoutParams) rb_type_other.getLayoutParams();
        params12.setMargins(width / 3, -height, 0, 0);
        rb_type_other.setLayoutParams(params12);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    public static int dpToPx(final Context context, final float dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }
}
