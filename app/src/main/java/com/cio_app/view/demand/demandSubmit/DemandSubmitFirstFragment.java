package com.cio_app.view.demand.demandSubmit;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cio_app.R;

public class DemandSubmitFirstFragment extends Fragment implements View.OnClickListener{

    private Context mContext;

    private RadioGroup radioGroup;
    private RadioButton radioButton_investigate_stage;
    private RadioButton radioButton_argue_stage;
    private RadioButton radioButton_project_stage;
    private RadioButton radioButton_call_stage;
    private RadioButton radioButton_implement_stage;
    private RadioButton radioButton_other_stage;

    private EditText et_number;      //会员编号

    private EditText et_other_maturity;

    private Button btn_to_next;      //进入下一步

    /*public static Fragment  newInstance() {
        DemandSubmitFirstFragment demandFragment = new DemandSubmitFirstFragment();
        return  demandFragment;
    }*/
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_demand_submit_first_layout,null);

        et_number = rootView.findViewById(R.id.demand_submit_content_number);

        radioButton_investigate_stage = (RadioButton) rootView.findViewById(R.id.demand_submit_content_investigate_stage);
        radioButton_argue_stage = (RadioButton) rootView.findViewById(R.id.demand_submit_content_argue_stage);
        radioButton_project_stage = (RadioButton) rootView.findViewById(R.id.demand_submit_content_project_stage);
        radioButton_call_stage = (RadioButton) rootView.findViewById(R.id.demand_submit_content_call_stage);
        radioButton_implement_stage = (RadioButton) rootView.findViewById(R.id.demand_submit_content_implement_stage);
        radioButton_other_stage = (RadioButton) rootView.findViewById(R.id.demand_submit_content_other_stage);

        et_other_maturity = (EditText) rootView.findViewById(R.id.demand_submit_content_other_maturity);
        btn_to_next = rootView.findViewById(R.id.btn_demand_submit_next);
        initRadioButton();

        return rootView;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    private void initRadioButton(){

        int width = dpToPx(mContext,316);
        // 这里设置的是radio button的高度
        int height = dpToPx(mContext, 21);

        LinearLayout.LayoutParams params4 = (LinearLayout.LayoutParams) radioButton_argue_stage.getLayoutParams();
        params4.setMargins(width / 3, -height, 0, 0);
        radioButton_argue_stage.setLayoutParams(params4);

        LinearLayout.LayoutParams params5 = (LinearLayout.LayoutParams) radioButton_project_stage.getLayoutParams();
        params5.setMargins((width / 3) * 2, -height, 0, 0);
        radioButton_project_stage.setLayoutParams(params5);

        LinearLayout.LayoutParams params6 = (LinearLayout.LayoutParams) radioButton_implement_stage.getLayoutParams();
        params6.setMargins(width / 3, -height, 0, 0);
        radioButton_implement_stage.setLayoutParams(params6);

        LinearLayout.LayoutParams params7 = (LinearLayout.LayoutParams) radioButton_other_stage.getLayoutParams();
        params7.setMargins((width / 3) * 2, -height, 0, 0);
        radioButton_other_stage.setLayoutParams(params7);

    }

    public static int dpToPx(final Context context, final float dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            default:
                break;
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }
}
