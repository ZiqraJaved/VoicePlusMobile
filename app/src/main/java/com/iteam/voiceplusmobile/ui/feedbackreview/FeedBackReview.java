package com.iteam.voiceplusmobile.ui.feedbackreview;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iteam.voiceplusmobile.R;

public class FeedBackReview extends Fragment {

    private FeedBackReviewViewModel mViewModel;

    public static FeedBackReview newInstance() {
        return new FeedBackReview();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_feed_back_review_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FeedBackReviewViewModel.class);
        // TODO: Use the ViewModel
    }

}