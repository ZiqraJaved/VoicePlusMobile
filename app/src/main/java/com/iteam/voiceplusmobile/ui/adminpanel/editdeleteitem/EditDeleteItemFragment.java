package com.iteam.voiceplusmobile.ui.adminpanel.editdeleteitem;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iteam.voiceplusmobile.R;

public class EditDeleteItemFragment extends Fragment {

    private EditDeleteItemViewModel mViewModel;

    public static EditDeleteItemFragment newInstance() {
        return new EditDeleteItemFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmentedit_edit_delete_item, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(EditDeleteItemViewModel.class);
        // TODO: Use the ViewModel
    }

}