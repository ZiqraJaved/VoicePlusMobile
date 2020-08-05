package com.iteam.voiceplusmobile.ui.adminpanel.additemmanagement;

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
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.iteam.voiceplusmobile.R;
import com.iteam.voiceplusmobile.ui.adminpanel.manageitem.ManageItemFragment;

public class AddItemManagementFragment extends Fragment {

    private AddItemManagementViewModel mViewModel;
    private Button button;
    private final String rootUrl = "http://127.0.0.1:8000/api/pricing/add_new_item/";
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
        final EditText repairing_part = view.findViewById(R.id.admin_item_product_name);
        final EditText repairing_description = view.findViewById(R.id.admin_repairing_description);
        final EditText repairing_price = view.findViewById(R.id.admin_item_price);

        button = view.findViewById(R.id.btn_add_item_cancel);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

//                FragmentManager fragmentManager = getFragmentManager();
//                FragmentTransaction transaction = fragmentManager.beginTransaction();
//
//                ManageItemFragment manageItemFragment = new ManageItemFragment(new ManageItemFragment.ClickListener() {
//                });
//                transaction.replace(R.id.nav_host_fragment, manageItemFragment);
//                transaction.addToBackStack(null);
//                transaction.commit();

            }

        });

        Button admin_add_item = view.findViewById(R.id.admin_add_item);
        admin_add_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(rootUrl)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                addItemServices = retrofit.create(AddItemServices.class);
//                @TODO: Add checks to validate all entries

                String _mobile_company = mobile_company.getText().toString();
                String _mobile_model = mobile_model.getText().toString();
                String _repairing_part = repairing_part.getText().toString();
                String _repairing_description = repairing_description.getText().toString();
                String _repairing_price = repairing_price.getText().toString();
                add_new_item_fu(_mobile_company, _mobile_model, _repairing_part, _repairing_description, _repairing_price);
//# fields = ['mobile_company', 'mobile_model', 'repairing_part', 'repairing_price', 'repairing_description']
            }
        });

        return view;
    }

    private void add_new_item_fu(String mobile_company, String mobile_model, String repairing_part, String repairing_description, String repairing_price) {

        AddItem addItem = new AddItem();
        addItem.setMobile_company(mobile_company);
        addItem.setMobile_model(mobile_model);
        addItem.setRepairing_part(repairing_part);
        addItem.setRepairing_description(repairing_description);
        addItem.setRepairing_price(Integer.parseInt(repairing_price));


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
                    String details = response.body().getDetail();
                System.out.println(details);
            }

            @Override
            public void onFailure(Call<AddItem> call, Throwable t) {
                progressDoalog.dismiss();
                System.out.print("Failed to process request");
                System.out.println(t.getStackTrace());
                System.out.println(t.getCause());
            }
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AddItemManagementViewModel.class);
        // TODO: Use the ViewModel
    }

}