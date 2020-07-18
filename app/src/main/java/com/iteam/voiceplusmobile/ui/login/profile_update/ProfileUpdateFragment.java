package com.iteam.voiceplusmobile.ui.login.profile_update;

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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.iteam.voiceplusmobile.HelperContent;
import com.iteam.voiceplusmobile.R;
import com.iteam.voiceplusmobile.ui.login.profile.ProfileFragment;

import org.w3c.dom.Text;

public class ProfileUpdateFragment extends Fragment {

    private ProfileUpdateViewModel mViewModel;
    private UpdateUserService update_service;
    private ProgressDialog mProgressDialog;
    private final String update_profile_url = "https://voice-plus-mobile.herokuapp.com/api/update_user/";

    public static ProfileUpdateFragment newInstance() {
        return new ProfileUpdateFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_profile_update, container, false);

        final EditText user_real_name = view.findViewById(R.id.profile_update_user_real_name);
        user_real_name.setText(HelperContent.getUser_real_name());

        TextView mobile_number = view.findViewById(R.id.profile_update_user_phone_number);
        mobile_number.setText(HelperContent.getUser_phone_number());


        final EditText user_address = view.findViewById(R.id.profile_update_user_address);
        user_address.setText(HelperContent.getUser_address());


        final EditText user_password = view.findViewById(R.id.profile_update_user_password);
        user_password.setText(HelperContent.getUser_password());

        final EditText user_password_conf = view.findViewById(R.id.profile_update_user_password_conf);
        user_password_conf.setText(HelperContent.getUser_password());

        TextView back_to_profile = view.findViewById(R.id.profile_update_back);
        back_to_profile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                ProfileFragment profileFragment = new ProfileFragment();
                transaction.replace(R.id.nav_host_fragment, profileFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }

        });

        TextView update_profile = view.findViewById(R.id.profile_update_account);
        update_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (user_real_name.getText().toString() == null || user_real_name.getText().toString().trim().equals("")) {
                    Toast.makeText(getActivity().getBaseContext(), "Please enter your name!", Toast.LENGTH_SHORT).show();

                } else if (user_password.getText().toString() == null || user_password.getText().toString().trim().equals("")) {
                    Toast.makeText(getActivity().getBaseContext(), "Please enter your password!", Toast.LENGTH_SHORT).show();
                } else if (user_password_conf.getText().toString() == null || user_password_conf.getText().toString().trim().equals("")) {
                    Toast.makeText(getActivity().getBaseContext(), "Please enter confirm password!", Toast.LENGTH_SHORT).show();
                } else if (user_address.getText().toString() == null || user_address.getText().toString().trim().equals("")) {
                    Toast.makeText(getActivity().getBaseContext(), "Please enter your address!", Toast.LENGTH_SHORT).show();
                } else if (!user_password.getText().toString().equals(user_password_conf.getText().toString())) {
                    Toast.makeText(getActivity().getBaseContext(), "Password and confirm password does not match. Please re-enter!", Toast.LENGTH_SHORT).show();
                } else {

                    int _user_id = HelperContent.getUser_id();
                    String _user_real_name = user_real_name.getText().toString();
                    String _user_password = user_password.getText().toString();
                    String _user_address = user_address.getText().toString();
                    try {
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(update_profile_url)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                        update_service = retrofit.create(UpdateUserService.class);
                        send_request(_user_id, _user_real_name, _user_password, _user_address);
                    } catch (Exception e) {
                        Toast.makeText(getActivity().getBaseContext(), e.toString(), Toast.LENGTH_LONG).show();
                    }


                }
            }
        });

        return view;
    }

    private void send_request(int user_account_id, final String user_real_name, final String user_password, final String user_address) {
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(getContext());
        progressDoalog.setMessage("Login your account into application.");
        progressDoalog.setTitle("Please Wait");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        try {
            UpdateUser update_user_profile = new UpdateUser();
            update_user_profile.setUser_account_id(user_account_id);
            update_user_profile.setUser_real_name(user_real_name);
            update_user_profile.setUser_password(user_password);
            update_user_profile.setUser_address(user_address);
            Call<UpdateUser> call = update_service.sendUpdateProfileInformation(update_user_profile);

            progressDoalog.show();

            call.enqueue(new Callback<UpdateUser>() {
                @Override
                public void onResponse(Call<UpdateUser> call, Response<UpdateUser> response) {
                    progressDoalog.dismiss();
                    String _response = response.body().user_info();
                    if (_response.contains("Updated Succ")) {
                        Toast.makeText(getActivity().getBaseContext(), "Account information has updated successfully.", Toast.LENGTH_LONG).show();
                        HelperContent.setUser_real_name(user_real_name);
                        HelperContent.setUser_password(user_password);
                        HelperContent.setUser_address(user_address);

                    } else {
                        Toast.makeText(getActivity().getBaseContext(), "Failed to update account information. Please try again.", Toast.LENGTH_LONG).show();

                    }


                }

                @Override
                public void onFailure(Call<UpdateUser> call, Throwable t) {
                    progressDoalog.dismiss();
                    Toast.makeText(getActivity().getBaseContext(), t.toString(), Toast.LENGTH_LONG).show();
                }

            });
        } catch (Exception e) {
            progressDoalog.dismiss();
            Toast.makeText(getActivity().getBaseContext(), "Invalid Username / password.", Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ProfileUpdateViewModel.class);
        // TODO: Use the ViewModel
    }

}