package com.cio_app.view.conference;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.cio_app.R;
import com.cio_app.view.conference.conferenceHandling.ConferenceHandleFragment;
import com.cio_app.view.conference.conferenceSigning.ConferenceSignFragment;
import com.cio_app.view.conference.conferenceFinished.ConferenceFinishedFragment;

public class ConferenceFragment extends Fragment implements RadioGroup.OnCheckedChangeListener{

    private Context mContext;

    private FragmentManager manager;

    private FragmentTransaction transaction;

    private RadioGroup radioGroup;

    public static Fragment newInstance() {
        ConferenceFragment conferenceFragment = new ConferenceFragment();
        return conferenceFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();

        manager = getFragmentManager();
        transaction = manager.beginTransaction();
        transaction.add(R.id.conference_content_layout, ConferenceSignFragment.newInstance());
        transaction.commit();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_conference_layout,null);
        radioGroup = (RadioGroup) rootView.findViewById(R.id.conference_title);
        radioGroup.setOnCheckedChangeListener(this);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        transaction = manager.beginTransaction();
        switch (checkedId) {
            case R.id.conference_title_signing:
                transaction.replace(R.id.conference_content_layout, ConferenceSignFragment.newInstance());
                break;
            case R.id.conference_title_handing:
                transaction.replace(R.id.conference_content_layout, ConferenceHandleFragment.newInstance());
                break;
            case R.id.conference_title_finish:
                transaction.replace(R.id.conference_content_layout, ConferenceFinishedFragment.newInstance());
                break;
        }
        transaction.commit();
    }

}
