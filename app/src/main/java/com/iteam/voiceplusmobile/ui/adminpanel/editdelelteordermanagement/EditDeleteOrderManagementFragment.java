package com.iteam.voiceplusmobile.ui.adminpanel.editdelelteordermanagement;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iteam.voiceplusmobile.R;

public class EditDeleteOrderManagementFragment extends Fragment {

    private EditDeleteOrderManagementViewModel mViewModel;

    public static EditDeleteOrderManagementFragment newInstance() {
        return new EditDeleteOrderManagementFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_delete_order_management, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(EditDeleteOrderManagementViewModel.class);
        // TODO: Use the ViewModel
    }

}