package com.iteam.voiceplusmobile.ui.adminpanel.additemmanagement;

import androidx.lifecycle.ViewModelProviders;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.iteam.voiceplusmobile.R;

public class AddItemManagementFragment extends Fragment {

    private AddItemManagementViewModel mViewModel;
    private Button btnCancel, choosefile;
    private final String rootUrl = "https://voice-plus-mobile.herokuapp.com/api/pricing/";
    private AddItemServices addItemServices;

    public static AddItemManagementFragment newInstance() {
        return new AddItemManagementFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,

                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_item_management, container, false);
        final EditText mobile_company = view.findViewById(R.id.admin_item_brand);
        final EditText mobile_model = view.findViewById(R.id.admin_item_model);
        final Button admin_add_item;
        final EditText repairing_part = view.findViewById(R.id.admin_item_product_name);
        final EditText repairing_description = view.findViewById(R.id.admin_repairing_description);
        final EditText repairing_price = view.findViewById(R.id.admin_item_price);
        final TextView imagefile = view.findViewById(R.id.admin_txt_image_file);
        btnCancel = view.findViewById(R.id.btn_add_item_cancel);
        choosefile = view.findViewById(R.id.btn_admin_choose_file);


        btnCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

//                FragmentManager fragmentManager = getFragmentManager();
//                FragmentTransaction transaction = fragmentManager.beginTransaction();
//
//                ManageItemFragment manageItemFragment = new ManageItemFragment(new ManageItemFragment.ClickListener() {
//                Intent intent = new Intent(getActivity().getApplication(), ManageItemFragment.class);
//                startActivity(intent);
//                });
//                transaction.replace(R.id.frame, manageItemFragment);
//                transaction.addToBackStack(null);
//                transaction.commit();

            }

        });

        admin_add_item = view.findViewById(R.id.admin_add_item);
        admin_add_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mobile_company.getText().toString() == null || mobile_company.getText().toString().trim().equals("")) {
                    Toast.makeText(getActivity().getBaseContext(), "Please enter Company Name!", Toast.LENGTH_SHORT).show();

                } else if (mobile_model.getText().toString() == null || mobile_model.getText().toString().trim().equals("")) {
                    Toast.makeText(getActivity().getBaseContext(), "Please enter mobile model!", Toast.LENGTH_SHORT).show();
                } else if (repairing_part.getText().toString() == null || repairing_part.getText().toString().trim().equals("")) {
                    Toast.makeText(getActivity().getBaseContext(), "Please enter mobile model!", Toast.LENGTH_SHORT).show();
                } else if (repairing_description.getText().toString() == null || repairing_description.getText().toString().trim().equals("")) {
                    Toast.makeText(getActivity().getBaseContext(), "Please enter mobile model!", Toast.LENGTH_SHORT).show();
                } else if (repairing_price.getText().toString() == null || repairing_price.getText().toString().trim().equals("")) {
                    Toast.makeText(getActivity().getBaseContext(), "Please enter mobile model!", Toast.LENGTH_SHORT).show();

                } else {

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(rootUrl)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    addItemServices = retrofit.create(AddItemServices.class);
                    String _mobile_company = mobile_company.getText().toString();
                    String _mobile_model = mobile_model.getText().toString();
                    String _repairing_part = repairing_part.getText().toString();
                    String _repairing_description = repairing_description.getText().toString();
                    String _repairing_price = repairing_price.getText().toString();
                    add_new_item_fu(_mobile_company, _mobile_model, _repairing_part, _repairing_description, _repairing_price);
                }
            }
        });

        return view;
    }

    private void add_new_item_fu(String mobile_company, String mobile_model, String repairing_part, String repairing_description, String repairing_price) {
        try {

            AddItem addItem = new AddItem();

            addItem.setMobileCompany(mobile_company);
            addItem.setMobileModel(mobile_model);
            addItem.setRepairingPart(repairing_part);
            addItem.setRepairingDescription(repairing_description);
            addItem.setRepairingPrice(Integer.parseInt(repairing_price));


            final ProgressDialog progressDoalog;
            progressDoalog = new ProgressDialog(getContext());
            progressDoalog.setMessage("Login your account into application.");
            progressDoalog.setTitle("Please Wait");
            progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDoalog.show();

            Call<AddItem> call = addItemServices.addNewItem(addItem);

            call.enqueue(new Callback<AddItem>() {
                @Override
                public void onResponse(Call<AddItem> call, Response<AddItem> response) {
                    progressDoalog.dismiss();
                    Toast.makeText(getActivity().getBaseContext(), "Item has added successfully.", Toast.LENGTH_LONG).show();

                }

                @Override
                public void onFailure(Call<AddItem> call, Throwable t) {
                    progressDoalog.dismiss();
                    Toast.makeText(getActivity().getBaseContext(), "Unable to process your request.", Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception exp) {
            Toast.makeText(getActivity().getBaseContext(), "Unable to process your request..", Toast.LENGTH_LONG).show();

        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AddItemManagementViewModel.class);
        // TODO: Use the ViewModel
    }

}