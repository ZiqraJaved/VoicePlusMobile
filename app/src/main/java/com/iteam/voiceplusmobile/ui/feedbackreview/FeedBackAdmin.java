package com.iteam.voiceplusmobile.ui.feedbackreview;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.iteam.voiceplusmobile.HelperContent;
import com.iteam.voiceplusmobile.R;

public class FeedBackAdmin extends Fragment {


    public static FeedBackAdmin newInstance() {
        return new FeedBackAdmin();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed_back_admin, container, false);

        CustomListDataModel selectedItem = HelperContent.getFeedbackSchema();

        TextView feed_back_customer_name = view.findViewById(R.id.feed_back_customer_name);
        feed_back_customer_name.setText(selectedItem.getUserRealName());

        TextView feed_back_customer_phone_number = view.findViewById(R.id.feed_back_customer_phone_number);
        feed_back_customer_phone_number.setText(selectedItem.getUserPhoneNumber());

        TextView feed_back_your_message = view.findViewById(R.id.feed_back_your_message);
        feed_back_your_message.setText(selectedItem.getUserFeedback());


        return view;
    }


}