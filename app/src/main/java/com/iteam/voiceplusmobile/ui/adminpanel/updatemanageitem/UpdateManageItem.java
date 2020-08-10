package com.iteam.voiceplusmobile.ui.adminpanel.updatemanageitem;

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
import com.iteam.voiceplusmobile.ui.adminpanel.editdeleteitem.EditDeleteItemFragment;

public class UpdateManageItem extends Fragment {
    EditText companyname,modelname,productname,productprice,productdescription,productimage;
    Button CancelButton,UpdateButton,ChooseFileButton;
    private UpdateManageItemViewModel mViewModel;

    public static UpdateManageItem newInstance() {
        return new UpdateManageItem();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_update_manage_item, container, false);
        companyname=view.findViewById(R.id.admin_update_item_brand);
        modelname=view.findViewById(R.id.admin_update_item_model);
        productname=view.findViewById(R.id.admin_update_item_product_name);
        productprice=view.findViewById(R.id.admin_update_item_price);
        productdescription=view.findViewById(R.id.admin_update_repairing_description);
        productimage=view.findViewById(R.id.txt_update_choosefile);
        UpdateButton=view.findViewById(R.id.btn_admin_Update_item);
        ChooseFileButton=view.findViewById(R.id.btn_update_chooseimage);
        CancelButton=view.findViewById(R.id.btn_cancel_item);

        CancelButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                EditDeleteItemFragment editDeleteItemFragment = new EditDeleteItemFragment();
                transaction.replace(R.id.frame, editDeleteItemFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }

        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(UpdateManageItemViewModel.class);
        // TODO: Use the ViewModel
    }

}