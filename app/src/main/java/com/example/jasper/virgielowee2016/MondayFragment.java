package com.example.jasper.virgielowee2016;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MondayFragment extends Fragment {

    final ArrayList<ProgrammaItem> programmaitems;
    private View rootView;
    private ProgrammaAdapter adapter;

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
        rootView = inflater.inflate(R.layout.fragment_monday, container, false);
        adapter = new ProgrammaAdapter(getContext());
        final ListView list  = (ListView) rootView.findViewById(R.id.programma_list);
        list.setAdapter(adapter);
        adapter.addAll(programmaitems);
        return rootView;
    }

}
