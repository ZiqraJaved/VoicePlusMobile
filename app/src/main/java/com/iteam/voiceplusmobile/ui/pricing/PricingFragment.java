package com.iteam.voiceplusmobile.ui.pricing;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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

import com.iteam.voiceplusmobile.HelperContent;
import com.iteam.voiceplusmobile.R;

import java.util.ArrayList;
import java.util.List;

public class PricingFragment extends Fragment {


    public static PricingFragment newInstance() {
        return new PricingFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_pricing, container, false);

        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(getContext());
        progressDoalog.setMessage("Loading pricing information from server.");
        progressDoalog.setTitle("Please Wait");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.show();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://voice-plus-mobile.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PricingService jsonPlaceHolderApi = retrofit.create(PricingService.class);

        Call<List<PricingSchema>> listCall = jsonPlaceHolderApi.pricesList();

        listCall.enqueue(new Callback<List<PricingSchema>>() {
            @Override
            public void onResponse(Call<List<PricingSchema>> call, Response<List<PricingSchema>> response) {
                ListView list_view;
                CustomListAdapter customListAdapter;
                ArrayList customListDataModelArrayList = new ArrayList<>();

                List<PricingSchema> posts = response.body();

                for (PricingSchema pricingSchema : posts) {

                    CustomListDataModel customListDataModel = new CustomListDataModel();
                    customListDataModel.setImage_id(get_image_id(pricingSchema.getMobileCompany()));
                    customListDataModel.setPhone_model_name(pricingSchema.getMobileModel());
                    customListDataModel.setRepair_part_name(pricingSchema.getRepairingPart());
                    customListDataModel.setMobile_company(pricingSchema.getMobileCompany());
                    customListDataModel.setRepairing_price(pricingSchema.getRepairingPrice());
                    customListDataModel.setRepairing_description(pricingSchema.getRepairingDescription());
                    customListDataModelArrayList.add(customListDataModel);

                }
                list_view = view.findViewById(R.id.pricing_list_view);

                if (getActivity() != null) {
                    customListAdapter = new CustomListAdapter(getContext(), customListDataModelArrayList);
                    list_view.setAdapter(customListAdapter);
                    customListAdapter.notifyDataSetChanged();
                }

                list_view.setClickable(true);
                progressDoalog.dismiss();

                list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        CustomListDataModel clickedItem = (CustomListDataModel) parent.getItemAtPosition(position);
                        HelperContent.setCustomListDataModel(clickedItem);

                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        PricingDetail pricingDetail = new PricingDetail();
                        transaction.replace(R.id.nav_host_fragment, pricingDetail);
                        transaction.addToBackStack(null);
                        transaction.commit();

                    }
                });

            }

            @Override
            public void onFailure(Call<List<PricingSchema>> call, Throwable t) {
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

    }

    private void call_service() {
    }

    private int get_image_id(String company_name) {
        if (company_name.toLowerCase().equals("apple")) {
            return R.drawable.apple_icon;
        } else if (company_name.toLowerCase().equals("samsung")) {
            return R.drawable.samsung_icon;
        } else if (company_name.toLowerCase().equals("htc")) {
            return R.drawable.htc_icon;
        } else if (company_name.toLowerCase().equals("motorola")) {
            return R.drawable.motorola_icon;
        } else if (company_name.toLowerCase().equals("huawei")) {
            return R.drawable.huawei_icon;
        } else {
            return R.drawable.raw_icon;
        }
    }
}
