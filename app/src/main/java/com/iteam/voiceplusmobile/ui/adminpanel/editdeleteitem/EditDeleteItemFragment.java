package com.iteam.voiceplusmobile.ui.adminpanel.editdeleteitem;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.iteam.voiceplusmobile.HelperContent;
import com.iteam.voiceplusmobile.R;
import com.iteam.voiceplusmobile.ui.adminpanel.manageitem.PricingSchema;
import com.iteam.voiceplusmobile.ui.adminpanel.updatemanageitem.UpdateManageItem;
import com.iteam.voiceplusmobile.ui.pricing.CustomListDataModel;

public class EditDeleteItemFragment extends Fragment {

    private EditDeleteItemViewModel mViewModel;
    //    public static final String API_URL = "https://voice-plus-mobile.herokuapp.com/api/pricing/";
    private UserService userService;
    private ProgressDialog mProgressDialog;
    private final String PricingUrl = "https://voice-plus-mobile.herokuapp.com/api/pricing/";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentedit_edit_delete_item, container, false);

        final CustomListDataModel selected_item = HelperContent.getCustomListDataModel();
        TextView manageItem_company_name = view.findViewById(R.id.admin_view_item_brand);
        manageItem_company_name.setText(selected_item.getMobile_company());

        TextView manageItem_model_name = view.findViewById(R.id.admin_view_item_model);
        manageItem_model_name.setText(selected_item.getPhone_model_name());

        ImageView manageItem_image = view.findViewById(R.id.admin_view_item_image);
        manageItem_image.setImageResource(selected_item.getImage_id());

        TextView manageItem_part_name = view.findViewById(R.id.admin_view_item_product_name);
        manageItem_part_name.setText(selected_item.getRepair_part_name());


        TextView manageItem_price = view.findViewById(R.id.admin_view_item_price);
        manageItem_price.setText("" + selected_item.getRepairing_price());


        final TextView manageItem_details = view.findViewById(R.id.admin_view_repairing_description);
        manageItem_details.setText(selected_item.getRepairing_description());

        Button EditItem = view.findViewById(R.id.btn_admin_edit_item);
        Button btn_admin_delete_Item = view.findViewById(R.id.btn_admin_delete_Item);


        EditItem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                UpdateManageItem updateManageItem = new UpdateManageItem();
                transaction.replace(R.id.frame, updateManageItem);
                transaction.addToBackStack(null);
                transaction.commit();
            }

        });

        btn_admin_delete_Item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Delete Item");
                builder.setMessage("Are you sure to delete record?");
                builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(PricingUrl)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                        userService = retrofit.create(UserService.class);
                        final ProgressDialog progressDoalog;
                        progressDoalog = new ProgressDialog(getContext());
                        progressDoalog.setMessage("Login your account into application.");
                        progressDoalog.setTitle("Please Wait");
                        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        progressDoalog.show();
                        Call<PricingSchema> call = userService.deleteItem(manageItem_details.getId());
                        call.enqueue(new Callback<PricingSchema>() {

                            @Override
                            public void onResponse(Call<PricingSchema> call, Response<PricingSchema> response) {
                                System.out.println(response.code());
                                progressDoalog.dismiss();
                            }

                            @Override
                            public void onFailure(Call<PricingSchema> call, Throwable t) {
                                System.out.println(t.getMessage());
                                progressDoalog.dismiss();
                            }
                        });
                    }
                });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //TODO
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(EditDeleteItemViewModel.class);
        // TODO: Use the ViewModel
    }

}