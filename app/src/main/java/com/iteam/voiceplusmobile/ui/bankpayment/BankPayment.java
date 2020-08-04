package com.iteam.voiceplusmobile.ui.bankpayment;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.iteam.voiceplusmobile.R;

public class BankPayment extends Fragment {

    private BankPaymentViewModel mViewModel;

    public static BankPayment newInstance() {
        return new BankPayment();
    }

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bank_payment, container, false);
        Button Easypaisa = view.findViewById(R.id.btn_easypaisa);
        Button Jazzcash = view.findViewById(R.id.btn_jazzcash);
        Easypaisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (getActivity() != null) {
                        Intent intent = getActivity().getPackageManager().getLaunchIntentForPackage("pk.com.telenor.phoenix");
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                } catch (Exception ex) {
                    final String appPackageName = "pk.com.telenor.phoenix"; // getPackageName() from Context or Activity object
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                    } catch (android.content.ActivityNotFoundException anfe) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                    }
                }

            }
        });
        Jazzcash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (getActivity() != null) {
                        Intent intent = getActivity().getPackageManager().getLaunchIntentForPackage("com.techlogix.mobilinkcustomer");
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                } catch (Exception ex) {
                    final String appPackageName = "pk.com.telenor.phoenix"; // getPackageName() from Context or Activity object
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                    } catch (android.content.ActivityNotFoundException anfe) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                    }
                }

            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(BankPaymentViewModel.class);
        // TODO: Use the ViewModel
    }

}