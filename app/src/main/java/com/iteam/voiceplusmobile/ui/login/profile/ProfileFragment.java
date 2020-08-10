package com.iteam.voiceplusmobile.ui.login.profile;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iteam.voiceplusmobile.HelperContent;
import com.iteam.voiceplusmobile.R;
import com.iteam.voiceplusmobile.ui.login.profile_update.ProfileUpdateFragment;
import com.iteam.voiceplusmobile.ui.login.register.RegisterFragment;
import com.iteam.voiceplusmobile.ui.orders.OrdersFragment;

import org.w3c.dom.Text;

public class ProfileFragment extends Fragment {

    private ProfileViewModel mViewModel;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
//        final EditText user_phone_number = root.findViewById(R.id.txt_user_phone_number);
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
//        profile_real_name
        TextView user_real_name = view.findViewById(R.id.profile_real_name);
        TextView user_real_name_two = view.findViewById(R.id.profile_real_name_two);
        TextView user_phone_number = view.findViewById(R.id.profile_user_phone_number);
        TextView user_address = view.findViewById(R.id.profile_user_address);
        TextView created_at = view.findViewById(R.id.profile_user_registered);

        TextView update_profile = view.findViewById(R.id.btn_profile_update);

        update_profile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                ProfileUpdateFragment profileUpdateFragment = new ProfileUpdateFragment();
                transaction.replace(R.id.nav_host_fragment, profileUpdateFragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }

        });

        user_real_name.setText(HelperContent.getUser_real_name());
        user_real_name_two.setText(HelperContent.getUser_real_name());
        user_phone_number.setText(HelperContent.getUser_phone_number());
        user_address.setText(HelperContent.getUser_address());
        created_at.setText(HelperContent.getCreated_at());

        TextView profile_view_order_history = view.findViewById(R.id.profile_view_order_history);
        profile_view_order_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                FragmentManager fragmentManager = getFragmentManager();
//                FragmentTransaction transaction = fragmentManager.beginTransaction();
//                ProfileFragment profileFragment = new ProfileFragment();
//                transaction.replace(R.id.nav_host_fragment, profileFragment);
//                transaction.addToBackStack(null);
//                transaction.commit();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                OrdersFragment ordersFragment = new OrdersFragment();
                transaction.replace(R.id.nav_host_fragment, ordersFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        // TODO: Use the ViewModel
    }

}