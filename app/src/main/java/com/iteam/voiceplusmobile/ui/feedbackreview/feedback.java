package com.iteam.voiceplusmobile.ui.feedbackreview;

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

import com.iteam.voiceplusmobile.HelperContent;
import com.iteam.voiceplusmobile.R;

public class feedback extends Fragment {

    private FeedbackViewModel mViewModel;
    private final String feedBackEndPointUrl = "https://voice-plus-mobile.herokuapp.com/api/";
    private FeedBackService endPointServiceName;
    EditText txtFB;

    public static feedback newInstance() {
        return new feedback();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feedback, container, false);
        txtFB = view.findViewById(R.id.feed_back_your_message);
        Button btn = view.findViewById(R.id.btn_submit_fb);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_feedback = txtFB.getText().toString();
                if (user_feedback.length() == 0 || user_feedback.equals("")) {
                    Toast.makeText(getActivity().getBaseContext(), "Please enter some feedback before sending it.", Toast.LENGTH_LONG).show();
                } else {
                    String user_phone_number = HelperContent.getUser_phone_number();
                    String user_real_name = HelperContent.getUser_real_name();
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(feedBackEndPointUrl)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    endPointServiceName = retrofit.create(FeedBackService.class);
                    callEndPoint(user_real_name, user_phone_number, user_feedback);
                }
            }
        });
        return view;
    }

    private void callEndPoint(String user_real_name, String user_phone_number, String user_feedback) {
        final ProgressDialog progressDoalog = new ProgressDialog(getContext());
        try {
            FeedbackSchema feedbackSchema = new FeedbackSchema();
            feedbackSchema.setUserFeedback(user_feedback);
            feedbackSchema.setUserPhoneNumber(user_phone_number);
            feedbackSchema.setUserRealName(user_real_name);


            progressDoalog.setMessage("Sending your feedback.");
            progressDoalog.setTitle("Please Wait");
            progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDoalog.show();


            Call<FeedbackSchema> call = endPointServiceName.feedBackFunction(feedbackSchema);
            call.enqueue(new Callback<FeedbackSchema>() {
                @Override
                public void onResponse(Call<FeedbackSchema> call, Response<FeedbackSchema> response) {
                    progressDoalog.dismiss();
                    if (response.code() == 200 || response.code() == 201)
                        Toast.makeText(getActivity().getBaseContext(), "Thank you for your feedback.", Toast.LENGTH_LONG).show();
                    else {
                        Toast.makeText(getActivity().getBaseContext(), "Please try again later. Server is busy right now!.", Toast.LENGTH_LONG).show();

                    }
                    reset_controls();
                }

                @Override
                public void onFailure(Call<FeedbackSchema> call, Throwable t) {
                    progressDoalog.dismiss();
                    reset_controls();
                    Toast.makeText(getActivity().getBaseContext(), "Sorry, we are unable to fulfil your request right now.", Toast.LENGTH_LONG).show();

                }
            });


        } catch (Exception exp) {
            Toast.makeText(getActivity().getBaseContext(), "Failed to process request. Please try again later.", Toast.LENGTH_LONG).show();

        }

    }

    private void reset_controls() {
        txtFB.setText("");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FeedbackViewModel.class);
        // TODO: Use the ViewModel
    }

}