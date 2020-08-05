package com.iteam.voiceplusmobile.ui.adminpanel.editdelelteordermanagement;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iteam.voiceplusmobile.HelperContent;
import com.iteam.voiceplusmobile.R;
import com.iteam.voiceplusmobile.ui.adminpanel.ordermanagement.CustomOrderModel;

public class EditDeleteOrderManagementFragment extends Fragment {

    private EditDeleteOrderManagementViewModel mViewModel;

    public static EditDeleteOrderManagementFragment newInstance() {
        return new EditDeleteOrderManagementFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_delete_order_management, container, false);
        CustomOrderModel selectedOrder = HelperContent.getCustomOrderModel();

        TextView edit_delete_order_id = view.findViewById(R.id.edit_delete_order_id);
        edit_delete_order_id.setText("Order Number:\t" + selectedOrder.getId());

        TextView edit_delete_phone_number = view.findViewById(R.id.edit_delete_phone_number);
        edit_delete_phone_number.setText("Phone Number:\t" + selectedOrder.getUserPhoneNumber());

        TextView edit_delete_order_mobile_model = view.findViewById(R.id.edit_delete_order_mobile_model);
        edit_delete_order_mobile_model.setText("Phone Model:\t" + selectedOrder.getMobileModel());

        TextView edit_delete_order_mobile_brand = view.findViewById(R.id.edit_delete_order_mobile_brand);
        edit_delete_order_mobile_brand.setText("Phone Brand:\t" + selectedOrder.getMobileBrand());

        TextView edit_mobile_fault = view.findViewById(R.id.edit_mobile_fault);
        edit_mobile_fault.setText("Phone Fault:\t" + selectedOrder.getMobileFault());

        TextView edit_delete_charges = view.findViewById(R.id.edit_delete_charges);
        edit_delete_charges.setText("Phone Chargers:\t" + selectedOrder.getCharges());

        TextView edit_delete_order_status = view.findViewById(R.id.edit_delete_order_status);
        edit_delete_order_status.setText("Phone Status:\t" + selectedOrder.getOrderStatus());


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(EditDeleteOrderManagementViewModel.class);
        // TODO: Use the ViewModel
    }

}