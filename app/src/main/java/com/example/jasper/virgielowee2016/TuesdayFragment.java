package com.example.jasper.virgielowee2016;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TuesdayFragment extends Fragment {

    final ArrayList<ProgrammaItem> programmaitems;

    public TuesdayFragment() {
        programmaitems = new ArrayList<>();
        programmaitems.add(new ProgrammaItem("16:55", "5v5 BORRELTIJD", "Hier nog een stuco tekstje Hier nog een stuco tekstje Hier nog een stuco tekstje Hier nog een stuco tekstje Hier nog een stuco tekstje Hier nog een stuco tekstje Hier nog een stuco tekstje Hier nog een stuco tekstje Hier nog een stuco tekstje Hier nog een stuco tekstje"));

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_monday, container, false);
        final ImageView imageView  = (ImageView) rootView.findViewById(R.id.maandagImageView);
        Bitmap maandag = BitmapExtend.decodeSampledBitmapFromResource(getResources(), R.drawable.dinsdag02, 200, 300);
        imageView.setImageBitmap(maandag);
        return rootView;
    }

}
