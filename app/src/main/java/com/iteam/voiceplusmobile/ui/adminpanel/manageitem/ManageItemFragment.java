package com.iteam.voiceplusmobile.ui.adminpanel.manageitem;

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
import com.iteam.voiceplusmobile.ui.adminpanel.additemmanagement.AddItemManagementFragment;
import com.iteam.voiceplusmobile.ui.login.profile_update.ProfileUpdateFragment;

public class ManageItemFragment extends Fragment {

    private ManageItemViewModel mViewModel;

    public static ManageItemFragment newInstance() {
        return new ManageItemFragment();
    }
    private Button button;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_item_management, container, false);
        button=view.findViewById(R.id.add_item);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                AddItemManagementFragment addItemManagementFragment = new AddItemManagementFragment();
                transaction.replace(R.id.nav_host_fragment, addItemManagementFragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }

        });





        return view;


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ManageItemViewModel.class);
        // TODO: Use the ViewModel
    }

}