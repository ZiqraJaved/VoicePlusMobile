package com.iteam.voiceplusmobile.ui.pricing;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.snackbar.Snackbar;
import com.iteam.voiceplusmobile.R;

import java.util.ArrayList;

public class PricingFragment extends Fragment {

    private PricingViewModel mViewModel;
    ListView list_view;
    CustomListAdapter customListAdapter;
    ArrayList customListDataModelArrayList = new ArrayList<>();

    public static PricingFragment newInstance() {
        return new PricingFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pricing, container, false);
        PopulateData();
        list_view =  view.findViewById(R.id.list_view);
        customListAdapter = new CustomListAdapter(getActivity(), customListDataModelArrayList);
        list_view.setAdapter(customListAdapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PricingViewModel.class);
        PopulateData();

        list_view =  getActivity().findViewById(R.id.list_view);
        customListAdapter = new CustomListAdapter(getActivity(), customListDataModelArrayList);
        list_view.setAdapter(customListAdapter);

    }

    public void PopulateData() {

        for (int i = 0; i < 10; i++) {

            CustomListDataModel customListDataModel = new CustomListDataModel();
            switch (i) {
                case 0:
                    customListDataModel.setImageName("Angry Bird Red");
                    customListDataModel.setImage_id(R.drawable.ic_contact_us);
                    customListDataModel.setImageDiscription("Angry Bird is red in color");
                    break;

                case 1:
                    customListDataModel.setImageName("Angry Bird Black");
                    customListDataModel.setImage_id(R.drawable.ic_book_repair);
                    customListDataModel.setImageDiscription("Angry Bird is black in color");
                    break;

                case 2:
                    customListDataModel.setImageName("Angry Bird Blue");
                    customListDataModel.setImage_id(R.drawable.ic_contact_us);
                    customListDataModel.setImageDiscription("Angry Bird is blue in color");
                    break;

                case 3:
                    customListDataModel.setImageName("Angry Bird Red");
                    customListDataModel.setImage_id(R.drawable.ic_book_repair);
                    customListDataModel.setImageDiscription("Angry Bird is red in color");
                    break;

                case 4:
                    customListDataModel.setImageName("Angry Bird Black");
                    customListDataModel.setImage_id(R.drawable.ic_menu_camera);
                    customListDataModel.setImageDiscription("Angry Bird is black in color");
                    break;
                case 5:
                    customListDataModel.setImageName("Angry Bird Blue");
                    customListDataModel.setImage_id(R.drawable.ic_contact_us);
                    customListDataModel.setImageDiscription("Angry Bird is blue in color");
                    break;

                case 6:
                    customListDataModel.setImageName("Angry Bird Green");
                    customListDataModel.setImage_id(R.drawable.ic_menu_camera);
                    customListDataModel.setImageDiscription("Angry Bird is green in color");
                    break;

                case 7:
                    customListDataModel.setImageName("Angry Bird Yellow");
                    customListDataModel.setImage_id(R.drawable.ic_contact_us);
                    customListDataModel.setImageDiscription("Angry Bird is yellow in color");
                    break;

                case 8:
                    customListDataModel.setImageName("Angry Bird White");
                    customListDataModel.setImage_id(R.drawable.ic_menu_camera);
                    customListDataModel.setImageDiscription("Angry Bird is white in color");
                    break;

                case 9:
                    customListDataModel.setImageName("Angry Bird Red");
                    customListDataModel.setImage_id(R.drawable.ic_contact_us);
                    customListDataModel.setImageDiscription("Angry Bird is white in color");
                    break;
            }
            customListDataModelArrayList.add(customListDataModel);

        }

    }
}