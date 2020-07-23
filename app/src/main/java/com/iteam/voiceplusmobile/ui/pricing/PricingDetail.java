package com.iteam.voiceplusmobile.ui.pricing;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.iteam.voiceplusmobile.HelperContent;
import com.iteam.voiceplusmobile.R;
import com.iteam.voiceplusmobile.ui.bookmyrepair.BookMyRepair;
import com.iteam.voiceplusmobile.ui.login.register.RegisterFragment;
import com.iteam.voiceplusmobile.ui.login.user_login.LoginFragment;

public class PricingDetail extends Fragment {

    private PricingDetailViewModel mViewModel;

    public static PricingDetail newInstance() {
        return new PricingDetail();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pricing_detail, container, false);
        CustomListDataModel selected_item = HelperContent.getCustomListDataModel();

        TextView price_detail_company_name = view.findViewById(R.id.price_detail_company_name);
        price_detail_company_name.setText(selected_item.getMobile_company());

        TextView price_detail_item_model = view.findViewById(R.id.price_detail_item_model);
        price_detail_item_model.setText(selected_item.getPhone_model_name());

        ImageView price_detail_image_id = view.findViewById(R.id.price_detail_image_id);
        price_detail_image_id.setImageResource(selected_item.getImage_id());

        TextView price_detail_repair_part = view.findViewById(R.id.price_detail_repair_part);
        price_detail_repair_part.setText(selected_item.getRepair_part_name());

        TextView price_detail_price = view.findViewById(R.id.price_detail_price);
        String part_price = "Price : " + selected_item.getRepairing_price();
        price_detail_price.setText(part_price);

        TextView price_detail_product_description = view.findViewById(R.id.price_detail_product_description);
        price_detail_product_description.setText(selected_item.getRepairing_description());

        Button btn_back = view.findViewById(R.id.pricing_btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
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

        Button price_detail_book_btn = view.findViewById(R.id.price_detail_book_btn);
        price_detail_book_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (HelperContent.getUser_id() != 0) {
                    HelperContent.setHas_pricing_flag(true);
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();

                    BookMyRepair bookMyRepair = new BookMyRepair();
                    transaction.replace(R.id.nav_host_fragment, bookMyRepair);
                    transaction.addToBackStack(null);
                    transaction.commit();

                } else {
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    HelperContent.setLast_fragment(1);
                    LoginFragment loginFragment = new LoginFragment();
                    transaction.replace(R.id.nav_host_fragment, loginFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();

                    Toast.makeText(getActivity().getBaseContext(), "You need to login your account before placing order.", Toast.LENGTH_LONG).show();

                }
            }
        });


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PricingDetailViewModel.class);
        // TODO: Use the ViewModel
    }

}