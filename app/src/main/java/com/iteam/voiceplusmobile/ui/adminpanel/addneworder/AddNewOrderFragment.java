package com.iteam.voiceplusmobile.ui.adminpanel.addneworder;

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
import android.widget.Toast;

import com.iteam.voiceplusmobile.R;
import com.iteam.voiceplusmobile.ui.adminpanel.additemmanagement.AddItem;
import com.iteam.voiceplusmobile.ui.adminpanel.additemmanagement.AddItemServices;
import com.iteam.voiceplusmobile.ui.adminpanel.ordermanagement.OrderManagementFragment;
import com.iteam.voiceplusmobile.ui.adminpanel.updatemanageitem.UpdateManageItem;

public class AddNewOrderFragment extends Fragment {

    private AddNewOrderViewModel mViewModel;

    public static AddNewOrderFragment newInstance() {
        return new AddNewOrderFragment();
    }

    EditText customername, customermobilenumber, customeraddress, customerpassword, mobilecompany, mobilemodel, mobilefault, repairingprice, repairingstatus, repairingdescription;
    Button addorder, canceladd;
    AddOrderServices addorderservices;
    private final String OrderUrl = "https://voice-plus-mobile.herokuapp.com/api/";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_order_management, container, false);
        customername = view.findViewById(R.id.order_customer_name);
        customermobilenumber = view.findViewById(R.id.order_customer_number);
        customeraddress = view.findViewById(R.id.order_customer_address);
        customerpassword = view.findViewById(R.id.order_customer_password);
        mobilecompany = view.findViewById(R.id.order_mobile_company);
        mobilemodel = view.findViewById(R.id.order_mobile_model);
        mobilefault = view.findViewById(R.id.order_mobile_fault);
        repairingprice = view.findViewById(R.id.order_repair_charges);
        repairingstatus = view.findViewById(R.id.order_fault_description);
        repairingdescription = view.findViewById(R.id.order_repair_status);

        addorder = view.findViewById(R.id.btn_order_add_new);
        canceladd = view.findViewById(R.id.btn_order_cancel_add);

        addorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addorder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (customername.getText().toString() == null || customername.getText().toString().trim().equals("")) {
                            Toast.makeText(getActivity().getBaseContext(), "Please enter Company Name!", Toast.LENGTH_SHORT).show();

                        } else if (customermobilenumber.getText().toString() == null || customermobilenumber.getText().toString().trim().equals("")) {
                            Toast.makeText(getActivity().getBaseContext(), "Please enter mobile model!", Toast.LENGTH_SHORT).show();
                        } else if (customeraddress.getText().toString() == null || customeraddress.getText().toString().trim().equals("")) {
                            Toast.makeText(getActivity().getBaseContext(), "Please enter mobile model!", Toast.LENGTH_SHORT).show();
                        } else if (customerpassword.getText().toString() == null || customerpassword.getText().toString().trim().equals("")) {
                            Toast.makeText(getActivity().getBaseContext(), "Please enter mobile model!", Toast.LENGTH_SHORT).show();
                        } else if (mobilecompany.getText().toString() == null || mobilecompany.getText().toString().trim().equals("")) {
                            Toast.makeText(getActivity().getBaseContext(), "Please enter mobile model!", Toast.LENGTH_SHORT).show();
                        } else if (mobilemodel.getText().toString() == null || mobilemodel.getText().toString().trim().equals("")) {
                            Toast.makeText(getActivity().getBaseContext(), "Please enter mobile model!", Toast.LENGTH_SHORT).show();

                        } else if (mobilefault.getText().toString() == null || mobilefault.getText().toString().trim().equals("")) {
                            Toast.makeText(getActivity().getBaseContext(), "Please enter mobile model!", Toast.LENGTH_SHORT).show();
                        } else if (repairingdescription.getText().toString() == null || repairingdescription.getText().toString().trim().equals("")) {
                            Toast.makeText(getActivity().getBaseContext(), "Please enter mobile model!", Toast.LENGTH_SHORT).show();
                        } else if (repairingprice.getText().toString() == null || repairingprice.getText().toString().trim().equals("")) {
                            Toast.makeText(getActivity().getBaseContext(), "Please enter mobile model!", Toast.LENGTH_SHORT).show();
                        } else if (repairingstatus.getText().toString() == null || repairingstatus.getText().toString().trim().equals("")) {
                            Toast.makeText(getActivity().getBaseContext(), "Please enter mobile model!", Toast.LENGTH_SHORT).show();
                        } else {
                            try {

                                Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl(OrderUrl)
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();
                                addorderservices = retrofit.create(AddOrderServices.class);
                                String _customer_name = customername.getText().toString();
                                String _customer_mobile = customermobilenumber.getText().toString();
                                String _customer_address = customeraddress.getText().toString();
                                String _customer_password = customerpassword.getText().toString();
                                String _repairing_status = repairingstatus.getText().toString();
                                String _mobile_company = mobilecompany.getText().toString();
                                String _mobile_model = mobilemodel.getText().toString();
                                String _mobile_fault = mobilefault.getText().toString();
                                String _repairing_description = repairingdescription.getText().toString();
                                String _repairing_price = repairingprice.getText().toString();
                                add_new_order_fu(_mobile_fault, _repairing_status, _customer_password,
                                        _customer_address, _customer_mobile, _customer_name,
                                        _mobile_company, _mobile_model, _mobile_fault, _repairing_description, _repairing_price);


                            } catch (Exception exp) {

                                Toast.makeText(getActivity().getBaseContext(), "Failed to send request to server", Toast.LENGTH_LONG).show();
                                System.out.println("Error in service building function" + exp.getMessage());

                            }


                        }
                    }
                });

            }
        });


        canceladd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                OrderManagementFragment orderManagementFragment = new OrderManagementFragment();
                transaction.replace(R.id.frame, orderManagementFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return view;
    }

    private void add_new_order_fu(String _mobile_fault, String _repairing_status, String _customer_password, String _customer_address, String _customer_mobile, String _customer_name, String _mobile_company, String _mobile_model, String mobilefault, String _repairing_description, String _repairing_price) {

        try {
            AddNewOrder addNewOrder = new AddNewOrder();
            addNewOrder.setUserPhoneNumber(_customer_mobile);
            addNewOrder.setUserRealName(_customer_name);
            addNewOrder.setUserAddress(_customer_address);
            addNewOrder.setUserPassword(_customer_password);
            addNewOrder.setMobileBrand(_mobile_company);
            addNewOrder.setMobileModel(_mobile_model);
            addNewOrder.setMobileFault(_mobile_fault);
            addNewOrder.setRepairingDescription(_repairing_description);
            boolean has_repaired = false;
            if (_repairing_status.length() != 0) {
                has_repaired = true;
            }
            addNewOrder.setHasRepaired(has_repaired);
            addNewOrder.setCharges(Integer.parseInt(_repairing_price));


            final ProgressDialog progressDoalog;
            progressDoalog = new ProgressDialog(getContext());
            progressDoalog.setMessage("Adding new order");
            progressDoalog.setTitle("Please Wait");
            progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDoalog.show();


            Call<AddNewOrder> call = addorderservices.addorderinfo(addNewOrder);

            call.enqueue(new Callback<AddNewOrder>() {
                @Override
                public void onResponse(Call<AddNewOrder> call, Response<AddNewOrder> response) {
                    progressDoalog.dismiss();
                    System.out.println("Hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
                    System.out.println(response.message());
                    System.out.println(response.errorBody());
                    System.out.println(response.code());
                }

                @Override
                public void onFailure(Call<AddNewOrder> call, Throwable t) {
                    progressDoalog.dismiss();
                    System.out.print("Failed to process request");
                    System.out.println(t.getStackTrace());
                    System.out.println(t.getCause());
                }
            });

        } catch (Exception exp) {
            System.out.println("Hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
            System.out.println(exp.getMessage());

        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AddNewOrderViewModel.class);
        // TODO: Use the ViewModel
    }

}