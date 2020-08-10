package com.iteam.voiceplusmobile.ui.feedbackreview;

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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.iteam.voiceplusmobile.HelperContent;
import com.iteam.voiceplusmobile.R;

import java.util.ArrayList;
import java.util.List;

public class FeedBackReview extends Fragment {

    private FeedBackReviewViewModel mViewModel;


    public static FeedBackReview newInstance() {
        return new FeedBackReview();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_feed_back_review_list, container, false);

        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(getContext());
        progressDoalog.setMessage("Loading customer's feedback.");
        progressDoalog.setTitle("Please Wait");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.show();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://voice-plus-mobile.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FeedBackService jsonPlaceHolderApi = retrofit.create(FeedBackService.class);

        Call<List<FeedbackSchema>> listCall = jsonPlaceHolderApi.feedbackList();

        listCall.enqueue(new Callback<List<FeedbackSchema>>() {
            @Override
            public void onResponse(Call<List<FeedbackSchema>> call, Response<List<FeedbackSchema>> response) {
                ListView list_view;
                FeedbackListAdapter customListAdapter;
                ArrayList customListDataModelArrayList = new ArrayList<>();

                List<FeedbackSchema> posts = response.body();

                for (FeedbackSchema feedbackSchema : posts) {

                    CustomListDataModel customListDataModel = new CustomListDataModel();
                    customListDataModel.setUserRealName(feedbackSchema.getUserRealName());
                    customListDataModel.setUserPhoneNumber(feedbackSchema.getUserPhoneNumber());
                    customListDataModel.setUserFeedback(feedbackSchema.getUserFeedback());

                    customListDataModelArrayList.add(customListDataModel);

                }
                list_view = view.findViewById(R.id.feed_back_list_view);

                if (getActivity() != null) {
                    customListAdapter = new FeedbackListAdapter(getContext(), customListDataModelArrayList);
                    list_view.setAdapter(customListAdapter);
                    customListAdapter.notifyDataSetChanged();
                }

                list_view.setClickable(true);
                progressDoalog.dismiss();

                list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        CustomListDataModel clickedItem = (CustomListDataModel) parent.getItemAtPosition(position);
                        HelperContent.setFeedbackSchema(clickedItem);

                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        FeedBackAdmin feedBackAdmin = new FeedBackAdmin();
                        transaction.replace(R.id.frame, feedBackAdmin);
                        transaction.addToBackStack(null);
                        transaction.commit();

                    }
                });

            }

            @Override
            public void onFailure(Call<List<FeedbackSchema>> call, Throwable t) {
                System.out.println("Error ---- ");
                System.out.println(t.getMessage());
                progressDoalog.dismiss();

            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FeedBackReviewViewModel.class);
        // TODO: Use the ViewModel
    }

}