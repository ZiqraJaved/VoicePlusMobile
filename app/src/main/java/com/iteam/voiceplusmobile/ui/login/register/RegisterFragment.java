package com.iteam.voiceplusmobile.ui.login.register;

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

import com.iteam.voiceplusmobile.R;
import com.iteam.voiceplusmobile.ui.login.user_login.LoginFragment;

public class RegisterFragment extends Fragment {

    private RegisterViewModel mViewModel;
    private RegisterUserService registerService;
    private String registerUrl = "https://voice-plus-mobile.herokuapp.com/api/";

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        TextView back_to_login = view.findViewById(R.id.register_login_account);
        TextView register_user_account = view.findViewById(R.id.register_create_account);
// Gathering data from XML

        final EditText user_real_name = view.findViewById(R.id.register_user_real_name);
        final EditText user_phone_number = view.findViewById(R.id.register_user_phone_number);
        final EditText user_password = view.findViewById(R.id.register_user_password);
        final EditText user_password_conf = view.findViewById(R.id.register_user_password_conf);
        final EditText user_address = view.findViewById(R.id.register_user_address);

        back_to_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                LoginFragment loginFragment = new LoginFragment();
                transaction.replace(R.id.nav_host_fragment, loginFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }

        });


        register_user_account.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
// Validate input collected from XML
                if (user_real_name.getText().toString() == null || user_real_name.getText().toString().trim().equals("")) {
                    Toast.makeText(getActivity().getBaseContext(), "Please enter your name!", Toast.LENGTH_SHORT).show();

                } else if (user_phone_number.getText().toString() == null || user_phone_number.getText().toString().trim().equals("")) {
                    Toast.makeText(getActivity().getBaseContext(), "Please enter your phone number!", Toast.LENGTH_SHORT).show();
                } else if (user_password.getText().toString() == null || user_password.getText().toString().trim().equals("")) {
                    Toast.makeText(getActivity().getBaseContext(), "Please enter your password!", Toast.LENGTH_SHORT).show();
                } else if (user_password_conf.getText().toString() == null || user_password_conf.getText().toString().trim().equals("")) {
                    Toast.makeText(getActivity().getBaseContext(), "Please enter confirm password!", Toast.LENGTH_SHORT).show();
                } else if (user_address.getText().toString() == null || user_address.getText().toString().trim().equals("")) {
                    Toast.makeText(getActivity().getBaseContext(), "Please enter your address!", Toast.LENGTH_SHORT).show();
                } else if (!user_password.getText().toString().equals(user_password_conf.getText().toString())) {
                    Toast.makeText(getActivity().getBaseContext(), "Password and confirm password does not match. Please re-enter!", Toast.LENGTH_SHORT).show();
                } else {

                    String _user_real_name = user_real_name.getText().toString();
                    String _user_password = user_password.getText().toString();
                    String _user_phone_number = user_phone_number.getText().toString();
                    String _user_address = user_address.getText().toString();
                    try {
//                        Url of login service
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(registerUrl)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                        registerService = retrofit.create(RegisterUserService.class);
                        sendPost(_user_real_name, _user_password, _user_phone_number, _user_address);
                    } catch (Exception e) {
                        Toast.makeText(getActivity().getBaseContext(), e.toString(), Toast.LENGTH_LONG).show();
                    }

                }
            }

        });


        return view;
    }

    private void sendPost(final String user_real_name, String user_password, String user_phone_number, String user_address) {
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(getContext());
        progressDoalog.setMessage("We are creating your account.");
        progressDoalog.setTitle("Please Wait");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        try {
            RegisterUser register_user = new RegisterUser();
            register_user.setUser_real_name(user_real_name);
            register_user.setUser_password(user_password);
            register_user.setUser_phone_number(user_phone_number);
            register_user.setUser_address(user_address);

            Call<RegisterUser> call = registerService.sendLoginInformation(register_user);

            progressDoalog.show();

            call.enqueue(new Callback<RegisterUser>() {
                @Override
                public void onResponse(Call<RegisterUser> call, Response<RegisterUser> response) {

                    String _response = response.body().user_info();

                    if (_response.contains("Account Created Successfully")) {
                        progressDoalog.dismiss();
                        Toast.makeText(getActivity().getBaseContext(), "User Account Created! " +
                                        "Please login into your account.",
                                Toast.LENGTH_LONG).show();
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction transaction = fragmentManager.beginTransaction();

                        LoginFragment loginFragment = new LoginFragment();
                        transaction.replace(R.id.nav_host_fragment, loginFragment);
                        transaction.addToBackStack(null);

                        transaction.commit();

                    } else {
                        progressDoalog.dismiss();
                        Toast.makeText(getActivity().getBaseContext(), "Failed to created account. " +
                                        "Please re-try with some different phone number.",
                                Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<RegisterUser> call, Throwable t) {
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
        mViewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);
        // TODO: Use the ViewModel
    }

}