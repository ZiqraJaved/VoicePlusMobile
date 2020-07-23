package com.iteam.voiceplusmobile.ui.bookmyrepair;

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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.iteam.voiceplusmobile.HelperContent;
import com.iteam.voiceplusmobile.R;
import com.iteam.voiceplusmobile.ui.login.user_login.LoginFragment;
import com.iteam.voiceplusmobile.ui.pricing.CustomListDataModel;
import com.iteam.voiceplusmobile.ui.pricing.PricingFragment;

public class BookMyRepair extends Fragment {

    private RepairService repairService;
    private String registerUrl = "https://voice-plus-mobile.herokuapp.com/api/";

    private BookMyRepairViewModel mViewModel;

    public static BookMyRepair newInstance() {
        return new BookMyRepair();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_book_my_repair, container, false);
        Button book_repair_cancel = view.findViewById(R.id.book_repair_cancel);
        book_repair_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                PricingFragment pricingFragment = new PricingFragment();
                transaction.replace(R.id.nav_host_fragment, pricingFragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

        if (HelperContent.getUser_id() == 0) {
            if (HelperContent.getLast_fragment() == 0) {
                HelperContent.setLast_fragment(2);
            }
            Toast.makeText(getActivity().getBaseContext(), "You need to login your account before placing order.", Toast.LENGTH_LONG).show();

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            LoginFragment loginFragment = new LoginFragment();
            transaction.replace(R.id.nav_host_fragment, loginFragment);
            transaction.addToBackStack(null);
            transaction.commit();

        }
        if (HelperContent.isHas_pricing_flag()) {
            CustomListDataModel customListDataModel = HelperContent.getCustomListDataModel();

            EditText book_repair_company_name = view.findViewById(R.id.book_repair_company_name);
            book_repair_company_name.setText(customListDataModel.getMobile_company());


            EditText book_repair_mobile_model = view.findViewById(R.id.book_repair_mobile_model);
            book_repair_mobile_model.setText(customListDataModel.getPhone_model_name());

            EditText book_repair_mobile_problem = view.findViewById(R.id.book_repair_mobile_problem);
            book_repair_mobile_problem.setText(customListDataModel.getRepair_part_name());


        }
        Button btn_apply_repair = view.findViewById(R.id.btn_apply_repair);
        final EditText book_repair_company_name = view.findViewById(R.id.book_repair_company_name);
        final EditText book_repair_mobile_model = view.findViewById(R.id.book_repair_mobile_model);
        final EditText book_repair_mobile_problem = view.findViewById(R.id.book_repair_mobile_problem);
        EditText txt_choosefile = view.findViewById(R.id.txt_choosefile);
        final CheckBox checkbox_book_repair = view.findViewById(R.id.checkbox_book_repair);
        btn_apply_repair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (book_repair_company_name.getText().toString() == null || book_repair_company_name.getText().toString().trim().equals("")) {
                    Toast.makeText(getActivity().getBaseContext(), "Mobile company name required.", Toast.LENGTH_LONG).show();
                } else if (book_repair_mobile_model.getText().toString() == null || book_repair_mobile_model.getText().toString().trim().equals("")) {
                    Toast.makeText(getActivity().getBaseContext(), "Mobile model name required.", Toast.LENGTH_LONG).show();
                } else if (book_repair_mobile_problem.getText().toString() == null || book_repair_mobile_problem.getText().toString().trim().equals("")) {
                    Toast.makeText(getActivity().getBaseContext(), "Describe your mobile fault.", Toast.LENGTH_LONG).show();
                } else if (!checkbox_book_repair.isChecked()) {
                    Toast.makeText(getActivity().getBaseContext(), "Please accept terms and conditions.", Toast.LENGTH_LONG).show();
                }
//                else if (txt_choosefile.equals("")) {
//                    Toast.makeText(getActivity().getBaseContext(), "Describe your mobile fault.", Toast.LENGTH_LONG).show();
//                }
                else {

                    String mobile_fault = book_repair_mobile_problem.getText().toString();
                    String user_phone_number = HelperContent.getUser_phone_number();
                    String mobile_brand = book_repair_company_name.getText().toString();
                    String mobile_model = book_repair_mobile_model.getText().toString();
                    System.out.print("Output --- " + mobile_fault + " " + user_phone_number + " " + mobile_brand + " " + mobile_model);

                    try {
//                        Url of login service
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(registerUrl)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                        repairService = retrofit.create(RepairService.class);
                        System.out.print("Output --- " + mobile_fault + " " + user_phone_number + " " + mobile_brand + " " + mobile_model);
                        registerOrder(mobile_fault, user_phone_number, mobile_brand, mobile_model);
                    } catch (Exception e) {
                        Toast.makeText(getActivity().getBaseContext(), e.toString(), Toast.LENGTH_LONG).show();
                    }

                }

            }
        });

        return view;
    }

    private void registerOrder(final String mobile_fault, String user_phone_number, String mobile_brand, String mobile_model) {
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(getContext());
        progressDoalog.setMessage("Registering your order.");
        progressDoalog.setTitle("Please Wait");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        try {
            OrderSchema orderSchema = new OrderSchema();
            orderSchema.setMobile_brand(mobile_brand);
            orderSchema.setMobile_fault(mobile_fault);
            orderSchema.setMobile_model(mobile_model);
            orderSchema.setUser_phone_number(user_phone_number);

            Call<OrderSchema> call = repairService.bookMyOrder(orderSchema);

            progressDoalog.show();

            call.enqueue(new Callback<OrderSchema>() {
                @Override
                public void onResponse(Call<OrderSchema> call, Response<OrderSchema> response) {

                    OrderSchema _response = response.body();
                    progressDoalog.dismiss();

                }

                @Override
                public void onFailure(Call<OrderSchema> call, Throwable t) {
                    progressDoalog.dismiss();
                    t.getMessage();
                    Toast.makeText(getActivity().getBaseContext(), "~Failed to created account. " +
                                    "Please re-try with some different phone number.",
                            Toast.LENGTH_LONG).show();
                }

            });
        } catch (Exception e) {
            progressDoalog.dismiss();
            Toast.makeText(getActivity().getBaseContext(), "^Failed to created account. " +
                            "Please re-try with some different phone number.",
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(BookMyRepairViewModel.class);
        // TODO: Use the ViewModel
    }

}