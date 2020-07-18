package com.iteam.voiceplusmobile.ui.adminpanel.ordermanagement;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iteam.voiceplusmobile.R;

public class OrderManagementFragment extends Fragment {

    private OrderManagementViewModel mViewModel;

    public static OrderManagementFragment newInstance() {
        return new OrderManagementFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_management, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(OrderManagementViewModel.class);
        // TODO: Use the ViewModel
    }

}