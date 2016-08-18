package com.example.jasper.virgielowee2016;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class WednesdayFragment extends Fragment {


    public WednesdayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_monday, container, false);
        final ImageView imageView  = (ImageView) rootView.findViewById(R.id.maandagImageView);
        Bitmap maandag = BitmapExtend.decodeSampledBitmapFromResource(getResources(), R.drawable.woensdag03, 200, 300);
        imageView.setImageBitmap(maandag);
        return rootView;
    }

}
