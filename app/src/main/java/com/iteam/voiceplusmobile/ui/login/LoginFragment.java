package com.iteam.voiceplusmobile.ui.login;

import androidx.lifecycle.ViewModelProviders;

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
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.iteam.voiceplusmobile.R;

public class LoginFragment extends Fragment {

    private LoginViewModel mViewModel;
    private LoginService loginService;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_login, container, false);
        final EditText user_name = root.findViewById(R.id.txt_user_name);
        final EditText user_password = root.findViewById(R.id.txt_user_password);
        Button button = (Button) root.findViewById(R.id.btn_login);
//        System.out.println("Clicked on Login Button");
//
//        NavigationView navigation = root.findViewById(R.id.nav_view);
//        Menu menu = navigation.getMenu();
//        menu.findItem(R.id.nav_login).setTitle("Hello");


//        MenuItem menuItem = root.findViewById(R.id.nav_login);
//System.out.println(menuItem.getTitle());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user_name.getText().toString() == null || user_name.getText().toString().trim().equals("")) {
                    Toast.makeText(getActivity().getBaseContext(), "Please enter user name!", Toast.LENGTH_SHORT).show();

                } else if (user_password.getText().toString() == null || user_password.getText().toString().trim().equals("")) {
                    Toast.makeText(getActivity().getBaseContext(), "Please enter password!", Toast.LENGTH_SHORT).show();
                } else {

                    try {
                        String _user_name, _password;
                        _user_name = user_name.getText().toString();
                        _password = user_password.getText().toString();

                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("https://voice-plus-mobile.herokuapp.com/api/login_user/")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                        loginService = retrofit.create(LoginService.class);
                        sendPost(_user_name, _password);
                    } catch (Exception e) {
                        Toast.makeText(getActivity().getBaseContext(), e.toString(), Toast.LENGTH_LONG).show();
                    }

                }
            }

        });

        return root;
    }

    private void sendPost(String user_name, String user_password) {


        try{
            Login login_user = new Login();
            login_user.setUser_name(user_name);
            login_user.setUser_password(user_password);
            Call<Login> call = loginService.sendLoginInformation(login_user);
            call.enqueue(new Callback<Login>() {
                @Override
                public void onResponse(Call<Login> call, Response<Login> response) {
                    System.out.println(response.code());
                    Toast.makeText(getActivity().getBaseContext(), response.body().toString(), Toast.LENGTH_LONG).show();

                }

                @Override
                public void onFailure(Call<Login> call, Throwable t) {
                    Toast.makeText(getActivity().getBaseContext(), t.toString(), Toast.LENGTH_LONG).show();
                }

            });
        }
        catch (Exception e){
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