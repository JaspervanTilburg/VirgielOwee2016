package com.example.jasper.virgielowee2016;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.AppCompatImageView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MondayFragment extends Fragment {

    final ArrayList<ProgrammaItem> programmaitems;

    public MondayFragment() {
        programmaitems = new ArrayList<>();
        programmaitems.add(new ProgrammaItem("16:55", "5v5 BORRELTIJD", "Hier nog een stuco tekstje Hier nog een stuco tekstje Hier nog een stuco tekstje Hier nog een stuco tekstje Hier nog een stuco tekstje Hier nog een stuco tekstje Hier nog een stuco tekstje Hier nog een stuco tekstje Hier nog een stuco tekstje Hier nog een stuco tekstje"));
        programmaitems.add(new ProgrammaItem("20:00", "GIRLS ONLY", "Hier nog een stuco tekstje"));
        programmaitems.add(new ProgrammaItem("20:00", "MEN SPECIAL", "Hier nog een stuco tekstje"));

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_monday, container, false);
        final ImageView imageView  = (ImageView) rootView.findViewById(R.id.maandagImageView);
        Bitmap maandag = BitmapExtend.decodeSampledBitmapFromResource(getResources(), R.drawable.maandag01, 200, 300);
        imageView.setImageBitmap(maandag);

        return rootView;
    }

}
