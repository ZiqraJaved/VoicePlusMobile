package com.iteam.voiceplusmobile.ui.adminpanel.editdelelteordermanagement;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.iteam.voiceplusmobile.HelperContent;
import com.iteam.voiceplusmobile.R;
import com.iteam.voiceplusmobile.ui.adminpanel.editdeleteitem.EditDeleteItemFragment;
import com.iteam.voiceplusmobile.ui.adminpanel.ordermanagement.CustomOrderModel;
import com.iteam.voiceplusmobile.ui.adminpanel.updateordermanagement.UpdateOrderManagementFragment;
import com.iteam.voiceplusmobile.ui.pricing.CustomListDataModel;
import com.squareup.okhttp.ResponseBody;

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

        Button EditOrder = view.findViewById(R.id.btn_admin_order_edit);
        EditOrder.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                UpdateOrderManagementFragment updateOrderManagementFragment = new UpdateOrderManagementFragment();
                transaction.replace(R.id.frame, updateOrderManagementFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }

        });

        Button DeleteOrder = view.findViewById(R.id.btn_admin_order_delete);
        DeleteOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog progressDoalog;
                progressDoalog = new ProgressDialog(getContext());
                progressDoalog.setMessage("Login your account into application.");
                progressDoalog.setTitle("Please Wait");
                progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDoalog.show();
                ItemService mService = null;
//                CustomListDataModel obj = HelperContent.getCustomListDataModel();
//                Call<ResponseBody> deleteRequest = mService.deleteItem(obj.id);
//                deleteRequest.enqueue(new Callback<ResponseBody>() {
//                    @Override
//                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                        // use response.code, response.headers, etc.
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResponseBody> call, Throwable t) {
//                        // handle failure
//                    }
//                });
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(EditDeleteOrderManagementViewModel.class);
        // TODO: Use the ViewModel
    }

}