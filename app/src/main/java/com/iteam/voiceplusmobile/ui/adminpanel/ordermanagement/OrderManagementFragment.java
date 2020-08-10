package com.iteam.voiceplusmobile.ui.adminpanel.ordermanagement;

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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.iteam.voiceplusmobile.HelperContent;
import com.iteam.voiceplusmobile.R;
import com.iteam.voiceplusmobile.ui.adminpanel.addneworder.AddNewOrderFragment;
import com.iteam.voiceplusmobile.ui.adminpanel.editdelelteordermanagement.EditDeleteOrderManagementFragment;
import com.iteam.voiceplusmobile.ui.adminpanel.updatemanageitem.UpdateManageItem;

import java.util.ArrayList;
import java.util.List;

public class OrderManagementFragment extends Fragment {
    Button addneworder;
    private OrderManagementViewModel mViewModel;

    public static OrderManagementFragment newInstance() {
        return new OrderManagementFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_manage_order, container, false);
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(getContext());
        progressDoalog.setMessage("Loading order list from server.");
        progressDoalog.setTitle("Please Wait");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.show();
try {


        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://voice-plus-mobile.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OrderService jsonPlaceHolderApi = retrofit.create(OrderService.class);
        Call<List<OrderSchema>> listCall = jsonPlaceHolderApi.repairList();

        listCall.enqueue(new Callback<List<OrderSchema>>() {
            @Override
            public void onResponse(Call<List<OrderSchema>> call, Response<List<OrderSchema>> response) {
                ListView list_view;
                CustomListAdapter customListAdapter;
                ArrayList customListDataModelArrayList = new ArrayList<>();

                List<OrderSchema> posts = response.body();

                for (OrderSchema pricingSchema : posts) {

                    CustomOrderModel customListDataModel = new CustomOrderModel();

                    customListDataModel.setId(pricingSchema.getId());
                    customListDataModel.setUserPhoneNumber(pricingSchema.getUserPhoneNumber());
                    customListDataModel.setMobileBrand(pricingSchema.getMobileBrand());
                    customListDataModel.setMobileFault(pricingSchema.getMobileFault());
                    customListDataModel.setDateOrderPlaced(pricingSchema.getDateOrderPlaced());
                    customListDataModel.setDateItemReceived(pricingSchema.getDateItemReceived());
                    customListDataModel.setDateItemDelivered(pricingSchema.getDateItemDelivered());
                    customListDataModel.setOrderStatus(pricingSchema.getOrderStatus());
                    customListDataModel.setHasRepaired(pricingSchema.getHasRepaired());
                    customListDataModel.setCharges(pricingSchema.getCharges());
                    customListDataModelArrayList.add(customListDataModel);

                }
                list_view = view.findViewById(R.id.order_list_view);

                if (getActivity() != null) {
                    customListAdapter = new CustomListAdapter(getContext(), customListDataModelArrayList);
                    list_view.setAdapter(customListAdapter);
                    customListAdapter.notifyDataSetChanged();
                }

                list_view.setClickable(true);
                progressDoalog.dismiss();

                list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        CustomOrderModel clickedItem = (CustomOrderModel) parent.getItemAtPosition(position);
                        HelperContent.setCustomOrderModel(clickedItem);
//
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        EditDeleteOrderManagementFragment editDeleteItemFragment = new EditDeleteOrderManagementFragment();
                        transaction.replace(R.id.frame, editDeleteItemFragment);
                        transaction.addToBackStack(null);
                        transaction.commit();

                    }
                });

            }

            @Override
            public void onFailure(Call<List<OrderSchema>> call, Throwable t) {
                System.out.println("Error ---- ");
                System.out.println(t.getMessage());
                progressDoalog.dismiss();

            }
        });
} catch (Exception e){

}
        addneworder=view.findViewById(R.id.btn_add_new_order);
        addneworder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                AddNewOrderFragment addNewOrderFragment = new AddNewOrderFragment();
                transaction.replace(R.id.frame, addNewOrderFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(OrderManagementViewModel.class);
        // TODO: Use the ViewModel
    }

}