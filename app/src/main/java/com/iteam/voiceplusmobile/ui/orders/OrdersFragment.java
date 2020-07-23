package com.iteam.voiceplusmobile.ui.orders;

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
import android.widget.ListView;
import android.widget.Toast;

import com.iteam.voiceplusmobile.HelperContent;
import com.iteam.voiceplusmobile.R;

import java.util.ArrayList;
import java.util.List;

public class OrdersFragment extends Fragment {

    private OrdersViewModel mViewModel;

    public static OrdersFragment newInstance() {
        return new OrdersFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_orders, container, false);
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(getContext());
        progressDoalog.setMessage("Loading pricing information from server.");
        progressDoalog.setTitle("Please Wait");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.show();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://voice-plus-mobile.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OrderService jsonPlaceHolderApi = retrofit.create(OrderService.class);

        final Call<List<OrderSchema>> orderList = jsonPlaceHolderApi.orderList();
//        Toast.makeText(getActivity().getBaseContext(), t.toString(), Toast.LENGTH_LONG).show();
        orderList.enqueue(new Callback<List<OrderSchema>>() {
            @Override
            public void onResponse(Call<List<OrderSchema>> call, Response<List<OrderSchema>> response) {
                ListView list_view;
//                ArrayList customListDataModelArrayList = new ArrayList<>();

                List<OrderSchema> posts = response.body();
                CustomListOrderAdapter customListOrderAdapter;
                ArrayList customListDataModelArrayList = new ArrayList<>();
                for (OrderSchema orderSchema : posts) {

                    if (orderSchema.getUserPhoneNumber().equals(HelperContent.getUser_phone_number())) {
                        CustomListOrderModel customListOrderModel = new CustomListOrderModel();
                        customListOrderModel.setMobile_brand(orderSchema.getMobileBrand());
                        customListOrderModel.setMobile_model(orderSchema.getMobileModel());
                        customListOrderModel.setMobile_fault(orderSchema.getMobileFault());
                        customListOrderModel.setImage(orderSchema.getImage());
                        customListOrderModel.setDate_order_placed(orderSchema.getDateOrderPlaced());
                        customListOrderModel.setDate_item_received(orderSchema.getDateItemReceived());
                        customListOrderModel.setDate_item_delivered(orderSchema.getDateItemDelivered());
                        customListOrderModel.setOrder_status(orderSchema.getOrderStatus());
                        customListOrderModel.setHas_repaired(orderSchema.getHasRepaired());
                        customListOrderModel.setCharges(orderSchema.getCharges());
                        customListDataModelArrayList.add(customListOrderModel);
                    }
                    list_view = view.findViewById(R.id.order_list_view);

                    if (getActivity() != null) {
                        customListOrderAdapter = new CustomListOrderAdapter(getContext(), customListDataModelArrayList);
                        list_view.setAdapter(customListOrderAdapter);
                        customListOrderAdapter.notifyDataSetChanged();
                    }

                    list_view.setClickable(true);
                    progressDoalog.dismiss();


                }

                progressDoalog.dismiss();

            }

            @Override
            public void onFailure(Call<List<OrderSchema>> call, Throwable t) {
                System.out.println("Error ---- ");
                System.out.println(t.getMessage());
                progressDoalog.dismiss();

            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(OrdersViewModel.class);
        // TODO: Use the ViewModel
    }

}