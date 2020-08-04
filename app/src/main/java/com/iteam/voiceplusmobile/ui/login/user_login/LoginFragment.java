package com.iteam.voiceplusmobile.ui.login.user_login;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.applozic.mobicomkit.Applozic;
import com.applozic.mobicomkit.listners.AlLogoutHandler;
import com.google.android.material.navigation.NavigationView;
import com.iteam.voiceplusmobile.HelperContent;
import com.iteam.voiceplusmobile.MainActivity;
import com.iteam.voiceplusmobile.R;
import com.iteam.voiceplusmobile.ui.adminpanel.admin.AdminPanelActivity;
import com.iteam.voiceplusmobile.ui.bookmyrepair.BookMyRepair;
import com.iteam.voiceplusmobile.ui.login.profile.ProfileFragment;
import com.iteam.voiceplusmobile.ui.login.register.RegisterFragment;

public class LoginFragment extends Fragment {
    private NavigationView navigationView;
    private LoginViewModel mViewModel;
    private LoginService loginService;
    private ProgressDialog mProgressDialog;
    private final String LoginUrl = "https://voice-plus-mobile.herokuapp.com/api/login_user/";
    private EditText user_phone_number;
    private EditText user_password;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_login, container, false);
        user_phone_number = root.findViewById(R.id.txt_user_phone_number);
        user_password = root.findViewById(R.id.txt_user_password);
        Button button = (Button) root.findViewById(R.id.btn_login);
        TextView clickTextView = root.findViewById(R.id.txt_notregister);
        navigationView = getActivity().findViewById(R.id.nav_view);

        try {
            Applozic.logoutUser(getContext(), new AlLogoutHandler() {
                @Override
                public void onSuccess(Context context) {

                }

                @Override
                public void onFailure(Exception exception) {

                }
            });
        } catch (Exception ex) {

        }
        clickTextView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                RegisterFragment registerFragment = new RegisterFragment();
                transaction.replace(R.id.nav_host_fragment, registerFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }

        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// Sending request after clicking on login button

                if (user_phone_number.getText().toString() == null || user_phone_number.getText().toString().trim().equals("")) {
                    Toast.makeText(getActivity().getBaseContext(), "Please enter phone number!", Toast.LENGTH_SHORT).show();

                } else if (user_password.getText().toString() == null || user_password.getText().toString().trim().equals("")) {
                    Toast.makeText(getActivity().getBaseContext(), "Please enter password!", Toast.LENGTH_SHORT).show();
                } else {

                    try {
                        String _user_phone_number, _password;
                        _user_phone_number = user_phone_number.getText().toString();
                        _password = user_password.getText().toString();

//                        Url of login service
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(LoginUrl)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                        loginService = retrofit.create(LoginService.class);
                        sendPost(_user_phone_number, _password);
                    } catch (Exception e) {
                        Toast.makeText(getActivity().getBaseContext(), e.toString(), Toast.LENGTH_LONG).show();
                    }

                }
            }

        });

        return root;
    }

    private void sendPost(String user_phone_number, final String user_password) {
        try {

            LoginUser login_user = new LoginUser();
            login_user.setUser_phone_number(user_phone_number);
            login_user.setUser_password(user_password);
            Call<LoginUser> call = loginService.sendLoginInformation(login_user);
            final ProgressDialog progressDoalog;
            progressDoalog = new ProgressDialog(getContext());
            progressDoalog.setMessage("Login your account into application.");
            progressDoalog.setTitle("Please Wait");
            progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDoalog.show();
            call.enqueue(new Callback<LoginUser>() {
                @Override
                public void onResponse(Call<LoginUser> call, Response<LoginUser> response) {

                    String user_data[] = response.body().data();
                    int user_id = Integer.parseInt(user_data[0]);
                    String user_phone_number = user_data[2];
                    String user_real_name = user_data[3];
                    String user_address = user_data[4];
                    String user_role = user_data[5];
                    String user_image = user_data[6];
                    String created_at = user_data[7];

                    if (user_id == 0) {
                        progressDoalog.dismiss();
                        Toast.makeText(getActivity().getBaseContext(), "Invalid login " +
                                        "details. Please enter correct user name and password",
                                Toast.LENGTH_LONG).show();
                        HelperContent.setAllNull();
                    } else {
                        if (navigationView != null) {
                            MainActivity.hasLoggedIn = true;
                            if (getActivity() != null)
                                getActivity().invalidateOptionsMenu();
                            Menu menu = navigationView.getMenu();
                            MenuItem nav_login = menu.findItem(R.id.nav_login);
                            nav_login.setTitle("Logout");
                            MenuItem nav_chat = menu.findItem(R.id.nav_chat);
                            nav_chat.setVisible(true);
                            MenuItem nav_payment = menu.findItem(R.id.nav_payment);
                            nav_payment.setVisible(true);

                            MenuItem nav_feedback = menu.findItem(R.id.nav_contact_us);
                            nav_feedback.setVisible(true);

                            nav_login.setOnMenuItemClickListener(null);
                            nav_login.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                                @Override
                                public boolean onMenuItemClick(MenuItem menuItem) {
                                    startActivity(new Intent(getActivity(), MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                                    return false;

                                }
                            });
//                            nav_login.setVisible(false);
                        }


                        HelperContent.setUser_id(user_id);
                        HelperContent.setUser_real_name(user_real_name);
                        HelperContent.setUser_phone_number(user_phone_number);
                        HelperContent.setUser_address(user_address);
                        HelperContent.setUser_role(user_role);
                        HelperContent.setImage(user_image);
                        HelperContent.setCreated_at(created_at);
                        HelperContent.setUser_password(user_password);
                        progressDoalog.dismiss();
                        if (HelperContent.getUser_role().equalsIgnoreCase("admin")) {
                            startActivity(new Intent(getActivity(), AdminPanelActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                            if (getActivity() != null)
                                getActivity().finish();
                        } else {

                            if (HelperContent.getLast_fragment() == 1) {
                                HelperContent.setHas_pricing_flag(true);
                                FragmentManager fragmentManager = getFragmentManager();
                                FragmentTransaction transaction = fragmentManager.beginTransaction();

                                BookMyRepair bookMyRepair = new BookMyRepair();
                                transaction.replace(R.id.nav_host_fragment, bookMyRepair);
                                transaction.addToBackStack(null);
                                transaction.commit();

                            } else if (HelperContent.getLast_fragment() == 2) {
                                HelperContent.setHas_pricing_flag(false);
                                FragmentManager fragmentManager = getFragmentManager();
                                FragmentTransaction transaction = fragmentManager.beginTransaction();

                                BookMyRepair bookMyRepair = new BookMyRepair();
                                transaction.replace(R.id.nav_host_fragment, bookMyRepair);
                                transaction.addToBackStack(null);
                                transaction.commit();

                            } else {
                                FragmentManager fragmentManager = getFragmentManager();
                                FragmentTransaction transaction = fragmentManager.beginTransaction();

                                ProfileFragment profileFragment = new ProfileFragment();
                                transaction.replace(R.id.nav_host_fragment, profileFragment);
                                transaction.addToBackStack(null);

                                transaction.commit();
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<LoginUser> call, Throwable t) {
                    HelperContent.setAllNull();
                    Toast.makeText(getActivity().getBaseContext(), t.toString(), Toast.LENGTH_LONG).show();
                }

            });

        } catch (Exception e) {
            HelperContent.setAllNull();
            Toast.makeText(getActivity().getBaseContext(), "Invalid Username / password.", Toast.LENGTH_LONG).show();

        }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        // TODO: Use the ViewModel
    }

}