package com.example.jasper.virgielowee2016;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        View rootView = inflater.inflate(R.layout.fragment_tuesday, container, false);
        ProgrammaAdapter adapter = new ProgrammaAdapter(getContext());
        final ListView list  = (ListView) rootView.findViewById(R.id.tuesday_list);
        list.setAdapter(adapter);
        adapter.addAll(programmaitems);
        return rootView;
    }

}
