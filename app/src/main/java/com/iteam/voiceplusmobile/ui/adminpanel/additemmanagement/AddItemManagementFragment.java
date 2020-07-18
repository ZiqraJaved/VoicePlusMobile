package com.iteam.voiceplusmobile.ui.adminpanel.additemmanagement;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.iteam.voiceplusmobile.R;
import com.iteam.voiceplusmobile.ui.adminpanel.manageitem.ManageItemFragment;

public class AddItemManagementFragment extends Fragment {

    private AddItemManagementViewModel mViewModel;
    private Button button;
    public static AddItemManagementFragment newInstance() {
        return new AddItemManagementFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,

                             @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_add_item_management, container, false);
        button=view.findViewById(R.id.btn_add_item_cancel);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                ManageItemFragment manageItemFragment = new ManageItemFragment();
                transaction.replace(R.id.nav_host_fragment, manageItemFragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }

        });



        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AddItemManagementViewModel.class);
        // TODO: Use the ViewModel
    }

}