package com.iteam.voiceplusmobile.ui.adminpanel.editdeleteitem;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iteam.voiceplusmobile.HelperContent;
import com.iteam.voiceplusmobile.R;
import com.iteam.voiceplusmobile.ui.pricing.CustomListDataModel;

public class EditDeleteItemFragment extends Fragment {

    private EditDeleteItemViewModel mViewModel;

    public static EditDeleteItemFragment newInstance() {
        return new EditDeleteItemFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentedit_edit_delete_item, container, false);

        CustomListDataModel selected_item = HelperContent.getCustomListDataModel();
        TextView manageItem_company_name = view.findViewById(R.id.manageItem_company_name);
        manageItem_company_name.setText(selected_item.getMobile_company());

        TextView manageItem_model_name = view.findViewById(R.id.manageItem_model_name);
        manageItem_model_name.setText(selected_item.getPhone_model_name());

        ImageView manageItem_image = view.findViewById(R.id.manageItem_image);
        manageItem_image.setImageResource(selected_item.getImage_id());

        TextView manageItem_part_name = view.findViewById(R.id.manageItem_part_name);
        manageItem_part_name.setText(selected_item.getRepair_part_name());


        TextView manageItem_price = view.findViewById(R.id.manageItem_price);
        manageItem_price.setText(""+selected_item.getRepairing_price());


        TextView manageItem_details= view.findViewById(R.id.manageItem_details);
        manageItem_details.setText(selected_item.getRepairing_description());

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(EditDeleteItemViewModel.class);
        // TODO: Use the ViewModel
    }

}