package com.iteam.voiceplusmobile.ui.gallery;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.iteam.voiceplusmobile.R;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private ImageView imageView;
    private int index = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        imageView = root.findViewById(R.id.img_gallary);
        final int[] imges = new int[]{R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5, R.drawable.img6};
        imageView.post(new Runnable() {
            @Override
            public void run() {
                if (index > imges.length - 1)
                    index = 0;
                imageView.setImageResource(imges[index]);
                index++;
                handler.postDelayed(this, 3000);
            }
        });
        return root;
    }

    Handler handler = new Handler();
}