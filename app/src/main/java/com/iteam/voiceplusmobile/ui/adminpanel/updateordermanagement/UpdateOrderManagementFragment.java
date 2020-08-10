package com.iteam.voiceplusmobile.ui.adminpanel.updateordermanagement;

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
import android.widget.EditText;

import com.iteam.voiceplusmobile.R;
import com.iteam.voiceplusmobile.ui.adminpanel.editdelelteordermanagement.EditDeleteOrderManagementFragment;
import com.iteam.voiceplusmobile.ui.adminpanel.editdeleteitem.EditDeleteItemFragment;

public class UpdateOrderManagementFragment extends Fragment {

    private UpdateOrderManagementViewModel mViewModel;
    EditText CustomerName,MobileNumber,Address,Password,MobileCompany,MobileModel,MobileFault,RepairingCharges,FaultDescription,OrderStatus;
    Button UpdateOrderDetails,CancelUpdateOrder;
    public static UpdateOrderManagementFragment newInstance() {
        return new UpdateOrderManagementFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_update_order_management, container, false);
        CustomerName=view.findViewById(R.id.txt_customername);
        MobileNumber=view.findViewById(R.id.txt_mobilenumber);
        Address=view.findViewById(R.id.txt_address);
        Password=view.findViewById(R.id.txt_password);
        MobileCompany=view.findViewById(R.id.txt_mobilecompany);
        MobileModel=view.findViewById(R.id.txt_mobilemodel);
        MobileFault=view.findViewById(R.id.txt_mobilefault);
        RepairingCharges=view.findViewById(R.id.txt_repairingcharges);
        FaultDescription=view.findViewById(R.id.txt_faultdescription);
        OrderStatus=view.findViewById(R.id.txt_orderstatus);
        UpdateOrderDetails=view.findViewById(R.id.btn_admin_update_order);
        CancelUpdateOrder=view.findViewById(R.id.btn_admin_order_update_cancel);

        CancelUpdateOrder.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                EditDeleteOrderManagementFragment editDeleteOrderManagementFragment = new EditDeleteOrderManagementFragment();
                transaction.replace(R.id.frame, editDeleteOrderManagementFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }

        });
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(UpdateOrderManagementViewModel.class);
        // TODO: Use the ViewModel
    }

}